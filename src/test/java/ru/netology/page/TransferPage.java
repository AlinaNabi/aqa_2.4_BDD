package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {

    private SelenideElement sum = $x(".//span[@data-test-id=\"amount\"]//input");
    private SelenideElement from = $x(".//span[@data-test-id=\"from\"]//input");
    private SelenideElement topUp = $("[data-test-id ='action-transfer']");
    private SelenideElement cancelButton = $("[data-test-id ='action-cancel']");

    public void doTransfer(int amount, String cardNumber) {
        sum.setValue(String.valueOf(amount));
        from.setValue(cardNumber);
        topUp.click();
    }
}
