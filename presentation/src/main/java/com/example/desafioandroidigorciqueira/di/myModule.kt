package com.example.desafioandroidigorciqueira.di

import com.example.data.environment.Environment
import com.example.data.factory.ServiceFactory
import com.example.data.repository.CharactersRepositoryImpl
import com.example.data.services.ImageProvider
import com.example.data.services.ServiceProvider
import com.example.data.services.ServiceProviderImpl
import com.example.desafioandroidigorciqueira.viewmodel.MainViewModel
import com.example.domain.repository.CharactersRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {

    //TODO ajustar variaveis de ambiente
    single { Environment("https://gateway.marvel.com/", "d4d20d6d2c25b8600d060ae71cd4eeb6", "b7ccdff3893996f147026f7ee11ae762a59342a1") }

    single { ImageProvider(get()) }

    single { ServiceFactory(get()) }

    single<ServiceProvider> { ServiceProviderImpl(get()) }

    single<CharactersRepository> { CharactersRepositoryImpl(get(), get()) }

    viewModel { MainViewModel(get()) }

}