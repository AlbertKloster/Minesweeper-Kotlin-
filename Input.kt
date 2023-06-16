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



    fun getCoordinateAndCommand(): CoordinateAndCommand {
        print("Set/unset mines marks or claim a cell as free: ")
        val string = readln().trim()
        if (!string.matches(Regex("[1-9] [1-9] (free|mine)"))) {
            throw RuntimeException("Wrong coordinates!")
        }
        val (x, y, command) = string.split(" ")
        return CoordinateAndCommand(Coordinate(y.toInt() - 1, x.toInt() - 1), Command.getCommand(command))
    }
}