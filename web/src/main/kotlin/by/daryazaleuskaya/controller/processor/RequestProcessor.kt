package by.daryazaleuskaya.controller.processor

import by.daryazaleuskaya.exception.RecognitionException
import by.daryazaleuskaya.model.MessageModel
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.multipart.MultipartFile

@Component
class RequestProcessor @Autowired constructor(
    private val requestBuilderFactory: RequestBuilderFactory
) {

    inline fun <reified T> extractResponseBody(resp: Response): T {

        val jsonString = resp.body()?.string()
        if (resp.isSuccessful) {
            return jacksonObjectMapper().readerFor(T::class.java).readValue(jsonString)
        } else {
            val messageModel =
                jacksonObjectMapper().readerFor(MessageModel::class.java).readValue<MessageModel>(jsonString)
            throw RecognitionException(messageModel.message, resp.code())
        }
    }

    fun createRequest(url : String, multipartBody: MultipartBody, requestType : RequestMethod = RequestMethod.POST) : Response {

        val request = requestBuilderFactory.createRequest(url, multipartBody, requestType)

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .build()

        return okHttpClient.newCall(request).execute()
    }

    fun buildMultiPartBody(reqParamNamesToFiles: List<Pair<String, MultipartFile>>): MultipartBody {

        val multipartBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)

        for (item in reqParamNamesToFiles) {
            val reqParamName = item.first
            val file = item.second
            val fileBody = okhttp3.RequestBody.create(
                MediaType.parse(
                    file.contentType!!
                ), file.bytes
            )
            multipartBody.addFormDataPart(reqParamName, file.originalFilename, fileBody)
        }

        return multipartBody.build()
    }
}