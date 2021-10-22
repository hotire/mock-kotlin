package com.github.hotire.mock.kotlin.ex

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class CarTest {

    @Test
    fun drive() {
        // given
        val car = mockk<Car>()
        val expected = "OK"

        // when
        every { car.drive(Direction.NORTH) } returns expected
        val result = car.drive(Direction.NORTH)

        // then
        verify { car.drive(Direction.NORTH) }
        assertThat(result).isEqualTo(expected)
        confirmVerified(car)
    }

    @Test
    fun driveWithMockitoKotlin() {
        // given
        val car = mock<Car>()
        val expected = "OK"

        // when
        whenever(car.drive(Direction.NORTH)).thenReturn(expected)
        val result = car.drive(Direction.NORTH)
        assertThat(result).isEqualTo(expected)
    }
}
