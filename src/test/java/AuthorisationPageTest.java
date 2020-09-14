import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class AuthorisationPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\sasha\\IdeaProjects\\test-selenium\\drivers\\geckodriver.exe");

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.shop.philips.ru/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void authorisationSignUp() {
        AuthorisationPage authorisationPage = mainPage.clickNewCustomer();
        authorisationPage.signUp("8-888-888-88-88", "Test", "Name", "email");
        String error= authorisationPage.getSignUpErrorText();
        Assert.assertEquals("Пожалуйста, укажите корректный адрес электронной почты", error);
    }

    @Test
    public void signUpFailTest() {
        AuthorisationPage authorisationPage = mainPage.clickNewCustomer();
        authorisationPage.signUp("", "Test", "Name", "email");
        String error= authorisationPage.getSignUpErrorText();
        Assert.assertEquals("Пожалуйста, введите корректный адрес электронной почты.", error);
    }

    @Test
    public void forgotPasswordTest() {
        AuthorisationPage authorisationPage = mainPage.clickForgotPassword();
        authorisationPage.recoveryForm("8 999 999-99-99", "password");
        String error = authorisationPage.getSignInErrorText();
        Assert.assertEquals("Указан неверный пароль", error);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}

