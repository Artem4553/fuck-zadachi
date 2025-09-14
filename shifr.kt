import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    print("Введите исходное сообщение: ")
    val message = scanner.nextLine().uppercase()

    print("Введите ключ: ")
    val key = scanner.nextLine().uppercase()

    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
    val table = createStandardVigenereTableFromImage(alphabet)

    println("\nШифровальная таблица:")
    printVigenereTable(table, alphabet)

    val encryptedMessage = vigenereEncrypt(message, key, table, alphabet)

    println("\nТекст:    $message")
    println("Ключ:    ${key.repeat(message.length / key.length) + key.substring(0, message.length % key.length)}")
    println("Итог:    $encryptedMessage")
}

fun createStandardVigenereTableFromImage(alphabet: String): Array<CharArray> {
    val table = Array(alphabet.length) { CharArray(alphabet.length) }
    for (i in 0 until alphabet.length) {
        for (j in 0 until alphabet.length) {
            table[i][j] = alphabet[(i + j) % alphabet.length]
        }
    }
    return table
}

fun vigenereEncrypt(message: String, key: String, table: Array<CharArray>, alphabet: String): String {
    val encryptedMessage = StringBuilder()
    for (i in 0 until message.length) {
        val messageChar = message[i]
        val keyChar = key[i % key.length]

        val messageIndex = alphabet.indexOf(messageChar)
        val keyIndex = alphabet.indexOf(keyChar)

        if (messageIndex == -1) {
            encryptedMessage.append(messageChar) // Оставляем символы, не входящие в алфавит, как есть
            continue
        }

        val encryptedChar = table[keyIndex][messageIndex]
        encryptedMessage.append(encryptedChar)
    }
    return encryptedMessage.toString()
}

fun printVigenereTable(table: Array<CharArray>, alphabet: String) {
    print("  ")
    for (char in alphabet) {
        print(char)
    }
    println()

    for (i in 0 until table.size) {
        print("${alphabet[i]} ")
        for (j in 0 until table[i].size) {
            print(table[i][j])
        }
        println()
    }
}

fun readInt(scanner: Scanner): Int? {
    try {
        return scanner.nextLine().toInt()
    } catch (e: NumberFormatException) {
        println("Ошибка: Введите целое число.")
        return null
    }
}
