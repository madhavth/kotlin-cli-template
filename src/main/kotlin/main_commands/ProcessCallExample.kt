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
    val command by option(help = "command to execute").default("echo")
    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    override fun run() {
        shell {
            val upper = stringLambda {
                it.uppercase() to ""
            }
//            val streamLambda = streamLambda { inputStream, outputStream, outputStream2 ->
//                echo("i am the- ---" + inputStream.bufferedReader().readText())
//            }

            "echo starting streaming lambda pipeline now..."()
            pipeline {
                "echo hello world".process() pipe upper //pipe streamLambda
            }
            command()
        }
    }
}