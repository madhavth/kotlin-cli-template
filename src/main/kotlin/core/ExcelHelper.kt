package org.example.core

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream

class ExcelHelper {
    fun writeExcel(fileName: String, rowCallBack: (firstShell: XSSFCell,  sheet: XSSFSheet) -> Unit) {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Sheet1")
        val myCell = sheet.createRow(0).createCell(0)
        rowCallBack(myCell, sheet)

        // check if file called myExcel.xlsx exists first
        File(fileName).let {
            if(it.exists()) {
                throw Exception("File already exists..")
            }
        }

        workbook.write(FileOutputStream(fileName))
        workbook.close()
    }

    fun readExcel(file: File, postRowCallBack: () -> Unit, cellCallBack: (row: Row, cell: Cell) -> Unit) {
        val workbook = XSSFWorkbook(file)
        val sheet = workbook.getSheetAt(0)

        for ((i, row) in sheet.withIndex()) {
            for((j,cell) in row.withIndex()) {
                cellCallBack(row,cell)
            }
            postRowCallBack()
        }
        workbook.close()
    }
}