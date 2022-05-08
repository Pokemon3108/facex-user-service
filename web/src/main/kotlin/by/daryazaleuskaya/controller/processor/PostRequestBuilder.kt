package by.daryazaleuskaya.controller.processor

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.springframework.stereotype.Component

@Component
class PostRequestBuilder : RequestBuilder {

    override fun sendRequest(url: String, multipartBody: MultipartBody) : Response {

        val request = Request.Builder()
            .url(url)
            .post(multipartBody)
            .build()

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .build()

        return okHttpClient.newCall(request).execute()
    }
}