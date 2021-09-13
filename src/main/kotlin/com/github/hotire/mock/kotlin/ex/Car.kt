package com.github.hotire.mock.kotlin.ex

class Car {
    fun drive(direction: Direction): String {
        return direction.name
    }
}

enum class Direction {
    NORTH
}

data class TrafficSystem(var car1: Car, var car2: Car, var car3: Car)
