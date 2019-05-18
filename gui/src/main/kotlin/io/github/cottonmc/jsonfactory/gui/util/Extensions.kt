package io.github.cottonmc.jsonfactory.gui.util

inline fun <A> ((A) -> A)?.maybeInvoke(a: A): A = this?.invoke(a) ?: a
