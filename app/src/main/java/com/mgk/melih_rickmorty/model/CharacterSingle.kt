package com.mgk.melih_rickmorty.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterSingle(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
):Parcelable

@Parcelize
data class Location(
    val name: String,
    val url: String
):Parcelable
