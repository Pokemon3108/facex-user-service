package by.daryazaleuskaya.controller

import by.daryazaleuskaya.dto.ImageDto
import by.daryazaleuskaya.model.RecognizedPersonModel
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/image")
class ImageController {

    @Value("\${facex.recognition.bytes.api}")
    private lateinit var RECOGNITION_BYTES_API: String;

    @PostMapping("/recognition")
    fun recognizeFace(@RequestParam("pic") file: MultipartFile): RecognizedPersonModel {

        val fileBody = okhttp3.RequestBody.create(
            MediaType.parse(
                file.contentType!!
            ), file.bytes
        )
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .build()

        val multipartBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("pic", file.originalFilename, fileBody)
            .build()

        val request = Request.Builder()
            .url(RECOGNITION_BYTES_API)
            .post(multipartBody)
            .build()

        val resp = okHttpClient.newCall(request).execute()
        val jsonString = resp.body()?.string()

        return jacksonObjectMapper().readerFor(RecognizedPersonModel::class.java).readValue(jsonString)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveImage(@RequestBody imageModel: ImageDto): ResponseEntity<String>? {
        return null
    }
}