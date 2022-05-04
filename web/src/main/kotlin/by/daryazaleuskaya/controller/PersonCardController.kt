package by.daryazaleuskaya.controller

import by.daryazaleuskaya.PersonCardService
import by.daryazaleuskaya.dto.PersonCardDto
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1")
class PersonCardController @Autowired constructor(
    private val personCardService: PersonCardService
) {

    @Value("\${facex.upload.person.image.api}")
    private lateinit var UPLOAD_PERSON_IMAGE_API: String

    @PostMapping("/personcard/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    fun savePersonFace(
        @RequestParam("pic") file: MultipartFile,
        @PathVariable(required = true) username: String
    ): String? {

        val multipartBody = buildMultiPartBody(file)
        val url = UPLOAD_PERSON_IMAGE_API + "/${username}"
        val request = Request.Builder()
            .url(url)
            .post(multipartBody)
            .build()

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .build()

        val response = okHttpClient.newCall(request).execute()
        return response.body()?.string()

    }

    @PostMapping("/personcard")
    @ResponseStatus(HttpStatus.CREATED)
    fun savePersonData(@RequestBody personCard: PersonCardDto) {
        personCardService.save(personCard)
    }

}