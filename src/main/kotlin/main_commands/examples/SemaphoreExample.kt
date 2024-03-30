package org.example.main_commands.examples

import com.github.ajalt.clikt.core.CliktCommand
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Semaphore
import org.example.core.FileHelper

class SemaphoreExample : CliktCommand(name = "sema") {
    val fileHelper = FileHelper()
    val semaphore = Semaphore(5)
    val job = SupervisorJob()
    val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    override fun run() {
        runBlocking {
            println("Semaphore Example")
            fileHelper.readFile {
                semaphore.acquire()
                echo(it)
                delay(1000)
                semaphore.release()
                true
            }
        }
    }
}
