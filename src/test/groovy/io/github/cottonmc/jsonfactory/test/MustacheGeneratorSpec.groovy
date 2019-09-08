package io.github.cottonmc.jsonfactory.test

import io.github.cottonmc.jsonfactory.generator.MustacheContentGenerator
import io.github.cottonmc.jsonfactory.util.Identifier
import org.cactoos.io.InputOf
import spock.lang.Specification

class MustacheGeneratorSpec extends Specification {
    def "using 'id' in templates"() {
        given:
        def generator = new MustacheContentGenerator(template)

        when:
        def result = generator.generate(id: new Identifier('minecraft:test'))

        then:
        result == expected

        where:
        template                                        | expected
        "{{id}}"                                        | "minecraft:test"
        '{ "variants": { "": { "model": "{{id}}" } } }' | '{ "variants": { "": { "model": "minecraft:test" } } }'
        '{{id.namespace}} {{id.path}}'                  | 'minecraft test'
    }

    def "loading templates from the classpath"() {
        given:
        def generator = new MustacheContentGenerator(new InputOf(this.class.getResource("/json-factory/test_1_input.json")))
        def expected = this.class.getResource("/json-factory/test_1_output.json").readLines().join("\n")

        when:
        def result = generator.generate(id: new Identifier('adorn:test'))

        then:
        result.trim() == expected
    }
}
