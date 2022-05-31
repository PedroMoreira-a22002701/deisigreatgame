package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType {
    GET,
    POST
}

fun router(): (CommandType) -> ((GameManager, List<String>) -> String?)? {
    return { comando -> comandoTipo(comando) }
}

fun comandoTipo(commandType: CommandType): ((GameManager, List<String>) -> String?)? {
    if (commandType == CommandType.GET) {
        return { game, list -> comandoGet(game, list) }
    } else if (commandType == CommandType.POST) {
        return { game, list -> comandoPost(game, list) }
    }
    return null
}

fun comandoGet(gameManager: GameManager, argsList: List<String>): String? {
    when (argsList[0]) {
        "PLAYER" -> return getPlayer(gameManager, argsList[1])
        "PLAYERS_BY_LANGUAGE" -> return getPlayersByLanguage(gameManager, argsList)
        "POLYGLOTS" -> return getPolyglots(gameManager)
        "MOST_USED_POSITIONS" -> return getMostUsedPositions(gameManager, argsList[1].toInt())
        "MOST_USED_ABYSSES" -> return getMostUsedAbysses(gameManager, argsList[1].toInt())
    }
    return null
}

fun comandoPost(gameManager: GameManager, argsList: List<String>): String? {
    when (argsList[0]) {
        "MOVE" -> return postMove(gameManager, argsList[1].toInt())
        "ABYSS" -> return postAbyss(gameManager, argsList[1].toInt(), argsList[2].toInt())
    }
    return null
}

fun getPlayer(gameManager: GameManager, nome: String): String {
    if (gameManager.jogadores != null && gameManager.jogadores.isNotEmpty()) {
        gameManager.jogadores.filter { it.name.substringBefore(" ") == nome }.forEach { return it.toString() }
    }
    return "Inexistent player"
}

fun getPlayersByLanguage(gameManager: GameManager, argsList: List<String>): String {
    val programadoresString = gameManager.jogadores.filter { it.linguagensFavoritas.contains(argsList[1]) }

    if (programadoresString.isNotEmpty() && programadoresString.count() == 1) {
        return programadoresString.joinToString("", "", "") { it.name.substringBefore(" ") }
    } else if (programadoresString.isNotEmpty() && programadoresString.count() != 1) {
        return programadoresString.joinToString(",", "", "") { it.name.substringBefore(" ") }
    }
    return ""
}

fun getPolyglots(gameManager: GameManager): String {
    val programadores =
        gameManager.jogadores.filter { it.linguagensFavoritas.size > 1 }.sortedBy { it.linguagensFavoritas.size }
            .map { it.name.plus(":").plus(it.linguagensFavoritas.size) }

    return programadores.joinToString("\n", "", "")
}

fun getMostUsedPositions(gameManager: GameManager, maxResults: Int): String {
    return gameManager.casasPassadas.entries.sortedBy { it.value }.reversed().take(maxResults)
        .joinToString("\n", "", "") { it.key.toString().plus(":").plus(it.value.toString()) }
}

fun getMostUsedAbysses(gameManager: GameManager, maxResults: Int): String {

    return gameManager.abismosPassados.entries.sortedBy { it.value }.reversed().take(maxResults)
        .joinToString("\n", "", "")
        {
            it.key.toString().replace("0", "Erro de sintaxe")
                .replace("1", "Erro de lógica")
                .replace("2", "Exception")
                .replace("3", "File Not Found Exception")
                .replace("4", "Crash (aka Rebentanço)")
                .replace("5", "Duplicated Code")
                .replace("6", "Efeitos secundários")
                .replace("7", "Blue Screen Of Death")
                .replace("8", "Ciclo Infinito")
                .replace("9", "Segmentation Fault").plus(":").plus(it.value.toString())
        }
}

fun postMove(gameManager: GameManager, numero: Int): String {
    gameManager.moveCurrentPlayer(numero)
    val react = gameManager.reactToAbyssOrTool()
    return react ?: "OK"
}

fun postAbyss(gameManager: GameManager, abyssTypeId: Int, position: Int): String {
    if (gameManager.currentBoardWithAbyssOrTool[position] != null) {
        return "Position is occupied"
    }
    gameManager.createAbyss(abyssTypeId, position)
    return "OK"
}

