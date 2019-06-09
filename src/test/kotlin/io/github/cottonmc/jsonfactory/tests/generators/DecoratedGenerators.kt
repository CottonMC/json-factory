package io.github.cottonmc.jsonfactory.tests.generators

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.DecoratedContentGenerator
import io.github.cottonmc.jsonfactory.gens.Gens
import io.github.cottonmc.jsonfactory.output.Output
import io.github.cottonmc.jsonfactory.output.prefixed
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expectThat
import strikt.assertions.all

object DecoratedGenerators : Spek({
    Feature("Decorated generators") {
        Scenario("decorating a block model with a prefix") {
            val id = Identifier("minecraft", "gravel")
            lateinit var outputs: List<Output>

            When("generating") {
                outputs = DecoratedContentGenerator(Gens.BASIC_BLOCK_MODEL) { _, outputs ->
                    outputs.map { it.prefixed("super") }
                }.generate(id)
            }

            Then("outputs should have the prefix") {
                expectThat(outputs)
                    .all {
                        assertThat("output should have the prefix") {
                            it.nameWrapper.prefix == "super"
                        }
                    }
            }
        }
    }
})
