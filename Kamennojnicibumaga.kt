import kotlin.random.Random

fun main() {
    val choices = arrayOf("Камень", "Ножницы", "Бумага")

    while (true) {
        println("\nСделайте свой выбор:")
        println("1 - Камень")
        println("2 - Ножницы")
        println("3 - Бумага")
        print("Введите номер: ")

        val playerChoice = readInt()
        if (playerChoice == null || playerChoice < 1 || playerChoice > 3) {
            println("Ошибка: Введите число от 1 до 3.")
            continue
        }

        val computerChoice = Random.nextInt(0, 3) // 0, 1 или 2

        println("\nВаш выбор: ${choices[playerChoice - 1]}")
        println("Выбор компьютера: ${choices[computerChoice]}")

        val result = determineWinner(playerChoice - 1, computerChoice)
        println(result)

        if (result.contains("Ничья")) {
            println("Переигрываем...\n")
            continue
        }

        break
    }
}

fun determineWinner(player: Int, computer: Int): String {
    return when {
        player == computer -> "Ничья!"
        (player == 0 && computer == 1) ||
                (player == 1 && computer == 2) ||
                (player == 2 && computer == 0) -> "Вы победили!"
        else -> "Вы проиграли!"
    }
}

fun readInt(): Int? {
    try {
        return readLine()?.toIntOrNull()
    } catch (e: NumberFormatException) {
        println("Ошибка: Введите целое число.")
        return null
    }
}

