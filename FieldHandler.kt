package minesweeper

import java.util.*
import kotlin.random.Random

class FieldHandler(private val field: Field) {
    fun setRandomMines(number: Int) {
        var mines = number
        while (mines > 0) {
            val row = Random.nextInt(0, field.rows)
            val column = Random.nextInt(0, field.columns)
            val cell = field.array[row][column]
            if (cell.cellType == CellType.HIDDEN) {
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
            for (cell in field.array[row]) {
                print(cell.cellSymbol.symbol)
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

    private fun getMineUp(coordinate: Coordinate) = if (coordinate.row == 0) 0 else if (field.array[coordinate.row - 1][coordinate.column].cellType == CellType.MINE) 1 else 0

    private fun getMineDown(coordinate: Coordinate) = if (coordinate.row == field.rows - 1) 0 else if (field.array[coordinate.row + 1][coordinate.column].cellType == CellType.MINE) 1 else 0

    private fun getMineLeft(coordinate: Coordinate) = if (coordinate.column == 0) 0 else if (field.array[coordinate.row][coordinate.column - 1].cellType == CellType.MINE) 1 else 0

    private fun getMineRight(coordinate: Coordinate) = if (coordinate.column == field.columns - 1) 0 else if (field.array[coordinate.row][coordinate.column + 1].cellType == CellType.MINE) 1 else 0

    private fun getMineUpLeft(coordinate: Coordinate) = if (coordinate.row == 0 || coordinate.column == 0) 0 else if (field.array[coordinate.row - 1][coordinate.column - 1].cellType == CellType.MINE) 1 else 0

    private fun getMineUpRight(coordinate: Coordinate) = if (coordinate.row == 0 || coordinate.column == field.columns - 1) 0 else if (field.array[coordinate.row - 1][coordinate.column + 1].cellType == CellType.MINE) 1 else 0

    private fun getMineDownLeft(coordinate: Coordinate) = if (coordinate.row == field.rows - 1 || coordinate.column == 0) 0 else if (field.array[coordinate.row + 1][coordinate.column - 1].cellType == CellType.MINE) 1 else 0

    private fun getMineDownRight(coordinate: Coordinate) = if (coordinate.row == field.rows - 1 || coordinate.column == field.columns - 1) 0 else if (field.array[coordinate.row + 1][coordinate.column + 1].cellType == CellType.MINE) 1 else 0

    fun setMark(coordinate: Coordinate) {
        val cell = getCellByCoordinate(coordinate)
        when (cell.cellSymbol) {
            CellSymbol.DOT -> cell.cellSymbol = CellSymbol.ASTERISK
            CellSymbol.ASTERISK -> cell.cellSymbol = CellSymbol.DOT
            else -> throw RuntimeException("There is a number here!")
        }
    }

    private fun getCellByCoordinate(coordinate: Coordinate) = field.array[coordinate.row][coordinate.column]

    fun getGameState(): GameState {
        if (field.array.flatten().count { it.cellSymbol == CellSymbol.X } > 0) {
            return GameState.LOST
        }

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
        return GameState.WON
    }

    private fun getNumberOfMines() = field.array.flatten().count { it.cellType == CellType.MINE }

    private fun getNumberOfAsterisks() = field.array.flatten().count { it.cellSymbol == CellSymbol.ASTERISK }

    fun setFree(coordinate: Coordinate) {
        val cell = getCellByCoordinate(coordinate)
        when (cell.cellType) {
            CellType.HIDDEN -> exploreFromCoordinate(coordinate)
            CellType.MINE -> makeBang(cell)
            CellType.FREE -> {}
        }
    }

    private fun exploreFromCoordinate(coordinate: Coordinate) {
        val stack = Stack<Cell>()
        stack.add(getCellByCoordinate(coordinate))
        while (stack.isNotEmpty()) {
            val currentCell = stack.pop()
            val dotAndNotMineCells = getDotAndNotMineCells(getNeighbourCells(currentCell))
            exploreCell(currentCell)
            dotAndNotMineCells.forEach {cell -> run {
                exploreCell(cell)
                if (cell.cellSymbol == CellSymbol.SLASH) {
                    stack.push(cell)
                }
            } }
        }
    }

    private fun exploreCell(cell: Cell) {
        cell.cellType = CellType.FREE
        val mineNumber = getMineNumberByCoordinate(getCoordinateByCell(cell))
        cell.cellSymbol = if (mineNumber == 0) CellSymbol.SLASH else CellSymbol.getSymbol(mineNumber.toString())
    }

    private fun getDotAndNotMineCells(cells: List<Cell>): List<Cell> {
        return cells.filter { cell -> (cell.cellSymbol == CellSymbol.DOT || cell.cellSymbol == CellSymbol.ASTERISK) && cell.cellType != CellType.MINE }
    }

    private fun getNeighbourCells(cell: Cell): List<Cell> {
        val coordinate = getCoordinateByCell(cell)
        val neighbourCells = mutableListOf<Cell>()
        if (coordinate.row > 0) { // up
            neighbourCells.add(getCellByCoordinate(Coordinate(coordinate.row - 1, coordinate.column)))
        }
        if (coordinate.row < field.rows - 1) { // down
            neighbourCells.add(getCellByCoordinate(Coordinate(coordinate.row + 1, coordinate.column)))
        }
        if (coordinate.column > 0) { // left
            neighbourCells.add(getCellByCoordinate(Coordinate(coordinate.row, coordinate.column - 1)))
        }
        if (coordinate.column < field.columns - 1) { // right
            neighbourCells.add(getCellByCoordinate(Coordinate(coordinate.row, coordinate.column + 1)))
        }
        if (coordinate.row > 0 && coordinate.column > 0) { // up-left
            neighbourCells.add(getCellByCoordinate(Coordinate(coordinate.row - 1, coordinate.column - 1)))
        }
        if (coordinate.row > 0 && coordinate.column < field.columns - 1) { // up-right
            neighbourCells.add(getCellByCoordinate(Coordinate(coordinate.row - 1, coordinate.column + 1)))
        }
        if (coordinate.row < field.rows - 1 && coordinate.column > 0) { // down-left
            neighbourCells.add(getCellByCoordinate(Coordinate(coordinate.row + 1, coordinate.column - 1)))
        }
        if (coordinate.row < field.rows - 1 && coordinate.column < field.columns - 1) { // down-right
            neighbourCells.add(getCellByCoordinate(Coordinate(coordinate.row + 1, coordinate.column + 1)))
        }
        return neighbourCells
    }

    private fun getCoordinateByCell(cell: Cell): Coordinate {
        for (row in field.array.indices) {
            for (column in field.array[row].indices) {
                if (cell === field.array[row][column]) {
                    return Coordinate(row, column)
                }
            }
        }
        throw RuntimeException("Cell not found")
    }

    private fun makeBang(cell: Cell) {
        cell.cellSymbol = CellSymbol.X
    }

}