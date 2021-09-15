package com.github.hotire.mock.kotlin.test

import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.util.Arrays
import java.util.stream.Collectors
import kotlin.reflect.KClass

data class MockHelper(val mockMap: MutableMap<KClass<*>, Any> = mutableMapOf()) {
    fun clear() = mockMap.clear()

    inline fun <reified T : Any> createInstanceWithMock(): T {
        return mockMap[T::class]?.let { it as T } ?: run {
            val instance = createInstance(T::class)
            getAllAutowiredFields(
                mutableListOf(),
                T::class.java
            ).forEach {
                val fieldInstance = getMockClass(it.type::class)
                it.isAccessible = true
                try {
                    it[instance] = fieldInstance
                } catch (e: IllegalAccessException) {
                    throw RuntimeException(e)
                }
            }
            mockMap[T::class] = instance
            instance
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> createInstance(type: KClass<T>): T {
        return try {
            val candidates = type.java.declaredConstructors
            for (candidate in candidates) {
                return candidate.newInstance(
                    *Arrays.stream(candidate.parameterTypes)
                        .map { getMockClass(it.kotlin) }
                        .toArray()
                ) as T
            }
            Mockito.mock(type.java)
        } catch (e: InstantiationException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        } catch (e: InvocationTargetException) {
            throw RuntimeException(e)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getMockClass(type: KClass<T>): T {
        return mockMap[type]?.let { it as T } ?: run {
            mockMap[type] = Mockito.mock(type.java)
            mockMap[type] as T
        }
    }

    inline fun <reified T : Any> getMock(): T {
        return mockMap[T::class]?.let { it as T } ?: run {
            mockMap[T::class] = Mockito.mock(T::class.java)
            mockMap[T::class] as T
        }
    }

    fun getAllAutowiredFields(fields: MutableList<Field>, type: Class<*>): List<Field> {
        fields.addAll(
            Arrays.stream(type.declaredFields)
                .filter { field: Field ->
                    field.isAnnotationPresent(
                        Autowired::class.java
                    )
                }
                .collect(Collectors.toList())
        )
        if (type.superclass != null) {
            getAllAutowiredFields(fields, type.superclass)
        }
        return fields
    }
}
