package org.example.core

import io.ktor.http.ContentType.Message.Http
import org.apache.poi.ss.formula.functions.T
import java.net.CookieManager
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class HttpHelper {
    private val cookieHandler = CookieManager().apply {
        setCookiePolicy(
            java.net.CookiePolicy.ACCEPT_ALL
        )
    }

    private val httpClient: HttpClient = HttpClient.newBuilder()
        .cookieHandler(cookieHandler)
        .build()


    fun <T> makeGetRequest(
        url: String, header: Map<String, String>? = null,
    ): HttpResponse<out T> {
        val getRequestBuilder = HttpRequest.newBuilder().uri(URI(url)).GET().apply {
            if (header != null) {
                for ((key, value) in header) {
                    this.header(key, value)
                }
            }
        }

        return httpClient.send(
            getRequestBuilder.build(),
            HttpResponse.BodyHandlers.ofString()
        ) as HttpResponse<T>
    }


    fun <T> makePostRequest(
        url: String, header: Map<String, String>? = null,
        formData: String? = null
    ): HttpResponse<T> {
        val postRequestBuilder = HttpRequest.newBuilder()
            .uri(URI(url)).apply {
                POST(
                    if (formData == null) HttpRequest.BodyPublishers.noBody()
                    else
                        HttpRequest.BodyPublishers.ofString(formData)
                )

                if (header != null) {
                    for ((key, value) in header) {
                        this.header(key, value)
                    }
                }
            }


        return httpClient.send(
            postRequestBuilder.build(),
            HttpResponse.BodyHandlers.ofString()
        ) as HttpResponse<T>
    }


}
