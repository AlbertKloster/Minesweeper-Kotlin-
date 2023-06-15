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
                val coordinate = input.getCoordinate()
                fieldHandler.setMark(coordinate)
                gameState = fieldHandler.getGameState()
                fieldHandler.printField()
            } catch (e: RuntimeException) {
                println(e.message)
            }
        }
        println("Congratulations! You found all the mines!")
    }
}