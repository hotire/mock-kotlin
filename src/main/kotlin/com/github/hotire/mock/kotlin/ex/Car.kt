package com.github.hotire.mock.kotlin.ex

class Car {
    fun drive(direction: Direction): String {
        return direction.name
    }

    fun stop() = "STOP"
}

enum class Direction {
    NORTH
}

data class TrafficSystem(
    var carGroup: MutableList<Car> = mutableListOf()
)
