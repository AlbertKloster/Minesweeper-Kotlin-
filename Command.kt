package minesweeper

enum class Command(val command: String) {
    FREE("free"), MINE("mine");

    companion object {
        fun getCommand(string: String): Command {
            for (command in Command.values()) {
                if (command.command == string) {
                    return command
                }
            }
            throw RuntimeException("Wrong command $string")
        }
    }

}