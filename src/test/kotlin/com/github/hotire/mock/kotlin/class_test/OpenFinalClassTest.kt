package com.github.hotire.mock.kotlin.class_test

import io.mockk.mockk
import org.junit.jupiter.api.Test

/**
 * http://www.javadecompilers.com/result
 */
internal class OpenFinalClassTest {
    @Test
    fun `class transform`() {
        System.setProperty("io.mockk.classdump.path", "build/classfiles")
        mockk<MySub>()
    }
}
