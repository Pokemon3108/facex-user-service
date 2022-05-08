package by.daryazaleuskaya.controller

import by.daryazaleuskaya.PersonService
import by.daryazaleuskaya.controller.processor.RequestProcessor
import by.daryazaleuskaya.model.MessageModel
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/face")
class FaceController @Autowired constructor(
    private val personService: PersonService,
    private val requestProcessor: RequestProcessor
) {

    @Value("\${facex.upload.face.image.api}")
    private lateinit var UPLOAD_FACE_IMAGE_API: String

    private val PIC: String = "pic"

    @PostMapping("/{fullname}/group/{group}")
    @ResponseStatus(HttpStatus.CREATED)
    fun savePersonFace(
        @RequestParam("pic") file: MultipartFile,
        @PathVariable(required = true) fullname: String,
        @PathVariable(required = true) group: String
    ): MessageModel {

        val requestParamNameToFile = Pair(PIC, file)
        val multipartBody = requestProcessor.buildMultiPartBody(listOf(requestParamNameToFile))
        val url = UPLOAD_FACE_IMAGE_API.format(fullname, group)
        val response = requestProcessor.createRequest(url, multipartBody)
        if (response.isSuccessful && !personService.exists(fullname, group)) {
            personService.save(fullname, group)
        }
        val jsonString = response.body()?.string()
        return jacksonObjectMapper().readerFor(MessageModel::class.java).readValue(jsonString)

    }

    @PatchMapping("/{fullname}/group/{group}")
    fun updatePersonFace(
        @RequestParam("pic") file: MultipartFile,
        @PathVariable(required = true) fullname: String,
        @PathVariable(required = true) group: String
    ): MessageModel {

        val requestParamNameToFile = Pair(PIC, file)
        val multipartBody = requestProcessor.buildMultiPartBody(listOf(requestParamNameToFile))
        val url = UPLOAD_FACE_IMAGE_API.format(fullname, group)
        val response = requestProcessor.createRequest(url, multipartBody, RequestMethod.PATCH)
        val jsonString = response.body()?.string()
        return jacksonObjectMapper().readerFor(MessageModel::class.java).readValue(jsonString)
    }

}