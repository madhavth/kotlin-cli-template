package org.example

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import org.example.main_commands.ExcelExample
import org.example.main_commands.ProcessCallExample
import org.example.main_commands.SessionTesterExample
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
            UserNetworkCallExample(),
            ExcelExample(),
            SessionTesterExample()
        )
    }

}