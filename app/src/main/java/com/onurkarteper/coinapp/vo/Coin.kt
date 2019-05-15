package com.onurkarteper.coinapp.vo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


@Suppress("unused")
data class Coin(
    @SerializedName("first_historical_data")
    val firstHistoricalData: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_active")
    val isActive: Int?,
    @SerializedName("last_historical_data")
    val lastHistoricalData: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("urls")
    val coinUrls: CoinUrls
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readParcelable<CoinUrls>(CoinUrls::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(firstHistoricalData)
        writeValue(id)
        writeValue(isActive)
        writeString(lastHistoricalData)
        writeString(name)
        writeString(slug)
        writeString(symbol)
        writeString(description)
        writeString(logo)
        writeParcelable(coinUrls, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Coin> = object : Parcelable.Creator<Coin> {
            override fun createFromParcel(source: Parcel): Coin = Coin(source)
            override fun newArray(size: Int): Array<Coin?> = arrayOfNulls(size)
        }
    }



}
