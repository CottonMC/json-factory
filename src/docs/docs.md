# Module json-factory
A tool for generating Minecraft assets and data.

# Package io.github.cottonmc.jsonfactory
Contains the main logic.

# Package io.github.cottonmc.jsonfactory.data
Various data types

# Package io.github.cottonmc.jsonfactory.output
Output types

# Package io.github.cottonmc.jsonfactory.output.model
Model output

# Package io.github.cottonmc.jsonfactory.output.loot
Loot table output

# Package io.github.cottonmc.jsonfactory.util
Various utils

# Package io.github.cottonmc.jsonfactory.gens.block
Block content generators

# Package io.github.cottonmc.jsonfactory.gens.item
Item content generators

# Package io.github.cottonmc.jsonfactory.gens
Contains the content generators.

## Content generators
The content generation pipeline goes from an
[Identifier][io.github.cottonmc.jsonfactory.data.Identifier] to
a [ContentGenerator][io.github.cottonmc.jsonfactory.gens.ContentGenerator].
The resulting [Output][io.github.cottonmc.jsonfactory.output.Output]
can be written into a file.
