package minesweeper

fun main() {
    val field = Field(9, 9)
    val fieldHandler = FieldHandler(field)
    fieldHandler.setRandomMines(10)
    fieldHandler.printField()
}
