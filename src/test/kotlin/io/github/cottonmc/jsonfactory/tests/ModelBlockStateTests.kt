package io.github.cottonmc.jsonfactory.tests

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.BlockStateProperty
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains

class ModelBlockStateTests {
    private val properties2 = listOf(
        BlockStateProperty("a", listOf("a1", "a2")),
        BlockStateProperty("b", listOf("b1", "b2"))
    )

    private val expected2 = setOf(
        "a=a1,b=b1", "a=a1,b=b2", "a=a2,b=b1", "a=a2,b=b2",
        "b=b1,a=a1", "b=b1,a=a2", "b=b2,a=a1", "b=b2,a=a2"
    )

    @Test
    fun twoPropertiesCreate() {
        val output = ModelBlockState.create(Identifier("test", "test"), properties2)

        expectThat(expected2)
            .contains(output.variants.keys)
    }
}
