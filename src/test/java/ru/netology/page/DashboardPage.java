package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement headingDashboard = $("[data-test-id=dashboard]+[class*='heading']");
    private static final ElementsCollection cardList = $$(".list .list__item");
    private final SelenideElement buttonActionReload = $("[data-test-id=action-reload]");

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public DashboardPage() {
    }

    public TransferPage openTransferForm(int cardNumber) {
    cardList.get(cardNumber).click();
       return new TransferPage();
    }

    public int getFirstCardBalance() {
        val text = cardList.first().text();
        return extractBalance(text);
    }

    public  int getSecondCardBalance() {
        val text = cardList.last().text();
        return extractBalance(text);
    }
    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}
