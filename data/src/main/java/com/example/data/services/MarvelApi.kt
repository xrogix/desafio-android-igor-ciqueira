package com.example.data.services

import com.example.domain.Characters
import com.example.domain.Comics
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    /**
     * Find all marvel characters
     * @param ts time stamp to hash auth
     * @param apikey generated by developer.marvel.com
     * @param hash Mds combination auth
     */
    @GET("v1/public/characters")
    fun listCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ) : Call<List<Characters>>

    /**
     * Find all comics by id from characters
     * @param characterId Id from character
     */
    @GET("v1/public/characters/{characterId}/comics")
    fun listComics(
        @Path("characterId") characterId: String
    ): Call<List<Comics>>
}