package com.pascal.randommeme.data.api

import com.pascal.randommeme.domain.model.ResponseMeme
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("gimme/20")
    fun getMeme(): Single<ResponseMeme>
}