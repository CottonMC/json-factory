package io.github.cottonmc.jsonfactory.tests

import arrow.core.Option
import arrow.core.Try
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.tests.util.isFailure
import io.github.cottonmc.jsonfactory.tests.util.isNone
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expectThat
import strikt.assertions.isEqualTo

object IdentifierFeature : Spek({
    val base = Identifier("json_factory", "test")

    Feature("Identifiers") {
        Scenario("prefixing the path") {
            lateinit var prefixed: Identifier

            When("a prefix is added to the path") {
                prefixed = base.prefixPath("my_prefix/")
            }

            Then("the new identifier should have the prefix") {
                expectThat(prefixed)
                    .isEqualTo(Identifier("json_factory", "my_prefix/test"))
            }
        }

        Scenario("suffixing the path") {
            lateinit var suffixed: Identifier

            When("a suffix is added to the path") {
                suffixed = base.suffixPath("/my_suffix")
            }

            Then("the new identifier should have the suffixed") {
                expectThat(suffixed)
                    .isEqualTo(Identifier("json_factory", "test/my_suffix"))
            }
        }

        Scenario("wrapping the path with a prefix and a suffix") {
            lateinit var wrapped: Identifier

            When("the path is wrapped") {
                wrapped = base.wrapPath("my_prefix/", "/my_suffix")
            }

            Then("the path should be wrapped") {
                expectThat(wrapped)
                    .isEqualTo(Identifier("json_factory", "my_prefix/test/my_suffix"))
                    .assertThat("it should be equal to the result of prefixing and suffixing") {
                        it == base.prefixPath("my_prefix/").suffixPath("/my_suffix")
                    }
            }
        }

        Scenario("identifiers in the minecraft namespace") {
            lateinit var id: Identifier

            When("a minecraft identifier is created") {
                id = Identifier.mc("test")
            }

            Then("it should be equal to a manually created identifier with the same properties") {
                expectThat(id)
                    .assertThat("it has the minecraft namespace") { it.namespace == "minecraft" }
                    .assertThat("it has the correct path") { it.path == "test" }
            }
        }

        Scenario("parsing an identifier") {
            lateinit var input: String

            Given("an input string") {
                input = "json_factory:test_id"
            }

            lateinit var parsed: Identifier

            When("it is parsed") {
                parsed = Identifier(input)
            }

            Then("it should be have the correct properties") {
                expectThat(parsed)
                    .isEqualTo(Identifier("json_factory", "test_id"))
            }
        }

        Scenario("parsing an invalid identifier") {
            lateinit var input: String

            Given("an invalid input string") {
                input = "no colons here"
            }

            lateinit var parsed: Try<Identifier>

            When("it is parsed") {
                parsed = Try { Identifier(input) }
            }

            Then("it should be have thrown an exception") {
                expectThat(parsed).isFailure()
            }
        }

        Scenario("parsing an invalid identifier with null fallback") {
            lateinit var input: String

            Given("an invalid input string") {
                input = "no colons here"
            }

            // No nullable lateinits :(
            lateinit var parsed: Option<Identifier>

            When("it is parsed") {
                parsed = Option.fromNullable(Identifier.orNull(input))
            }

            Then("the result should be null") {
                expectThat(parsed).isNone()
            }
        }
    }
})
