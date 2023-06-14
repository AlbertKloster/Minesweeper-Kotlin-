package minesweeper

fun main() {
    val field = Field(9, 9)
    val fieldHandler = FieldHandler(field)
    print("How many mines do you want on the field? ")
    fieldHandler.setRandomMines(readln().toInt())
    fieldHandler.printField()
}
