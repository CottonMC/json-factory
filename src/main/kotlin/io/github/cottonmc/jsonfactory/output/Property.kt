package io.github.cottonmc.jsonfactory.output

import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import kotlin.reflect.KProperty

/**
 * An output property.
 *
 * @property name the name
 * @property value the value
 * @property mode the serialization mode
 */
data class Property<out T>(val name: String, val value: T, val mode: Mode = Mode.Default) {
    /**
     * The serialization modes.
     */
    enum class Mode {
        /**
         * The default.
         */
        Default,

        /**
         * The property is removed if its [value] is empty.
         */
        RemoveIfEmpty,

        /**
         * The property is removed if its [value] is null.
         */
        RemoveIfNull,

        /**
         * The property is removed if its [value] is true.
         * @since 0.4.0
         */
        RemoveIfTrue
    }

    class Builder internal constructor() {
        private val list = ArrayList<Property<*>>()

        operator fun KProperty<*>.unaryPlus() {
            list += Property(name, getter.call())
        }

        operator fun Property<*>.unaryPlus() {
            list += this
        }

        @JvmName("stringRemoveIfEmpty")
        fun KProperty<String>.removeIfEmpty() = Property(
            name,
            getter.call(),
            Mode.RemoveIfEmpty
        )

        @JvmName("collectionRemoveIfEmpty")
        fun <T> KProperty<Collection<T>>.removeIfEmpty() = Property(
            name,
            getter.call(),
            Mode.RemoveIfEmpty
        )

        @JvmName("mapRemoveIfEmpty")
        fun <K, V> KProperty<Map<K, V>>.removeIfEmpty() = Property(
            name,
            getter.call(),
            Mode.RemoveIfEmpty
        )

        fun <T : Any> KProperty<T?>.removeIfNull() = Property(
            name,
            getter.call(),
            Mode.RemoveIfNull
        )

        fun KProperty<Boolean?>.removeIfTrue() = Property(
            name,
            getter.call(),
            Mode.RemoveIfTrue
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
                        if (!isEmpty(value))
                            add(name, context.serialize(value))

                    Mode.RemoveIfNull ->
                        if (value != null)
                            add(name, context.serialize(value))

                    Mode.RemoveIfTrue ->
                        if (value != true)
                            add(name, context.serialize(value))

                    else -> add(name, context.serialize(value))
                }
            }
        }

        private fun isEmpty(value: Any?) =
            (value as? Collection<*>)?.isEmpty() == true || (value as? String)?.isEmpty() == true ||
                    (value as? Map<*, *>)?.isEmpty() == true
    }
}

fun <T : Any> T.createProperties(block: Property.Builder.(T) -> Unit) =
    Property.Builder().apply { block(this@createProperties) }.build()

fun createProperties(block: Property.Builder.() -> Unit) =
    Property.Builder().apply(block).build()
