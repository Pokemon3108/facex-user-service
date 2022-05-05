package by.daryazaleuskaya.controller

import by.daryazaleuskaya.exception.RecognitionException
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
    private lateinit var RECOGNITION_USER_API: String

    @GetMapping("/group/{group}")
    fun recognizeFace(
        @RequestParam("pic") file: MultipartFile,
        @PathVariable(required = true) group: String
    ): RecognizedPersonModel {

        val multipartBody = buildMultiPartBody(file)

        val url = "${RECOGNITION_API}/${group}"
        val request = Request.Builder()
            .url(url)
            .post(multipartBody)
            .build()

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .build()

        val resp = okHttpClient.newCall(request).execute()
        return extractResponseBody(resp)
    }

    @GetMapping("/user/{username}")
    fun recognizeFaceByName(
        @RequestParam("pic") file: MultipartFile,
        @PathVariable(required = true) username: String
    ): RecognizedPersonModel {

        val multipartBody = buildMultiPartBody(file)

        val url = "${RECOGNITION_USER_API}/${username}"
        val request = Request.Builder()
            .url(url)
            .post(multipartBody)
            .build()

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .build()

        val resp = okHttpClient.newCall(request).execute()
        return extractResponseBody(resp)
    }

    private fun extractResponseBody(resp: Response): RecognizedPersonModel {

        val jsonString = resp.body()?.string()
        if (resp.isSuccessful) {
            return jacksonObjectMapper().readerFor(RecognizedPersonModel::class.java).readValue(jsonString)
        } else {
            val messageModel =
                jacksonObjectMapper().readerFor(MessageModel::class.java).readValue<MessageModel>(jsonString)
            throw RecognitionException(messageModel.message, resp.code())
        }
    }

}

fun buildMultiPartBody(file: MultipartFile): MultipartBody {

    val fileBody = okhttp3.RequestBody.create(
        MediaType.parse(
            file.contentType!!
        ), file.bytes
    )

    return MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("pic", file.originalFilename, fileBody)
        .build()
}