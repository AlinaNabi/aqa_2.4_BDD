package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.getSecondCardNumber;
import static ru.netology.page.DashboardPage.firstButtonAction;

public class MoneyTransferTest {


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
        DashboardPage dashboardPage = new DashboardPage();
        val firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        val secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        var transferPage = firstButtonAction();

        val expectedRecipient = firstCardBalanceStart + amount;
        val expectedSender = secondCardBalanceStart - amount;

        assertEquals(expectedRecipient, dashboardPage.getFirstCardBalance());
        assertEquals(expectedSender, dashboardPage.getSecondCardBalance());
    }

//    @Test
//    public void shouldTransferFromCard0002toCard001() {
//
//        DashboardPage dashboardPage = new DashboardPage();
//        TransferPage transferPage = dashboardPage.openTransferForm(1);
//        transferPage.doTransfer(100, "5559 0000 0000 0001");
//
//        int expectedRecipient = dashboardPage.getFirstCardBalance();
//        int expectedSender = dashboardPage.getSecondCardBalance();
//
//        int cardExpectedRecipient = 10000;
//        int cardExpectedSender = 10000;
//
//        assertEquals(cardExpectedRecipient, expectedRecipient);
//        assertEquals(cardExpectedSender, expectedSender);
//    }
//    @Test
//    public void shouldMoneyCard0001toCard002() {
//
//        DashboardPage dashboardPage = new DashboardPage();
//        TransferPage transferPage = dashboardPage.openTransferForm(0);
//        transferPage.doTransfer(10000, "5559 0000 0000 0002");
//
//        int expectedRecipient = dashboardPage.getFirstCardBalance();
//        int expectedSender = dashboardPage.getSecondCardBalance();
//
//        int cardExpectedRecipient = 30100;
//        int cardExpectedSender = -10100;
//
//        assertEquals(cardExpectedRecipient, expectedRecipient);
//        assertEquals(cardExpectedSender, expectedSender);
//    }

}