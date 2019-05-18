package io.github.cottonmc.jsonfactory.tests.gens

import com.google.gson.JsonObject
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.Gens
import io.github.cottonmc.jsonfactory.tests.JsonLoadingSpek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.io.ByteArrayOutputStream

object BasicBlockGenerators : JsonLoadingSpek({
    Feature("Basic Block") {
        val id = Identifier("minecraft", "dirt")

        Scenario("generating the block model") {
            val expected = loadJson("expected/basic_block_model.json")
            lateinit var result: JsonObject

            When("generating") {
                val stream = ByteArrayOutputStream()
                Gens.basicBlockModel.generate(id).first().writeToStream(stream)
                result = GSON.fromJson(stream.toString("UTF-8"), JsonObject::class.java)
            }

            Then("result should be equal to expected") {
                expectThat(result).isEqualTo(expected)
            }
        }

        Scenario("generating the block state") {
            val expected = loadJson("expected/basic_block_state.json")
            lateinit var result: JsonObject

            When("generating") {
                val stream = ByteArrayOutputStream()
                Gens.basicBlockState.generate(id).first().writeToStream(stream)
                result = GSON.fromJson(stream.toString("UTF-8"), JsonObject::class.java)
            }

            Then("result should be equal to expected") {
                expectThat(result).isEqualTo(expected)
            }
        }

        Scenario("generating the block item model") {
            val expected = loadJson("expected/basic_block_item_model.json")
            lateinit var result: JsonObject

            When("generating") {
                val stream = ByteArrayOutputStream()
                Gens.basicBlockItemModel.generate(id).first().writeToStream(stream)
                result = GSON.fromJson(stream.toString("UTF-8"), JsonObject::class.java)
            }

            Then("result should be equal to expected") {
                expectThat(result).isEqualTo(expected)
            }
        }

        Scenario("generating the loot table") {
            val expected = loadJson("expected/basic_loot_table.json")
            lateinit var result: JsonObject

            When("generating") {
                val stream = ByteArrayOutputStream()
                Gens.basicLootTable.generate(id).first().writeToStream(stream)
                result = GSON.fromJson(stream.toString("UTF-8"), JsonObject::class.java)
            }

            Then("result should be equal to expected") {
                expectThat(result).isEqualTo(expected)
            }
        }
    }
})
