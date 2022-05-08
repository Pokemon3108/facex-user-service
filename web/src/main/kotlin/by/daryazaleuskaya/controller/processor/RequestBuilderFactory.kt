package by.daryazaleuskaya.controller.processor

import okhttp3.MultipartBody
import okhttp3.Request
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMethod

@Component
class RequestBuilderFactory {

    fun createRequest(url: String, multipartBody: MultipartBody, requestMethod: RequestMethod): Request {
        val requestBuilder = Request.Builder()
            .url(url)
        return if (requestMethod == RequestMethod.PATCH) {
            requestBuilder
                .patch(multipartBody)
                .build()
        } else {
            requestBuilder
                .post(multipartBody)
                .build()
        }
    }

}