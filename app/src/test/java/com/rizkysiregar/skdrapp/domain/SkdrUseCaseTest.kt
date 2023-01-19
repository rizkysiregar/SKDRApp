package com.rizkysiregar.skdrapp.domain

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity
import com.rizkysiregar.skdrapp.core.domain.repository.ISkdrRepository
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrInteractor
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SkdrUseCaseTest {
    private lateinit var skdrUseCase: SkdrUseCase

    @Mock private lateinit var skdrRepository: ISkdrRepository

}