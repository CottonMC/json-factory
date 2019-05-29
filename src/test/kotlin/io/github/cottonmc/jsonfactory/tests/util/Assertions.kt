package io.github.cottonmc.jsonfactory.tests.util

import arrow.core.Option
import arrow.core.Try
import arrow.core.orNull
import strikt.api.Assertion
import strikt.assertions.throws

fun <A> Assertion.Builder<Try<A>>.isSuccess() = assertThat("has successfully returned") { it.isSuccess() }
fun <A> Assertion.Builder<Try<A>>.isFailure() = get { failed().orNull() }.throws<Throwable>()

fun <A> Assertion.Builder<Option<A>>.isSome() = assertThat("is present") { it.isDefined() }
fun <A> Assertion.Builder<Option<A>>.isNone() = assertThat("is not present") { it.isEmpty() }
