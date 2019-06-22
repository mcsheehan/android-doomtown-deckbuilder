package net.marksheehan.doomtowndeckbuilder;

import com.squareup.picasso.Picasso;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.http.GET;

public class DoomtownDbAccessTest
{
    final class GetCardsFromApiAsync implements DoomtownDbAccess.CardQueryCallback
    {
        GetCardsFromApiAsync()
        {
            DoomtownDbAccess dtDb = new DoomtownDbAccess();
            dtDb.sendServerRequestCardList(this);
        }

        List<CardModel> cardList;
        final CountDownLatch lock = new CountDownLatch(1);

        public void waitForLock10Seconds() throws InterruptedException
        {
            lock.await(10, TimeUnit.SECONDS);
        }

        public void waitForLockSeconds(long timeoutSeconds) throws InterruptedException
        {
            lock.await(timeoutSeconds, TimeUnit.SECONDS);
        }

        @Override
        public void success(List<CardModel> cards)
        {
            cardList = cards;
            lock.countDown();
        }

        @Override
        public void failure()
        {
            Assert.fail();
        }
    }

    @Test
    public void getDtDbApi() throws Exception
    {
        String EXPECTED_NAME_OF_FIRST_CARD = "Pistol Whip";

        GetCardsFromApiAsync cardCallback = new GetCardsFromApiAsync();
        cardCallback.waitForLock10Seconds();

        Assert.assertTrue(cardCallback.cardList.size() > 600);

        String firstCardTitle = cardCallback.cardList.get(0).getTitle();
        Assert.assertEquals(EXPECTED_NAME_OF_FIRST_CARD, firstCardTitle);
    }


    @Test
    public void downloadCardImage() throws Exception
    {
        GetCardsFromApiAsync cardCallback = new GetCardsFromApiAsync();
        cardCallback.waitForLock10Seconds();

        String imageUrl = cardCallback.cardList.get(0).getImagesrc();

        for (CardModel card : cardCallback.cardList)
        {
            System.out.println(card.getImagesrc());
        }

        Assert.assertTrue(true);
    }
}