package io.github.cottonmc.jsonfactory.tests.util

import arrow.core.Option
import arrow.core.Try
import strikt.api.Assertion

fun <A> Assertion.Builder<Try<A>>.isSuccess() = assertThat("has successfully returned") { it.isSuccess() }
fun <A> Assertion.Builder<Try<A>>.isFailure() = assertThat("has thrown an exception") { it.isFailure() }

fun <A> Assertion.Builder<Option<A>>.isSome() = assertThat("is present") { it.isDefined() }
fun <A> Assertion.Builder<Option<A>>.isNone() = assertThat("is not present") { it.isEmpty() }
