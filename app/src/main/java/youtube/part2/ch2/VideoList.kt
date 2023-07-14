package youtube.part2.ch2

import com.google.gson.annotations.SerializedName

data class VideoList(
    @SerializedName("videos")
    val videos: List<VideoEntity>
)

data class VideoEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("sources")
    val videoUrl: String,
    @SerializedName("channelName")
    val channelName: String,
    @SerializedName("viewCount")
    val viewCount: String,
    @SerializedName("dateText")
    val dateText: String,
    @SerializedName("channelThumb")
    val channelThumb: String,
    @SerializedName("thumb")
    val videoThumb: String,
)
