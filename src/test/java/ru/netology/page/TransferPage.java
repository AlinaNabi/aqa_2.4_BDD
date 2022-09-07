package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.valueOf;

public class TransferPage {

    private SelenideElement sum = $x(".//span[@data-test-id=\"amount\"]//input");
    private SelenideElement from = $x(".//span[@data-test-id=\"from\"]//input");
    private SelenideElement topUp = $("[data-test-id ='action-transfer']");
    private SelenideElement cancelButton = $("[data-test-id ='action-cancel']");
    private SelenideElement errorNotification = $x("//div[@data-test-id='error-notification']");
    private SelenideElement errorButton = $x("//div[@data-test-id='error-notification']/button");

    public void doTransfer(int amount, DataHelper.CardNumber card) {
        sum.val(valueOf(amount));
        from.setValue(valueOf(card));
        topUp.click();
        new DashboardPage();
    }

    public static void errorLimit() {
        $(".notification__content").should(Condition.exactText("Ошибка"));
    }

    public static void invalidCard() {
        $(".notification__content").should(Condition.text("Ошибка! Произошла ошибка"));
    }
}
