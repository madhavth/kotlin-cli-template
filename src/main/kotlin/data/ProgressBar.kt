package org.example.data

class ProgressBar(private val totalTasks: Int, private val terminalWidth: Int) {
    private var completedTasks = 0

    fun update(completedTasks: Int) {
        this.completedTasks = completedTasks
        display()
    }

    private fun display() {
        val progress = completedTasks.toDouble() / totalTasks
        val progressBarWidth = terminalWidth - 10 // Leave space for percentage and brackets
        val completedBars = (progress * progressBarWidth).toInt()

        val progressBar = StringBuilder("[")
        for (i in 0 until completedBars) {
            progressBar.append("=")
        }
        for (i in completedBars until progressBarWidth) {
            progressBar.append(" ")
        }
        progressBar.append("] ")
        progressBar.append((progress * 100).toInt()).append("%")

        print("\r" + progressBar.toString())
        if (completedTasks == totalTasks) {
            println() // Print newline after progress bar completion
        }
    }
}
