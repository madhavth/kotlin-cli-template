package org.example

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import kotlinx.coroutines.sync.Semaphore
import org.example.main_commands.examples.*

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
            SessionTesterExample(),
            ProgressBarExample(),
            SemaphoreExample()
        )
    }

}