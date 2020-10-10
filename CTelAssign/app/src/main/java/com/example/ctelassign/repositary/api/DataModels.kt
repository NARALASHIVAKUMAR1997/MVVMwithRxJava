package com.example.ctelassign.repositary.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("prefix")
    @Expose
    var prefix: String? = null,
    @SerializedName("suffix")
    @Expose
    var suffix: String? = null
)

data class Venue(
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("location")
    @Expose
    var location: Location? = null,

    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null
)

data class Category(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("pluralName")
    @Expose
    var pluralName: String? = null,

    @SerializedName("shortName")
    @Expose
    var shortName: String? = null,

    @SerializedName("icon")
    @Expose
    var icon: Icon? = null,

    @SerializedName("primary")
    @Expose
    var primary: Boolean? = null
)

class Location(
    @SerializedName("address")
    @Expose
    var address: String? = null,

    @SerializedName("crossStreet")
    @Expose
    var crossStreet: String? = null,

    @SerializedName("lat")
    @Expose
    var lat: Double? = null,

    @SerializedName("lng")
    @Expose
    var lng: Double? = null,

    @SerializedName("distance")
    @Expose
    var distance: Int? = null,

    @SerializedName("postalCode")
    @Expose
    var postalCode: String? = null,

    @SerializedName("cc")
    @Expose
    var cc: String? = null,

    @SerializedName("city")
    @Expose
    var city: String? = null,

    @SerializedName("state")
    @Expose
    var state: String? = null,

    @SerializedName("country")
    @Expose
    var country: String? = null,

    @SerializedName("formattedAddress")
    @Expose
    var formattedAddress: List<String>? = null
)


data class ResponseData(
    @SerializedName("response")
    @Expose
    var response: Response?
)


data class Response(
    @SerializedName("venues")
    @Expose
    var venues: List<Venue>?
)
