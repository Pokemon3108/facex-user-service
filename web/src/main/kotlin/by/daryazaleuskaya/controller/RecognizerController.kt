package by.daryazaleuskaya.controller

import by.daryazaleuskaya.PersonService
import by.daryazaleuskaya.StatisticService
import by.daryazaleuskaya.controller.processor.RequestProcessor
import by.daryazaleuskaya.model.ImageComparisonModel
import by.daryazaleuskaya.model.RecognizedPersonModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/api/v1/recognition")
class RecognizerController @Autowired constructor(
     private val requestProcessor: RequestProcessor,
     private val statisticService: StatisticService,
     private val personService: PersonService
) {

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
        val multipartBody = requestProcessor.buildMultiPartBody(listOf(requestParamNameToFile))

        val url = RECOGNITION_API.format(group)
        val response = requestProcessor.createRequest(url, multipartBody)
        val recognizedPersonModel : RecognizedPersonModel = requestProcessor.extractResponseBody(response)
        processRecognitionStatistic(recognizedPersonModel, group)
        return recognizedPersonModel
    }

    @GetMapping("/user/{username}/group/{group}")
    fun recognizeFaceByName(
        @RequestParam("pic") file: MultipartFile,
        @PathVariable(required = true) username: String,
        @PathVariable(required = true) group: String
    ): RecognizedPersonModel {

        val requestParamNameToFile =  Pair(PIC, file)
        val multipartBody = requestProcessor.buildMultiPartBody(listOf(requestParamNameToFile))

        val url = RECOGNITION_USERNAME_API.format(username)

        val response = requestProcessor.createRequest(url, multipartBody)
        val recognizedPersonModel : RecognizedPersonModel = requestProcessor.extractResponseBody(response)
        processRecognitionStatistic(recognizedPersonModel, group)
        return recognizedPersonModel
    }

    @GetMapping("/pair")
    fun comparePairOfFaces(@RequestParam("pic1") file1: MultipartFile, @RequestParam("pic2") file2: MultipartFile) : ImageComparisonModel {

        val requestParamNameToFile1 =  Pair(PIC1, file1)
        val requestParamNameToFile2 =  Pair(PIC2, file2)
        val multipartBody = requestProcessor.buildMultiPartBody(listOf(requestParamNameToFile1, requestParamNameToFile2))
        val url = RECOGNITION_PAIR_API
        val response = requestProcessor.createRequest(url, multipartBody)
        return requestProcessor.extractResponseBody(response)
    }

    private fun processRecognitionStatistic(recognizedPersonModel : RecognizedPersonModel, group : String) {

        if (recognizedPersonModel.wasRecognized) {
            val personDto = personService.buildPersonDto(recognizedPersonModel.name, group)
            statisticService.addRecord(personDto)
        }
    }

}