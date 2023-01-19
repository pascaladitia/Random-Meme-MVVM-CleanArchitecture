package com.pascal.randommeme.domain.usecase

import com.pascal.randommeme.domain.model.ResponseMeme
import com.pascal.randommeme.domain.repo.RemoteRepository
import com.pascal.randommeme.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMemeUseCase @Inject constructor(
    private val repo: RemoteRepository
): SingleUseCase<ResponseMeme>() {

    override fun buildUseCaseSingle(): Single<ResponseMeme> {
        return repo.getMeme()
    }

}