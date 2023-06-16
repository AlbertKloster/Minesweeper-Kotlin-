package minesweeper

class Game {
    fun run() {
        val input = Input()
        val field = Field(9, 9)
        val fieldHandler = FieldHandler(field)

        try {
            fieldHandler.setRandomMines(input.getMineNumber())
            fieldHandler.setNumbers()
            fieldHandler.printField()
        } catch (e: RuntimeException) {
            println(e.message)
        }

        var gameState = GameState.PLAY
        while (gameState == GameState.PLAY) {
            try {
                val coordinateAndCommand = input.getCoordinateAndCommand()
                when (coordinateAndCommand.command) {
                    Command.FREE -> fieldHandler.setFree(coordinateAndCommand.coordinate)
                    Command.MINE -> fieldHandler.setMark(coordinateAndCommand.coordinate)
                }
                gameState = fieldHandler.getGameState()
                fieldHandler.printField()
            } catch (e: RuntimeException) {
                println(e.message)
            }
        }
        println(if (gameState == GameState.WON) "Congratulations! You found all the mines!" else "You stepped on a mine and failed!")
    }
}