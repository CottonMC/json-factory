package io.github.cottonmc.jsonfactory.tests

import io.github.cottonmc.jsonfactory.data.Identifier
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

class IdentifierTests {
    private val base = Identifier("json_factory", "test")

    @Test
    fun prefixPath() {
        expectThat(base.prefixPath("my_prefix/"))
            .isEqualTo(Identifier("json_factory", "my_prefix/test"))
    }

    @Test
    fun suffixPath() {
        expectThat(base.suffixPath("/my_suffix"))
            .isEqualTo(Identifier("json_factory", "test/my_suffix"))
    }

    @Test
    fun wrapPath() {
        expectThat(base.wrapPath("my_prefix/", "/my_suffix"))
            .isEqualTo(Identifier("json_factory", "my_prefix/test/my_suffix"))
    }

    @Test
    fun `wrapPath equals prefixPath + suffixPath`() {
        expectThat(base.wrapPath("my_prefix/", "/my_suffix"))
            .isEqualTo(base.prefixPath("my_prefix/").suffixPath("/my_suffix"))
    }

    @Test
    fun minecraft() {
        expectThat(Identifier.mc("example"))
            .isEqualTo(Identifier("minecraft", "example"))
    }

    @Test
    fun `Identifier from combined parts`() {
        expectThat(Identifier("json_factory:test"))
            .isEqualTo(base)
    }

    @Test
    fun `invalid Identifier from combined parts`() {
        expectThat(Identifier.orNull("invalid identifier =D"))
            .isNull()
    }
}
