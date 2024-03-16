package org.example.main_commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import eu.jrie.jetbrains.kotlinshell.shell.shell
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class ProcessCallExample : CliktCommand(name = "shell", help = "shell to execute commands") {
    val command by option(help = "command to execute").default("")
    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    override fun run() {
        shell {
            val upper = stringLambda {
                it.uppercase() to ""
            }
            val streamLambda = streamLambda { inputStream, outputStream, outputStream2 ->
                echo("i am the- ---" + inputStream.bufferedReader().readText())
            }

            pipeline {
                "cat gradlew.bat".process() pipe upper pipe streamLambda
            }
        }
    }
}