package minesweeper

import kotlin.random.Random

class FieldHandler(private val field: Field) {
    fun setRandomMines(number: Int) {
        var mines = number
        while(mines > 0) {
            val row = Random.nextInt(0, field.rows)
            val column = Random.nextInt(0, field.columns)
            val cell = field.array[row][column]
            if (cell.cellType == CellType.FREE) {
                cell.cellType = CellType.MINE
                mines--
            }
        }
    }

    fun printField() {
        for (row in field.array) {
            for (cell in row) {
                print(cell.cellType.symbol)
            }
            println()
        }
    }
}