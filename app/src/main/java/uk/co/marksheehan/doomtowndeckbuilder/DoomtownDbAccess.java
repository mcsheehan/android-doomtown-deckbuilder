package uk.co.marksheehan.doomtowndeckbuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class DoomtownDbAccess
{
    public interface CallbackOnCards
    {
        void success(List<CardModel> result);
        void failure();
    }

    private Retrofit mRetrofit;

    public DoomtownDbAccess()
    {
        mRetrofit = getRetrofitDtDbApi();
    }

    private Retrofit getRetrofitDtDbApi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dtdb.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    interface GetCardsService
    {
        @GET("cards")
        Call<List<CardModel>> getFullCardList();
    }

    public void sendServerRequestCardList(CallbackOnCards callbackCards)
    {
        GetCardsService               service      = mRetrofit.create(GetCardsService.class);
        Call<List<CardModel>>         call         = service.getFullCardList();
        CardCallbackToRetrofitAdapter cardCallback = new CardCallbackToRetrofitAdapter(callbackCards);
        call.enqueue(cardCallback);
    }

    class CardCallbackToRetrofitAdapter implements Callback<List<CardModel>>
    {
        CallbackOnCards mCardCallback;

        public CardCallbackToRetrofitAdapter(CallbackOnCards callbackCards)
        {
            mCardCallback = callbackCards;
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            mCardCallback.failure();
        }

        @Override
        public void onResponse(Call<List<CardModel>> call, Response<List<CardModel>> response)
        {
            List<CardModel> cardList = response.body();
            mCardCallback.success(cardList);
        }
    }

    public interface DownloadImage
    {
        @GET("web/bundles/dtdbcards/images/mCardResult/en/00024.jpg")
        Call<List<CardModel>> getImage();
    }
}