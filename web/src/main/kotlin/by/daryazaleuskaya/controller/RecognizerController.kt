package by.daryazaleuskaya.controller

import by.daryazaleuskaya.exception.RecognitionException
import by.daryazaleuskaya.model.ImageComparisonModel
import by.daryazaleuskaya.model.MessageModel
import by.daryazaleuskaya.model.RecognizedPersonModel
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/api/v1/recognition")
class RecognizerController {

    @Value("\${facex.image.recognition.api}")
    private lateinit var RECOGNITION_API: String

    @Value("\${facex.image.recognition.user.api}")
    private lateinit var RECOGNITION_USERNAME_API: String

    @Value("\${facex.image.recognition.pair.api}")
    private lateinit var RECOGNITION_PAIR_API : String

    private val PIC : String = "pic"
    private val PIC1 : String = "pic1"
    private val PIC2 : String = "pic2"

    @GetMapping("/group/{group}")
    fun recognizeFace(
        @RequestParam("pic") file: MultipartFile,
        @PathVariable(required = true) group: String
    ): RecognizedPersonModel {

        val requestParamNameToFile =  Pair(PIC, file)
        val multipartBody = buildMultiPartBody(listOf(requestParamNameToFile))

        val url = "${RECOGNITION_API}/${group}"
        val response = createRequest(url, multipartBody)
        return extractResponseBody(response)
    }

    @GetMapping("/user/{username}")
    fun recognizeFaceByName(
        @RequestParam("pic") file: MultipartFile,
        @PathVariable(required = true) username: String
    ): RecognizedPersonModel {

        val requestParamNameToFile =  Pair(PIC, file)
        val multipartBody = buildMultiPartBody(listOf(requestParamNameToFile))

        val url = "${RECOGNITION_USERNAME_API}/${username}"

        val response = createRequest(url, multipartBody)
        return extractResponseBody(response)
    }

    @GetMapping("/pair")
    fun comparePairOfFaces(@RequestParam("pic1") file1: MultipartFile, @RequestParam("pic2") file2: MultipartFile) : ImageComparisonModel {

        val requestParamNameToFile1 =  Pair(PIC1, file1)
        val requestParamNameToFile2 =  Pair(PIC2, file2)
        val multipartBody = buildMultiPartBody(listOf(requestParamNameToFile1, requestParamNameToFile2))
        val url = RECOGNITION_PAIR_API
        val response = createRequest(url, multipartBody)
        return extractResponseBody(response)
    }

    private fun createRequest(url : String, multipartBody: MultipartBody) : Response {

        val request = Request.Builder()
            .url(url)
            .post(multipartBody)
            .build()

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .build()

        return okHttpClient.newCall(request).execute()
    }

    private inline fun <reified T> extractResponseBody(resp: Response): T {

        val jsonString = resp.body()?.string()
        if (resp.isSuccessful) {
            return jacksonObjectMapper().readerFor(T::class.java).readValue(jsonString)
        } else {
            val messageModel =
                jacksonObjectMapper().readerFor(MessageModel::class.java).readValue<MessageModel>(jsonString)
            throw RecognitionException(messageModel.message, resp.code())
        }
    }

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