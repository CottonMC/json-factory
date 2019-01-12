package io.github.cottonmc.jsonfactory.data.output

class Suffixed(delegate: Output, override val suffix: String) : Output by delegate
