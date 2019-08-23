package net.marksheehan.doomtowndeckbuilder

import androidx.test.platform.app.InstrumentationRegistry
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.datamodel.MoshiCardModel
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import net.marksheehan.doomtowndeckbuilder.internetdatabase.ParseCardListFromJsonFile
import org.junit.Assert


@RunWith(RobolectricTestRunner::class)
class TestClass {

//    val context = ApplicationProvider.getApplicationContext<Context>()

    interface CardQueryCallback {
        fun success(result: List<CardModel>?)
        fun failure()
    }

    internal inner class CardCallbackImplementation() : Callback<List<MoshiCardModel>> {

        override fun onFailure(call: Call<List<MoshiCardModel>>, t: Throwable) {
        }

        override fun onResponse(callResult: Call<List<MoshiCardModel>>, response: Response<List<MoshiCardModel>>) {
            val cardList = response.body()
            println("success")
        }
    }

    internal interface GetCardsService {
        @get:GET("cards")
        val fullCardList: Call<List<MoshiCardModel>>

        @get:GET("photo")
        val photoList: Call<List<MoshiCardModel>>
    }

    @Test
    fun gsonTesting() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val cardList = ParseCardListFromJsonFile.parseCardListFromAssetFile(context, "card_list.json")

        //There are more than 800 cards and this value can only increase.
        Assert.assertTrue(cardList.size > 800)
    }

    @Test
    fun moshiTesting() {

        val moshi = Moshi.Builder()
                // ... add your own JsonAdapters and factories ...
                .add(KotlinJsonAdapterFactory())
                .build()
        var context = InstrumentationRegistry.getInstrumentation().context

        var cards = ParseCardListFromJsonFile.parseCardsFromMoshi(context)

//        val okHttpClientBuilder = OkHttpClient.Builder()
//                .readTimeout(30, TimeUnit.SECONDS)
//                .writeTimeout(30, TimeUnit.SECONDS)
//
//        val retrofit = Retrofit.Builder()
//                .baseUrl("https://dtdb.co/api/")
//                .addConverterFactory(MoshiConverterFactory.create(moshi))
//                .client(okHttpClientBuilder.build())
//                .build()
//
//        retrofit.callbackExecutor()
//        val service = retrofit.create(GetCardsService::class.java)
//        val call = service.fullCardList
//
//        val cardCallback = CardCallbackImplementation()
//        call.enqueue(cardCallback)
//
//        while (true)
//        {
//        }
    }
}