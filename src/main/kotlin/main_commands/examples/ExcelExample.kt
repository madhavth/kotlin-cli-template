package org.example.main_commands.examples

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.example.core.ExcelHelper
import java.io.File
import java.io.FileOutputStream

class ExcelExample : CliktCommand(name = "excel", help = "process to invoke excel") {
    val excelHelper = ExcelHelper()
    val append by option("-a","--append").flag(default =false)
    override fun run() {
        writeExcel()
//        readExcel(File("myExcel.xlsx"))
    }

    private fun writeExcel() {
        excelHelper.writeToExcel(
             fileName = "myExcel.xlsx",
            rows = listOf(
                listOf("1", "2", "3"),
                listOf("4", "5", "6"),
                listOf("7", "8", "9"),
                listOf("1", "2", "3"),
                listOf("4", "5", "6"),
                listOf("7", "8", "9"),
            ),
            columns = listOf("A", "B", "C"),
            append = true
        )
    }

    private fun readExcel(file: File) {
        excelHelper.readExcel(file, postRowCallBack = {
            println()
        }, cellCallBack = { row, cell ->
            print(cell)
        })
    }
}