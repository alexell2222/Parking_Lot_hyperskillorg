package parking

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    var spot1 = CarSpot(false)
    var spot2 = CarSpot(true)

    //user input
    when(scanner.next()) {
        "park" ->{
            spot2.park(scanner.next(), scanner.next(), 2)
        }
        "leave" -> {
            when(scanner.next()) {
                "1" -> spot1.leave(1)
                "2" -> spot2.leave(2)
            }
        }
    }




}

class CarSpot(var empty: Boolean) {

    var spot = 1
    var licensePlate: String = "KA-01-HH-2347"
    var colorOfCar: String = "white"

    fun park(licensePlate: String, colorOfCar: String, spot: Int) {
        this.licensePlate = licensePlate
        this.colorOfCar = colorOfCar
        this.spot = spot
        empty = false

        println("${colorOfCar.capitalize()} car parked on the spot $spot. ")
    }

    fun leave(spot: Int) {
        if (empty) {
            println("There is no car in the spot $spot. ")
        } else {
            this.licensePlate = ""
            this.colorOfCar = ""
            this.spot = spot
            empty = true

            println("Spot $spot is free.")
        }
    }
}