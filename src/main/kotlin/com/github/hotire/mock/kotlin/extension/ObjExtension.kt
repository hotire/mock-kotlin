package com.github.hotire.mock.kotlin.extension

data class Obj(val value: Int)

val Obj.extensionProperty: Int
    get() = value + 5
fun Obj.extensionFunc() = value + 5
