package dev.carlodips.itunesmusicsearch.data.remote.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

data class SearchResult(
    val trackName: String,
    val artistName: String,
    @SerializedName("artworkUrl100")
    val artworkThumbnail: String,
    val trackPrice: Int,
    val currency: String,

    val collectionName: String,
    val trackTimeMillis: Long,
    val releaseDate: String,
    @SerializedName("primaryGenreName")
    val genre: String,
    val trackExplicitness: String,

    val artistViewUrl: String,
    val trackViewUrl: String,
    val collectionViewUrl: String
): Parcelable {

    fun getDisplayPrice(): String {
        return "$currency ${String.format("%.2f", trackPrice.toDouble())}"
    }

    fun getFormattedTrackTime(): String {
        val millis = trackTimeMillis
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))

        return if (seconds < 10)
            String.format("%d:0%d", minutes, seconds)
        else
            String.format("%d:%d", minutes, seconds)
    }

    fun getDisplayReleaseDate(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val outputFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
        val date: Date = inputFormat.parse("2018-04-10T04:00:00.000Z")!!
        return outputFormat.format(date)
    }

    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readInt(),
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readLong(),
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(trackName)
        parcel.writeString(artistName)
        parcel.writeString(artworkThumbnail)
        parcel.writeInt(trackPrice)
        parcel.writeString(currency)
        parcel.writeString(collectionName)
        parcel.writeLong(trackTimeMillis)
        parcel.writeString(releaseDate)
        parcel.writeString(genre)
        parcel.writeString(trackExplicitness)
        parcel.writeString(artistViewUrl)
        parcel.writeString(trackViewUrl)
        parcel.writeString(collectionViewUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchResult> {
        override fun createFromParcel(parcel: Parcel): SearchResult {
            return SearchResult(parcel)
        }

        override fun newArray(size: Int): Array<SearchResult?> {
            return arrayOfNulls(size)
        }
    }
}