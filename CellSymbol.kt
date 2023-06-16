package minesweeper

enum class CellSymbol(val symbol: String) {
    DOT("."),
    ASTERISK("*"),
    X("X"),
    SLASH("/"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    companion object {
        fun getSymbol(string: String): CellSymbol {
            for (cellSymbol in CellSymbol.values()) {
                if (cellSymbol.symbol == string) {
                    return cellSymbol
                }
            }
            throw RuntimeException("Wrong symbol $string")
        }
    }

}