package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object DoorBlockModel : ContentGenerator("Door Block Model", "models/block", GeneratorInfo.DOORS) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(Model(
            parent = Identifier.mc("block/door_bottom"),
            textures = mapOf(
                "bottom" to id.wrapPath("block/", "_door_bottom"),
                "top" to id.wrapPath("block/", "_door_top")
            )
        ), "door_bottom"),
        Suffixed(Model(
            parent = Identifier.mc("block/door_bottom_rh"),
            textures = mapOf(
                "bottom" to id.wrapPath("block/", "_door_bottom"),
                "top" to id.wrapPath("block/", "_door_top")
            )
        ), "door_bottom_hinge"),
        Suffixed(Model(
            parent = Identifier.mc("block/door_top"),
            textures = mapOf(
                "bottom" to id.wrapPath("block/", "_door_bottom"),
                "top" to id.wrapPath("block/", "_door_top")
            )
        ), "door_top"),
        Suffixed(Model(
            parent = Identifier.mc("block/door_top_rh"),
            textures = mapOf(
                "bottom" to id.wrapPath("block/", "_door_bottom"),
                "top" to id.wrapPath("block/", "_door_top")
            )
        ), "door_top_hinge")
    )
}
