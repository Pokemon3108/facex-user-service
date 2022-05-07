package by.daryazaleuskaya.controller

import by.daryazaleuskaya.PersonService
import by.daryazaleuskaya.controller.processor.RequestProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1")
class PersonController @Autowired constructor(
    private val personService: PersonService,
    private val requestProcessor : RequestProcessor
) {

    @Value("\${facex.upload.person.image.api}")
    private lateinit var UPLOAD_PERSON_IMAGE_API: String

    private val PIC : String= "pic"

    @PostMapping("/person/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    fun savePersonFace(
        @RequestParam("pic") file: MultipartFile,
        @PathVariable(required = true) username: String
    ): String? {

        if (!personService.exists(username)) {

        }

        val requestParamNameToFile =  Pair(PIC, file)
        val multipartBody = requestProcessor.buildMultiPartBody(listOf(requestParamNameToFile))
        val url = UPLOAD_PERSON_IMAGE_API + "/${username}"
        val response = requestProcessor.createRequest(url, multipartBody)
        return response.body()?.string()

    }

}