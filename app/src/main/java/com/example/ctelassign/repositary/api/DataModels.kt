package com.example.ctelassign.repositary.api

import android.content.ClipData.Item
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Icon(
    @SerializedName("prefix")
    var prefix: String? = null,
    @SerializedName("suffix")
    var suffix: String? = null
)

data class Venue(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("location")
    var location: Location? = null,

    @SerializedName("categories")
    var categories: List<Category>? = null
)

data class Category(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("pluralName")
    var pluralName: String? = null,

    @SerializedName("shortName")
    @Expose
    var shortName: String? = null,

    @SerializedName("icon")
    var icon: Icon? = null,

    @SerializedName("primary")
    var primary: Boolean? = null
)

class Location(
    @SerializedName("address")
    var address: String? = null,

    @SerializedName("crossStreet")
    var crossStreet: String? = null,

    @SerializedName("lat")
    var lat: Double? = null,

    @SerializedName("lng")
    var lng: Double? = null,

    @SerializedName("distance")
    var distance: Int? = null,

    @SerializedName("postalCode")
    var postalCode: String? = null,

    @SerializedName("cc")
    var cc: String? = null,

    @SerializedName("city")
    var city: String? = null,

    @SerializedName("state")
    var state: String? = null,

    @SerializedName("country")
    var country: String? = null,

    @SerializedName("formattedAddress")
    var formattedAddress: List<String>? = null
)
data class Response(
    @SerializedName("venues")
    @Expose
    var venues: List<Venue>?
)


data class ResponseData(
    @SerializedName("meta")
    var meta: Meta? = null,

    @SerializedName("notifications")
    var notifications: List<Notification>? = null,

    @SerializedName("response")
    var response: Response? = null
)


data class Meta(
    @SerializedName("code")
    var code: Int? = null,

    @SerializedName("requestId")
    var requestId: String? = null
)


data class Notification (
    @SerializedName("type")
    var type: String? = null,

    @SerializedName("item")
    var item: Item? = null
)