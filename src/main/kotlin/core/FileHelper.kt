package org.example.core

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Semaphore
import java.io.File

class FileHelper {
    private val job = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    suspend fun readFile(lineCallBack: suspend (String) -> Boolean) {
        val file = File("read_me")

        if (!file.exists()) {
            throw Exception("File does not exist")
        }

        val coroutines = mutableListOf<Job>()

        file.forEachLine {
            val coroutine = coroutineScope.launch {
                if (!lineCallBack(it)) {
                    return@launch
                }
            }
            coroutines.add(coroutine)
        }

        coroutines.joinAll()
    }
}