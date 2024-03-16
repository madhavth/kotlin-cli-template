package org.example

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import org.example.main_commands.ProcessCallExample
import org.example.main_commands.UserNetworkCallExample

fun main(list: Array<String>) {
    Main("myapp").main(list)
}

class Main(name:String): CliktCommand(name=name) {
    override fun run() {
        // empty initialization
    }

    init {
        subcommands(
            ProcessCallExample(),
            UserNetworkCallExample()
        )
    }

}