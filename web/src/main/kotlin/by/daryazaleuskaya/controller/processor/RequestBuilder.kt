package by.daryazaleuskaya.controller.processor

import okhttp3.MultipartBody
import okhttp3.Response

interface RequestBuilder {

    fun sendRequest(url : String, multipartBody: MultipartBody) : Response
}