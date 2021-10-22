package com.github.hotire.mock.kotlin.extension

import io.mockk.every
import io.mockk.mockkStatic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ObjTest {

    @Test
    fun extensionProperty() {
        // given
        val obj = Obj(4)
        val expected = 3
        mockkStatic(Obj::extensionProperty)

        every { obj.extensionProperty } returns expected
        
        // when
        val result = obj.extensionProperty

        // then
        assertThat(result).isEqualTo(expected)
    }
}
