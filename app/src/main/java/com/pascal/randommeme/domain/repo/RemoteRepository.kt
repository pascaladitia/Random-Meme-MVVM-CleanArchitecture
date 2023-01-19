package com.pascal.randommeme.domain.repo

import com.pascal.randommeme.domain.model.ResponseMeme
import io.reactivex.rxjava3.core.Single

interface RemoteRepository {
    fun getMeme(): Single<ResponseMeme>
}