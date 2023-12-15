import java.util.*
fun main() {
    val secretNumber = generateSecretNumber()
    println("Добро пожаловать в игру Быки и коровы! Попробуйте отгадать 4-значное число, загаданное компьютером.")
    var guessed = false
    while (!guessed) {
        print("Введите вашу попытку: ")
        val guess = readLine()
        if (guess != null && guess.length == 4 && guess.all { it.isDigit() } && guess.toSet().size == 4) {
            val result = checkGuess(secretNumber, guess)
            if (result.first == 4) {
                println("Поздравляем, вы угадали число $secretNumber!")
                guessed = true
            } else {
                println("Быки: ${result.first}, Коровы: ${result.second}")
            }
        } else {
            println("Пожалуйста, введите 4-значное число с неповторяющимися цифрами.")
        }
    }
}

fun generateSecretNumber(): String {
    var number = ""
    repeat(4) {
        number += Random.nextInt(10)
            .toString()
    }
    return number
}

fun checkGuess(secret: String, guess: String): Pair<Int, Int> {
    var bulls = 0
    var cows = 0
    for (i in secret.indices) {
        if (secret[i] == guess[i]) {
            bulls++
        } else if (secret.contains(guess[i])) {
            cows++
        }
    }
    return Pair(bulls, cows)
}