import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    private By personalArea = By.xpath("//span[@class='hidden-xs']");
    private By newCustomer = By.xpath("//a[@class='link-new-customer']");
    private By forgotPassword = By.xpath("//a[@class='link-forgot-password']");
    private By loginField = By.xpath("//*[@name='login[username]']");
    private By passwordField = By.xpath("//*[@name='login[password]']");
    private By signIn = By.xpath("//button[@id='submit']");
    private By errorSignIn = By.xpath("//div[@class='message-error']");

    public void clickPersonalArea() {
        driver.findElement(personalArea).click();
    }

    public AuthorisationPage clickNewCustomer() {
        clickPersonalArea();
        driver.findElement(newCustomer).click();
        return new AuthorisationPage(driver);
    }

    public AuthorisationPage clickForgotPassword() {
        clickPersonalArea();
        driver.findElement(forgotPassword).click();
        return new AuthorisationPage(driver);
    }

    public MainPage typeLogin(String login) {
        driver.findElement(loginField).sendKeys(login);
        return this;
    }

    public MainPage typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public void clickSignIn() {
        driver.findElement(signIn).click();
    }

    public MainPage signIn(String login, String password) {
        this.clickPersonalArea();
        this.typeLogin(login);
        this.typePassword(password);
        this.clickSignIn();
        return new MainPage(driver);
    }

    public String getSignInErrorText() {
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(errorSignIn)));
        return driver.findElement(errorSignIn).getText();
    }

}
