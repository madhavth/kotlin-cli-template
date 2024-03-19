package org.example.main_commands

import com.github.ajalt.clikt.core.CliktCommand
import eu.jrie.jetbrains.kotlinshell.shell.shell
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.example.core.httpClient
import org.example.models.User
import java.io.File
import java.io.FileOutputStream

class UserNetworkCallExample : CliktCommand(name = "network", help = "process to invoke network call") {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun run() {
        runBlocking {
            shell {
                val response = httpClient.get("https://jsonplaceholder.typicode.com/users")
                print(response.status)
                val myRes = response.body<List<User>>()
                print(myRes.size)
                for(i in myRes) {
                    echo(i.name)
                }
            }
        }
    }

}
