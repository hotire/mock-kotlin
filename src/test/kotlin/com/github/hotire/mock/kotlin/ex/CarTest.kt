package com.github.hotire.mock.kotlin.ex

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class CarTest {

    @Test
    fun drive() {
        // given
        val car = mockk<Car>()

        // when
        every { car.drive(Direction.NORTH) } returns "OK"
        val result = car.drive(Direction.NORTH)

        // then
        verify { car.drive(Direction.NORTH) }
        Assertions.assertThat(result).isEqualTo("OK")
        confirmVerified(car)
    }

    @Test
    fun driveWithMockitoKotlin() {
        // given
        val car = mock<Car>()

        // when
        whenever(car.drive(Direction.NORTH)).thenReturn("OK")
        val result = car.drive(Direction.NORTH)
        Assertions.assertThat(result).isEqualTo("OK")
    }
}
