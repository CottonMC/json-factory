package io.github.cottonmc.jsonfactory.tests

import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.Gens
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import strikt.api.expectThat
import strikt.assertions.containsExactlyInAnyOrder

object GensTest : Spek({
    describe("Gens") {
        describe("ALL_GENS") {
            it("should not have duplicate values") {
                expectThat(Gens.ALL_GENS)
                    .containsExactlyInAnyOrder(Gens.ALL_GENS.distinct())
            }

            it("should not have duplicate IDs") {
                expectThat(Gens.ALL_GENS.map(ContentGenerator::id))
                    .containsExactlyInAnyOrder(Gens.ALL_GENS.map(ContentGenerator::id).distinct())
            }
        }
    }
})
