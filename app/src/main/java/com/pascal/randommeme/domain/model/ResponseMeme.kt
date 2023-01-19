package com.pascal.randommeme.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseMeme(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("memes")
	val memes: List<MemesItem?>? = null
) : Parcelable

@Parcelize
data class MemesItem(

	@field:SerializedName("preview")
	val preview: List<String?>? = null,

	@field:SerializedName("postLink")
	val postLink: String? = null,

	@field:SerializedName("nsfw")
	val nsfw: Boolean? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("ups")
	val ups: Int? = null,

	@field:SerializedName("spoiler")
	val spoiler: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("subreddit")
	val subreddit: String? = null,

	@field:SerializedName("url")
	val url: String? = null
) : Parcelable
