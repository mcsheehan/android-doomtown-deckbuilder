package net.marksheehan.doomtowndeckbuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class DoomtownDbAccess
{
    private Retrofit doomtownRestApi;

    private Retrofit createDoomtownRestApi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dtdb.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public DoomtownDbAccess()
    {
        doomtownRestApi = createDoomtownRestApi();
    }

    interface GetCardsService
    {
        @GET("cards")
        Call<List<CardModel>> getFullCardList();

        @GET("photo")
        Call<List<CardModel>> getPhotoList();
    }

    public interface CardQueryCallback
    {
        void success(List<CardModel> result);
        void failure();
    }

    public void sendServerRequestCardList(CardQueryCallback cardQueryCallback)
    {
        GetCardsService               service      = doomtownRestApi.create(GetCardsService.class);
        Call<List<CardModel>>         call         = service.getFullCardList();
        CardCallbackImplementation cardCallback = new CardCallbackImplementation(cardQueryCallback);
        call.enqueue(cardCallback);
    }

    class CardCallbackImplementation implements Callback<List<CardModel>>
    {
        CardQueryCallback cardCallback;

        public CardCallbackImplementation(CardQueryCallback callbackCards)
        {
            cardCallback = callbackCards;
        }

        @Override
        public void onResponse(Call<List<CardModel>> call, Response<List<CardModel>> response)
        {
            List<CardModel> cardList = response.body();
            cardCallback.success(cardList);
        }

        @Override
        public void onFailure(Call call, Throwable t)
        {
            cardCallback.failure();
        }
    }
}