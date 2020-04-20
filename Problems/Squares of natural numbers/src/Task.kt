import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    // put your code here
    val number = scanner.nextInt()
    var i = 1

    while (number >= i*i) {
        println(i*i)
        i++
    }
}