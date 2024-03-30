package org.example.main_commands.examples

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.terminal
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.enum
import org.example.data.ProgressBar

enum class Test {
    LLE, PROD
}
class ProgressBarExample: CliktCommand(name="progress") {

    override fun run() {
        // enum choice
        val progressBar = ProgressBar(100, terminal.info.width)

        for (i in 0 until 100) {
            // Do some work
            // For demonstration purposes, we'll just sleep for a short time
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            // Update progress bar
            progressBar.update(i + 1)
        }
    }
}