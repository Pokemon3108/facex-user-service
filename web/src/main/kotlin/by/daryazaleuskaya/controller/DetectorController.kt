package by.daryazaleuskaya.controller

import by.daryazaleuskaya.controller.processor.RequestProcessor
import by.daryazaleuskaya.model.FaceCoordinatesModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/detection")
class DetectorController @Autowired constructor(
    private val requestProcessor: RequestProcessor
) {

    @Value("\${facex.image.detection.api}")
    private lateinit var DETECTION_API: String

    private val PIC: String = "pic"

    @GetMapping
    fun detectFace(
        @RequestParam("pic") file: MultipartFile
    ): Array<FaceCoordinatesModel> {

        val requestParamNameToFile = Pair(PIC, file)
        val multipartBody = requestProcessor.buildMultiPartBody(listOf(requestParamNameToFile))

        val url = DETECTION_API
        val response = requestProcessor.createRequest(url, multipartBody)
        return requestProcessor.extractResponseBody(response)
    }

    @GetMapping("/dec")
    fun detectFace1(
        @RequestParam("pic") file: MultipartFile
    ) : String {
        return "ddfd"
    }
}