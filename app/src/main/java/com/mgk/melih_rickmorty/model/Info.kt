package com.mgk.melih_rickmorty.model

data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: Any? = null
)