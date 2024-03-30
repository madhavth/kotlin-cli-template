package org.example.core

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ExcelHelper {

    fun writeToExcel(rows: List<List<String>>, columns: List<String>, fileName: String, append: Boolean = false) {
        val workbook: XSSFWorkbook

        if (append && File(fileName).exists()) {
            // Open existing workbook in append mode
            workbook = XSSFWorkbook(FileInputStream(fileName))
        } else {
            // Create a new workbook
            workbook = XSSFWorkbook()
        }

        val worksheet = if(workbook.numberOfSheets == 0) {
            workbook.createSheet("Sheet1")
        } else {
            workbook.getSheetAt(0)
        }

        val lastRowNum = if (append) worksheet.lastRowNum else 0

        // Create a header row (if not already present)
        if (worksheet.getRow(0) == null) {
            val headerRow = worksheet.createRow(0)
            columns.forEachIndexed { index, column ->
                headerRow.createCell(index).setCellValue(column)
            }
        }

        // Write data starting from the last row + 1 (if appending)
        var rowNum = lastRowNum + 1
        for (rowData in rows) {
            val row = worksheet.createRow(++rowNum)
            for (cellNum in rowData.indices) {
                val cell = row.createCell(cellNum)
                cell.setCellValue(rowData[cellNum])
            }
        }

        // Write the workbook to the specified file
        FileOutputStream(fileName).use {
            workbook.write(it)
        }
    }

    fun readExcel(file: File, postRowCallBack: () -> Unit, cellCallBack: (row: Row, cell: Cell) -> Unit) {
        if (!file.exists()) {
            return
        }
        val workbook = XSSFWorkbook(file)
        val sheet = workbook.getSheetAt(0)

        for ((i, row) in sheet.withIndex()) {
            for ((j, cell) in row.withIndex()) {
                cellCallBack(row, cell)
            }
            postRowCallBack()
        }
        workbook.close()
    }
}