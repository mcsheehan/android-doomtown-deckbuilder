package uk.co.marksheehan.doomtowndeckbuilder;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DoomtownDbAccessTest
{
    @Test
    public void getDtDbApi() throws Exception
    {
        final CountDownLatch lock = new CountDownLatch(1);

        final class TestCardCallback implements DoomtownDbAccess.CallbackOnCards
        {
            List<CardModel> cardList;

            @Override
            public void success(List<CardModel> cards)
            {
                cardList = cards;
                lock.countDown();
            }

            @Override
            public void failure()
            {

            }
        }


        TestCardCallback cardCallback = new TestCardCallback();

        DoomtownDbAccess dtDb = new DoomtownDbAccess();
        dtDb.sendServerRequestCardList(cardCallback);

        lock.await(10, TimeUnit.SECONDS);
        Assert.assertTrue(cardCallback.cardList.size() > 600);
        String nameOfFirstCard = "Pistol Whip";
        Assert.assertTrue(nameOfFirstCard.equals(cardCallback.cardList.get(0).title));
    }
}