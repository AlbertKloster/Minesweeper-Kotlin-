package minesweeper

class Field(val rows: Int, val columns: Int) {
    val array = Array(rows) { Array(columns) { Cell(CellType.FREE)} }
}