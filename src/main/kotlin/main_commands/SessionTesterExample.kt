package org.example.main_commands

import com.github.ajalt.clikt.core.CliktCommand
import org.example.core.HttpHelper

class SessionTesterExample : CliktCommand(name = "session") {

    private val httpClientHelper = HttpHelper()
    override fun run() {
        val formData = "username=tomsmith&password=SuperSecretPassword!"
        val response = httpClientHelper.makePostRequest<String>(
            "https://the-internet.herokuapp.com/authenticate",
            header = mapOf("Content-Type" to "application/x-www-form-urlencoded"),
            formData = formData
        )
        print(response.statusCode())
        val securedResponse = httpClientHelper.makeGetRequest<String>(
            "https://the-internet.herokuapp.com/secure",
        )
        print(securedResponse.body())
    }


}