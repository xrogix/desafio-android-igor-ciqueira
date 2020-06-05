package com.example.desafioandroidigorciqueira.di

import com.example.data.environment.EnvironmentProvider
import com.example.data.environment.EnvironmentProviderImpl
import com.example.data.factory.ServiceFactory
import com.example.data.repository.CharactersRepositoryImpl
import com.example.data.repository.ComicsRepositoryImpl
import com.example.data.services.ImageProvider
import com.example.data.services.ServiceProvider
import com.example.data.services.ServiceProviderImpl
import com.example.desafioandroidigorciqueira.viewmodel.MainViewModel
import com.example.domain.repository.CharactersRepository
import com.example.domain.repository.ComicsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {

    single<EnvironmentProvider> { EnvironmentProviderImpl(get()) }

    single { ImageProvider(get()) }

    single { ServiceFactory(get()) }

    single<ServiceProvider> { ServiceProviderImpl(get()) }

    single<CharactersRepository> { CharactersRepositoryImpl(get(), get()) }

    single<ComicsRepository> { ComicsRepositoryImpl(get(), get()) }

    viewModel { MainViewModel(get(), get()) }

}