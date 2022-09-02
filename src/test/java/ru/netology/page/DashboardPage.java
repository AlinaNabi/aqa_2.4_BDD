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
    private ElementsCollection cardList = $$(".list .list__item");
    private static SelenideElement firstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button");
    private static SelenideElement secondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button");

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public DashboardPage() {
    }

    public static TransferPage firstButtonAction() {
        firstCardButton.click();
        return new TransferPage();
    }

    public static TransferPage secondButtonAction() {
        secondCardButton.click();
        return new TransferPage();
    }

    public int getFirstCardBalance() {
        val text = cardList.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
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
