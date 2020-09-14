import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MainPageTest {

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
    public void newCustomer() {
        AuthorisationPage authorisationPage = mainPage.clickNewCustomer();
        String heading = authorisationPage.getHeadingText();
        Assert.assertEquals("Вход/Регистрация", heading);
    }

    @Test
    public void signInErrorTest(){
        mainPage.signIn("email", "password");
        String error = mainPage.getSignInErrorText();
        Assert.assertEquals("Несуществующий email адрес или пароль", error);
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
