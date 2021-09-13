package com.github.hotire.mock.kotlin.ex

class Car {
    fun drive(direction: Direction): String {
        return direction.name
    }
}

enum class Direction {
    NORTH
}
