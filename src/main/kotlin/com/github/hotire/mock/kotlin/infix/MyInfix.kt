package com.github.hotire.mock.kotlin.infix

class MyString {
    var string = ""
    infix fun add(other: String) {
        this.string = this.string + other
    }
}
