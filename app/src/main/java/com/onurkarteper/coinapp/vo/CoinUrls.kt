package com.onurkarteper.coinapp.vo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Suppress("SpellCheckingInspection", "unused")
data class CoinUrls(
    @SerializedName("website")
    val website: ArrayList<String>?,
    @SerializedName("twitter")
    val twitter: ArrayList<String>?,
    @SerializedName("reddit")
    val reddit: ArrayList<String>?,
    @SerializedName("message_board")
    val messageBoard: ArrayList<String>?,
    @SerializedName("announcement")
    val announcement: ArrayList<String>?,
    @SerializedName("chat")
    val chat: ArrayList<String>?,
    @SerializedName("explorer")
    val explorer: ArrayList<String>?,
    @SerializedName("source_code")
    val sourceCode: ArrayList<String>?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.createStringArrayList(),
        source.createStringArrayList(),
        source.createStringArrayList(),
        source.createStringArrayList(),
        source.createStringArrayList(),
        source.createStringArrayList(),
        source.createStringArrayList(),
        source.createStringArrayList()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeStringList(website)
        writeStringList(twitter)
        writeStringList(reddit)
        writeStringList(messageBoard)
        writeStringList(announcement)
        writeStringList(chat)
        writeStringList(explorer)
        writeStringList(sourceCode)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CoinUrls> = object : Parcelable.Creator<CoinUrls> {
            override fun createFromParcel(source: Parcel): CoinUrls = CoinUrls(source)
            override fun newArray(size: Int): Array<CoinUrls?> = arrayOfNulls(size)
        }
    }

    fun getAllUrls(): String {
        var result = ""
        result = result.plus(getUrlsFromArray(website))
        result = result.plus(getUrlsFromArray(twitter))
        result = result.plus(getUrlsFromArray(reddit))
        result = result.plus(getUrlsFromArray(messageBoard))
        result = result.plus(getUrlsFromArray(announcement))
        result = result.plus(getUrlsFromArray(chat))
        result = result.plus(getUrlsFromArray(explorer))
        result = result.plus(getUrlsFromArray(sourceCode))
        return result
    }

    private fun getUrlsFromArray(list: ArrayList<String>?): String {
        var urls = ""
        list?.let {
            for (url in it) {
                urls = urls.plus(url).plus("\n")
            }
        }
        return urls
    }
}