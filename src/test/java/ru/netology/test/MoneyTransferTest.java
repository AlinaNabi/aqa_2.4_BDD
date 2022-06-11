package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoneyTransferTest {
    int amount;

    public static DashboardPage openDashboard() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode();
        return verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldTransferMoneyCard0001toCard002() {
        amount = 1000;
        DashboardPage page = openDashboard();
        page.updateBalance();
        int expectedRecipient = page.getBalance(DataHelper.getSecondCardNumber()) + amount;
        int expectedSender = page.getBalance(DataHelper.getFirstCardNumber()) - amount;
        page.moneyTransfer(DataHelper.getFirstCardNumber(), DataHelper.getSecondCardNumber(), amount);
        page.updateBalance();
        assertEquals(expectedRecipient, page.getBalance(DataHelper.getSecondCardNumber()));
        assertEquals(expectedSender, page.getBalance(DataHelper.getFirstCardNumber()));
    }
    @Test
    public void shouldTransferFromCard0002toCard001() {
        amount = 10;
        DashboardPage page = openDashboard();
        page.updateBalance();
        int expectedRecipient = page.getBalance(DataHelper.getFirstCardNumber()) + amount;
        int expectedSender = page.getBalance(DataHelper.getSecondCardNumber()) - amount;
        page.moneyTransfer(DataHelper.getSecondCardNumber(), DataHelper.getFirstCardNumber(), amount);
        page.updateBalance();
        assertEquals(expectedRecipient, page.getBalance(DataHelper.getFirstCardNumber()));
        assertEquals(expectedSender, page.getBalance(DataHelper.getSecondCardNumber()));
    }
    @Test
    public void shouldNotTransferFromCard0002toCard001() {
        amount = 100000;
        DashboardPage page = openDashboard();
        page.updateBalance();
        page.moneyTransfer(DataHelper.getFirstCardNumber(), DataHelper.getSecondCardNumber(), amount);
        page.updateBalance();
        assertTrue(page.getBalance(DataHelper.getFirstCardNumber()) >= 0);

    }


}
