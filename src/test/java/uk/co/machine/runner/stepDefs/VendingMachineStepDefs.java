package uk.co.machine.runner.stepDefs;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import uk.co.machine.api.VendingMachine;
import uk.co.machine.entity.Bucket;
import uk.co.machine.entity.Coin;
import uk.co.machine.entity.Item;
import uk.co.machine.factory.VendingMachineFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineStepDefs {
    private static VendingMachine vm;
    private Bucket<Item, List<Coin>> bucket;
    @Before
    public static void setUp(){
        vm = VendingMachineFactory.createVendingMachine();
    }

    @After
    public static void tearDown(){
        vm = null;
    }

    @Given("^I insert the coin value of (.*) Cent$")
    public void i_insert_the_coin_value_of_Cents(String coinValues) {
        List<Coin> coins = Arrays.asList(coinValues.split(","))
                .stream()
                .map(Coin::valueOf)
                .collect(Collectors.toList());

        coins.forEach(c->vm.insertCoin(c));
    }

    @Then("^vending machine is accepting the (.*) Cent coin$")
    public void vending_machine_is_accepting_the_coins(Coin coin) {
        assertThat("Coin value not matching", vm.refund().get(0), is(coin));
    }
    @Then("^I am able to select (.*) product from the list$")
    public void i_am_able_to_select_Coke_product_from_the_list(Item item) {
        vm.selectItemAndGetPrice(item);
        bucket =  vm.collectItemAndChange();
        assertThat("Not able to select right product", vm.refund().size(), is(0));
    }

    @Then("^I can get refund amount (.*) by cancelling the request$")
    public void i_can_get_refund_by_cancelling_the_request(Coin coin) {
        assertThat("Cancel refund not working", vm.refund().get(0), is(coin));
    }

    @Then("^I got remaining (.*) balance back$")
    public void i_got_remaining_balance_back(long balance) {
        List<Coin> change = bucket.getSecond();
        assertThat("Coin value not matching", getTotal(change), is(balance));

    }

    private long getTotal(List<Coin> change){
        long total = 0;
        for(Coin c : change){
            total = total + c.getDenomination();
        }
        return total;
    }
}
