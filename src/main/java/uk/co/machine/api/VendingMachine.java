package uk.co.machine.api;

import uk.co.machine.entity.Bucket;
import uk.co.machine.entity.Coin;
import uk.co.machine.entity.Item;

import java.util.List;

public interface VendingMachine {
    public long selectItemAndGetPrice(Item item);

    public void insertCoin(Coin coin);

    public List<Coin> refund();

    public Bucket<Item, List<Coin>> collectItemAndChange();

    public void reset();
}

