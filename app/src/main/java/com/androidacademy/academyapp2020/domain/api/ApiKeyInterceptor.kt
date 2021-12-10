package com.androidacademy.academyapp2020.domain.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

private const val API_KEY_HEADER = "api_key"
private const val apiKey = "be7886445a6302ad1ed5279cb6afc102"

@Singleton
class ApiKeyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val urlWithKey = originalHttpUrl.newBuilder()
            .addQueryParameter(API_KEY_HEADER, apiKey)
            .build()

        val request = originalRequest.newBuilder()
            .url(urlWithKey)
            .build()

        return chain.proceed(request)
    }
}