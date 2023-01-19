package com.pascal.randommeme.data.repo

import com.pascal.randommeme.data.api.ApiService
import com.pascal.randommeme.domain.model.ResponseMeme
import com.pascal.randommeme.domain.repo.RemoteRepository
import io.reactivex.rxjava3.core.Single

class RepositoryRemoteImp(
    private val apiService: ApiService
): RemoteRepository {

    override fun getMeme(): Single<ResponseMeme> = apiService.getMeme()
}