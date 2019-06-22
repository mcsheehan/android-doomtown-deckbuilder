package net.marksheehan.doomtowndeckbuilder

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class DoomtownDbAccess {
    private val doomtownRestApi: Retrofit

    private fun createDoomtownRestApi(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://dtdb.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    init {
        doomtownRestApi = createDoomtownRestApi()
    }

    internal interface GetCardsService {
        @get:GET("cards")
        val fullCardList: Call<List<CardModel>>

        @get:GET("photo")
        val photoList: Call<List<CardModel>>
    }

    interface CardQueryCallback {
        fun success(result: List<CardModel>?)
        fun failure()
    }

    fun sendServerRequestCardList(cardQueryCallback: CardQueryCallback) {
        val service = doomtownRestApi.create(GetCardsService::class.java)
        val call = service.fullCardList
        val cardCallback = CardCallbackImplementation(cardQueryCallback)
        call.enqueue(cardCallback)
    }

    internal inner class CardCallbackImplementation(var cardCallback: CardQueryCallback) : Callback<List<CardModel>> {

        override fun onFailure(call: Call<List<CardModel>>, t: Throwable) {
            cardCallback.failure()
        }

        override fun onResponse(callResult: Call<List<CardModel>>, response: Response<List<CardModel>>) {
            val cardList = response.body()
            cardCallback.success(cardList)
        }
    }
}