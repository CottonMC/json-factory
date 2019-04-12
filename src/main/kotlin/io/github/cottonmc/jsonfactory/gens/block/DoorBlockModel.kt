package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object DoorBlockModel : ContentGenerator("Door Block Model", "models/block", GeneratorInfo.DOORS) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/door_bottom"),
            textures = mapOf(
                "bottom" to id.wrapPath("block/", "_door_bottom"),
                "top" to id.wrapPath("block/", "_door_top")
            )
        ).suffixed("door_bottom"),
        Model(
            parent = Identifier.mc("block/door_bottom_rh"),
            textures = mapOf(
                "bottom" to id.wrapPath("block/", "_door_bottom"),
                "top" to id.wrapPath("block/", "_door_top")
            )
        ).suffixed("door_bottom_hinge"),
        Model(
            parent = Identifier.mc("block/door_top"),
            textures = mapOf(
                "bottom" to id.wrapPath("block/", "_door_bottom"),
                "top" to id.wrapPath("block/", "_door_top")
            )
        ).suffixed("door_top"),
        Model(
            parent = Identifier.mc("block/door_top_rh"),
            textures = mapOf(
                "bottom" to id.wrapPath("block/", "_door_bottom"),
                "top" to id.wrapPath("block/", "_door_top")
            )
        ).suffixed("door_top_hinge")
    )
}
