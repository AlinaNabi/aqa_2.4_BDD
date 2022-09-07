package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.getSecondCardNumber;
import static ru.netology.page.DashboardPage.firstButtonAction;
import static ru.netology.page.DashboardPage.secondButtonAction;

public class MoneyTransferTest {

     DataHelper.AuthInfo authInfo;
     DashboardPage dashboardPage;


    @BeforeEach
    public void auth() {
        open("http://localhost:9999/");
        LoginPage login = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = login.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldTransferMoneyCard0001toCard002() {
        int amount = 100;
        val DashboardPage = new DashboardPage();
        val firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        val secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        val transferPage = firstButtonAction();
        transferPage.doTransfer(amount, getSecondCardNumber());

        val expectedRecipient = firstCardBalanceStart + amount;
        val expectedSender = secondCardBalanceStart - amount;

        assertEquals(expectedRecipient, dashboardPage.getFirstCardBalance());
        assertEquals(expectedSender, dashboardPage.getSecondCardBalance());
    }

    @Test
    public void shouldTransferFromCard0002toCard001() {

        int amount = 2000;
        val DashboardPage = new DashboardPage();
        val firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        val secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        val transferPage = firstButtonAction();
        transferPage.doTransfer(amount, getSecondCardNumber());

        val expectedRecipient = firstCardBalanceStart + amount;
        val expectedSender = secondCardBalanceStart - amount;

        assertEquals(expectedRecipient, dashboardPage.getFirstCardBalance());
        assertEquals(expectedSender, dashboardPage.getSecondCardBalance());
   }
    @Test
    public void shouldTransferExceedCardBalance() {
        int amount = 30000;
        val dashboardPage = new DashboardPage();
        val firstCardBalanceStart = DashboardPage.getFirstCardBalance();
        val secondCardBalanceStart = DashboardPage.getSecondCardBalance();
        val transferPage = secondButtonAction();
        transferPage.doTransfer(amount, getSecondCardNumber());
        TransferPage.errorLimit();

    }
}