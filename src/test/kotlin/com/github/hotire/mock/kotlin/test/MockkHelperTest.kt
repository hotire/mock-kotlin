package com.github.hotire.mock.kotlin.test

import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test

class MockkHelperTest {

    class CarService(val carRepository: CarRepository) {
        fun active() = carRepository.active()
    }

    interface CarRepository {
        fun active(): Boolean
    }

    @Test
    fun driveWithMockitoKotlin() {
        // given
        val mockkHelper = MockkHelper()
        val car = mockkHelper.createInstanceWithMock<CarService>()
        val carRepository = mockkHelper.getMock<CarRepository>()

        // when
        every { carRepository.active() } returns true
        car.active()

        // then
        verify(exactly = 1) {
            carRepository.active()
        }
    }
}
