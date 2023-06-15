package minesweeper

import kotlin.random.Random

class FieldHandler(private val field: Field) {
    fun setRandomMines(number: Int) {
        var mines = number
        while (mines > 0) {
            val row = Random.nextInt(0, field.rows)
            val column = Random.nextInt(0, field.columns)
            val cell = field.array[row][column]
            if (cell.cellType == CellType.FREE) {
                cell.cellType = CellType.MINE
                mines--
            }
        }
    }

    fun setNumbers() {
        for (row in field.array.indices) {
            for (column in field.array[row].indices) {
                val cell = field.array[row][column]
                val mineNumber = getMineNumberByCoordinate(Coordinate(row, column))
                if (cell.cellType == CellType.FREE && mineNumber > 0) {
                    cell.cellSymbol = CellSymbol.getSymbol(mineNumber.toString())
                }
            }
        }
    }

    fun printField() {
        println(" │123456789│")
        println("—│—————————│")
        for (row in field.array.indices) {
            print("${row + 1}│")
            for (column in field.array[row].indices) {
                print(field.array[row][column].cellSymbol.symbol)
            }
            println("|")
        }
        println("—│—————————│")
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

    fun setMark(coordinate: Coordinate) {
        val cell = getCellByCoordinate(coordinate)
        when (cell.cellSymbol) {
            CellSymbol.DOT -> cell.cellSymbol = CellSymbol.ASTERISK
            CellSymbol.ASTERISK -> cell.cellSymbol = CellSymbol.DOT
            else -> throw RuntimeException("There is a number here!")
        }
    }

    private fun getCellByCoordinate(coordinate: Coordinate): Cell {
        return field.array[coordinate.row][coordinate.column]
    }

    fun getGameState(): GameState {
        if (getNumberOfMines() != getNumberOfAsterisks()) {
            return GameState.PLAY
        }

        for (row in field.array) {
            for (cell in row) {
                if (cell.cellType == CellType.MINE && cell.cellSymbol != CellSymbol.ASTERISK) {
                    return GameState.PLAY
                }
            }
        }
        return GameState.END
    }

    private fun getNumberOfMines(): Int {
        var count = 0
        for (row in field.array) {
            for (cell in row) {
                if (cell.cellType == CellType.MINE) {
                    count++
                }
            }
        }
        return count
    }

    private fun getNumberOfAsterisks(): Int {
        var count = 0
        for (row in field.array) {
            for (cell in row) {
                if (cell.cellSymbol == CellSymbol.ASTERISK) {
                    count++
                }
            }
        }
        return count
    }

}