package org.example.main_commands

import com.github.ajalt.clikt.core.CliktCommand
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.example.core.ExcelHelper
import java.io.File
import java.io.FileOutputStream

class ExcelExample : CliktCommand(name = "excel", help = "process to invoke excel") {
    val excelHelper = ExcelHelper()
    override fun run() {
        writeExcel()
        readExcel(File("myExcel.xlsx"))
    }

    private fun writeExcel() {
        excelHelper.writeExcel("myExcel.xlsx") { firstCell, sheet ->
            firstCell.setCellValue("Madhav")
            for (i in 1..4) {
                val row = sheet.createRow(i)
                for (j in 0..3) {
                    val cell = row.createCell(j)
                    cell.setCellValue(((i - 1) * 4 + j + 1).toString())
                }
            }
        }
    }

    private fun readExcel(file: File) {
        excelHelper.readExcel(file, postRowCallBack = {
            println()
        }, cellCallBack = { row, cell ->
            print(cell)
        })
    }
}