package test;

import data.DataHelper;
import data.SQLHelper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.*;

public class LoginTest {

    LoginPage loginPage;

    @AfterEach
    void tearDown() {cleanAuthCodes();}

    @AfterAll
    static void tearDownAll() {cleanData();}

    @BeforeEach
    void setup() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @Test
    @DisplayName("Successful login")
    void shouldBeSuccessLogin() {
        var authInfo = DataHelper.getCorrectUserLogInInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = SQLHelper.getVerificationCede();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Unsuccessful login")
    void shouldUnsuccessfulLogin() {
        var authInfo = DataHelper.generateUser();
        loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotification("Ошибка! \nеНеверно указан логин или пароль");
    }

    @Test
    @DisplayName("Incorrect code")
    void shouldUnsuccessfulCode() {
        var authInto = DataHelper.getCorrectUserLogInInfo();
        var verificationPage = loginPage.validLogin(authInto);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = DataHelper.generateRandomVerificationCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyErrorNotification("Ошибка! \nНеверно указан код! Попробуйте еще раз.");
    }
}
