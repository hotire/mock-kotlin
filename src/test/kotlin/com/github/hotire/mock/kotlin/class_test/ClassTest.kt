package com.github.hotire.mock.kotlin.class_test

import io.mockk.mockk
import org.junit.jupiter.api.Test

open class OpenClass
class FinalClass
abstract class AbstractClass
interface Interface

internal class ClassTest {
    @Test
    fun `mock instance class`() {
        val o: OpenClass = mockk()
        val f: FinalClass = mockk()
        val a: AbstractClass = mockk()
        val i: Interface = mockk()

        println(o::class)
        println(f::class)
        println(a::class)
        println(i::class)
    }
}
