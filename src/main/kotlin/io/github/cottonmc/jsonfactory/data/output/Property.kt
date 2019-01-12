package io.github.cottonmc.jsonfactory.data.output

import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import kotlin.reflect.KProperty

data class Property<out T>(val name: String, val value: T, val mode: Mode = Mode.Default) {
    enum class Mode {
        Default, RemoveIfEmpty
    }

    class Builder internal constructor() {
        private val list = ArrayList<Property<*>>()

        operator fun KProperty<*>.unaryPlus() {
            list += Property(name, getter.call())
        }

        operator fun Property<*>.unaryPlus() {
            list += this
        }

        fun <T> KProperty<T>.removeIfEmpty() = Property(
            name,
            getter.call(),
            Mode.RemoveIfEmpty
        )

        fun build(): List<Property<*>> = list
    }

    companion object : JsonSerializer<Json.ByProperties> {
        override fun serialize(
            src: Json.ByProperties,
            typeOfSrc: Type?,
            context: JsonSerializationContext
        ) = JsonObject().apply {
            for ((name, value, mode) in src.properties) {
                when (mode) {
                    Mode.RemoveIfEmpty ->
                        if ((value as? Collection<*>)?.isNotEmpty() == true)
                            add(name, context.serialize(value))

                    else -> add(name, context.serialize(value))
                }
            }
        }

        fun createList(block: Builder.() -> Unit) =
            Builder().apply(block).build()
    }
}
