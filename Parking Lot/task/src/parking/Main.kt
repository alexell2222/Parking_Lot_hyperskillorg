package parking

import java.util.*
import kotlin.system.exitProcess

fun main() {
    val scanner = Scanner(System.`in`)

    var nextAvailableSpot = 1
    var spotToLeave = 0

    var parking = Array(0) {CarSpot(true)}
    fun createParking (size: Int) {
        nextAvailableSpot = 1
        parking = Array(size) {CarSpot(true)}
        for (i in parking.indices) {
            parking[i] = CarSpot(true)
        }
        println("Created a parking lot with ${size-1} spots.")
    }

    //return true if all parking spots are full
    fun isParkingFull(): Boolean {
        var flag = true
        for (i in 1..parking.lastIndex) {
            if (parking[i].empty) {
                flag = false
            }
        }
        return flag
    }

    //return true if all the parking spots are empty
    fun isParkingEmpty(): Boolean {
        var emptySpotsCounter = 0
        for (i in 1..parking.lastIndex) {
            if (parking[i].empty) {
                emptySpotsCounter ++
            }
        }
        return emptySpotsCounter == parking.lastIndex

    }

    //search parking spots one by one
    fun findNextAvailableSpot() {
        if (!isParkingFull()) {
            nextAvailableSpot = 1
            while (!parking[nextAvailableSpot].empty) {
                nextAvailableSpot ++
            }
        }
    }


    fun parkingStatus() {
        if (isParkingEmpty()) {
            println("Parking lot is empty.")
        } else {
            for (i in 1..parking.lastIndex) {
                if (!parking[i].empty){
                    println("$i ${parking[i]}")
                }
            }
        }
    }

    fun findCarByColor(color: String, regOrSpot: String) {
        var noSuchCar = true
        var carsFound = ""
        for (i in 1..parking.lastIndex) {
            if (parking[i].colorOfCar.toLowerCase() == color.toLowerCase()) {
                noSuchCar = false
                when(regOrSpot) {
                    "reg" -> {
                        carsFound += parking[i].licensePlate+", "
                    }
                    "spot" -> {
                        carsFound += "$i, "
                    }
                }
            }
        }
        if (noSuchCar) {
            println("No cars with color ${color.toUpperCase()} were found.")
        } else {
            println(carsFound.dropLast(2))
        }
    }

    fun findCarByReg(reg: String) {
        var noSuchCar = true
        var carsFound = ""
        for (i in 1..parking.lastIndex) {
            if (parking[i].licensePlate == reg) {
                carsFound += "$i, "
                noSuchCar = false
            }
        }
        if (noSuchCar) {
            println("No cars with registration number $reg were found.")
        } else {
            println(carsFound.dropLast(2))
        }
    }


    //user input
    while (true) {
        when(scanner.next()) {
            "create" -> {
                createParking(scanner.nextInt()+1)
            }
            "park" ->{
                if (parking.size == 0) {
                    println("Sorry, a parking lot has not been created.")
                } else if (isParkingFull()) {
                    println("Sorry, parking lot is full.")
                } else {
                    parking[nextAvailableSpot].park(scanner.next(), scanner.next(), nextAvailableSpot)
                    findNextAvailableSpot()
                }
            }
            "leave" -> {
                if (parking.size == 0) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    spotToLeave = scanner.nextInt()
                    parking[spotToLeave].leave(spotToLeave)
                    findNextAvailableSpot()
                }
            }
            "status" -> {
                if (parking.size == 0) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    parkingStatus()
                }
            }
            "reg_by_color" -> {
                if (parking.size == 0) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    findCarByColor(scanner.next(), "reg")
                }
            }
            "spot_by_color" -> {
                if (parking.size == 0) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    findCarByColor(scanner.next(), "spot")
                }
            }
            "spot_by_reg" -> {
                if (parking.size == 0) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    findCarByReg(scanner.next())
                }
            }
            "exit" -> {
                exitProcess(1)
            }
        }
    }
}



class CarSpot(var empty: Boolean) {

    var licensePlate: String = ""
    var colorOfCar: String = ""

    override fun toString(): String {
        return "$licensePlate $colorOfCar"
    }
    fun park(licensePlate: String, colorOfCar: String, spot: Int) {
        this.licensePlate = licensePlate
        this.colorOfCar = colorOfCar
        empty = false

        println("${colorOfCar.capitalize()} car parked in spot $spot.")
    }

    fun leave(spot: Int) {
        if (empty) {
            println("There is no car in the spot $spot. ")
        } else {
            this.licensePlate = ""
            this.colorOfCar = ""
            empty = true

            println("Spot $spot is free.")
        }
    }
}