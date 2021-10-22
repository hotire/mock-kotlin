package com.github.hotire.mock.kotlin.ex

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

internal class CarTest {

    @Test
    fun drive() {
        // given
        val car = mockk<Car>()
        val expected = "OK"

        every { car.drive(Direction.NORTH) } returns expected

        // when
        val result = car.drive(Direction.NORTH)
        car.toString()
        // then
        verify(exactly = 1) {
            car.drive(Direction.NORTH)
//            car.toString()
        }
        assertThat(result).isEqualTo(expected)
        confirmVerified(car)
    }

    @Test
    fun driveWithMockitoKotlin() {
        // given
        val car = mock<Car>()
        val expected = "OK"
        whenever(car.drive(Direction.NORTH)).thenReturn(expected)
//        car.stop()
        // when
        val result = car.drive(Direction.NORTH)

        // then 
        assertThat(result).isEqualTo(expected)
        org.mockito.kotlin.verify(car, times(1)).drive(Direction.NORTH)
        verifyNoMoreInteractions(car)
    }
}
