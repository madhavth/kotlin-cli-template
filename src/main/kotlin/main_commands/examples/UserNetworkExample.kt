package org.example.main_commands.examples

import com.github.ajalt.clikt.core.CliktCommand
import eu.jrie.jetbrains.kotlinshell.shell.shell
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.example.core.httpKtorClient
import org.example.models.User

class UserNetworkCallExample : CliktCommand(name = "network", help = "process to invoke network call") {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun run() {
        runBlocking {
            shell {
                val response = httpKtorClient.get("https://jsonplaceholder.typicode.com/users")
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
