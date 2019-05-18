package io.github.cottonmc.jsonfactory.tests.util

import com.google.gson.JsonObject
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import org.spekframework.spek2.style.gherkin.FeatureBody
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.io.ByteArrayOutputStream

@Suppress("TestFunctionName")
fun FeatureBody.JsonScenario(description: String, id: Identifier, expectedPath: String, generator: ContentGenerator) =
    Scenario(description) {
        val expected = JsonLoader.loadJson(expectedPath)
        lateinit var result: JsonObject

        When("generating") {
            val stream = ByteArrayOutputStream()
            generator.generate(id).first().writeToStream(stream)
            result = JsonLoader.GSON.fromJson(stream.toString("UTF-8"), JsonObject::class.java)
        }

        Then("result should be equal to expected") {
            expectThat(result).isEqualTo(expected)
        }
    }
