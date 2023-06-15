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

    fun printFieldWithMineNumbers() {
        for (row in field.array.indices) {
            for (column in field.array[row].indices) {
                if (field.array[row][column].cellType == CellType.FREE) {
                    val mineNumber = getMineNumberByCoordinate(Coordinate(row, column))
                    print(if (mineNumber == 0) CellType.FREE.symbol else mineNumber)
                } else {
                    print(field.array[row][column].cellType.symbol)
                }
            }
            println()
        }
    }

    private fun getMineNumberByCoordinate(coordinate: Coordinate): Int {
        return getMineUp(coordinate) +
                getMineDown(coordinate) +
                getMineLeft(coordinate) +
                getMineRight(coordinate) +
                getMineUpLeft(coordinate) +
                getMineUpRight(coordinate) +
                getMineDownLeft(coordinate) +
                getMineDownRight(coordinate)
    }

    private fun getMineUp(coordinate: Coordinate): Int {
        return if (coordinate.row == 0) 0 else if (field.array[coordinate.row - 1][coordinate.column].cellType == CellType.MINE) 1 else 0
    }

    private fun getMineDown(coordinate: Coordinate): Int {
        return if (coordinate.row == field.rows - 1) 0 else if (field.array[coordinate.row + 1][coordinate.column].cellType == CellType.MINE) 1 else 0
    }

    private fun getMineLeft(coordinate: Coordinate): Int {
        return if (coordinate.column == 0) 0 else if (field.array[coordinate.row][coordinate.column - 1].cellType == CellType.MINE) 1 else 0
    }

    private fun getMineRight(coordinate: Coordinate): Int {
        return if (coordinate.column == field.columns - 1) 0 else if (field.array[coordinate.row][coordinate.column + 1].cellType == CellType.MINE) 1 else 0
    }

    private fun getMineUpLeft(coordinate: Coordinate): Int {
        return if (coordinate.row == 0 || coordinate.column == 0) 0 else if (field.array[coordinate.row - 1][coordinate.column - 1].cellType == CellType.MINE) 1 else 0
    }

    private fun getMineUpRight(coordinate: Coordinate): Int {
        return if (coordinate.row == 0 || coordinate.column == field.columns - 1) 0 else if (field.array[coordinate.row - 1][coordinate.column + 1].cellType == CellType.MINE) 1 else 0
    }

    private fun getMineDownLeft(coordinate: Coordinate): Int {
        return if (coordinate.row == field.rows - 1 || coordinate.column == 0) 0 else if (field.array[coordinate.row + 1][coordinate.column - 1].cellType == CellType.MINE) 1 else 0
    }

    private fun getMineDownRight(coordinate: Coordinate): Int {
        return if (coordinate.row == field.rows - 1 || coordinate.column == field.columns - 1) 0 else if (field.array[coordinate.row + 1][coordinate.column + 1].cellType == CellType.MINE) 1 else 0
    }

}