package minesweeper

class Input {
    fun getMineNumber(): Int {
        print("How many mines do you want on the field? ")
        val string = readln().trim()
        if (!string.matches(Regex("80|[1-7][0-9]|[1-9]"))) {
            throw RuntimeException("The number of mines should be from 1 to 80")
        }
        return string.toInt()
    }

    fun getCoordinate(): Coordinate {
        print("Set/delete mines marks (x and y coordinates): ")
        val string = readln().trim()
        if (!string.matches(Regex("[1-9] [1-9]"))) {
            throw RuntimeException("Wrong coordinates!")
        }
        val (x, y) = string.split(" ")
        return Coordinate(y.toInt() - 1, x.toInt() - 1)
    }
}