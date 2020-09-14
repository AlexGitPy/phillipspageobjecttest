import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorisationPage {

    private WebDriver driver;

    public AuthorisationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By phoneField = By.xpath("(//input[@type='tel'])[1]");
    private By signUpNameField = By.xpath("//input[@name='register-name']");
    private By emailField = By.xpath("//input[@name='register-email']");
    private By signUpButton = By.xpath("//button[@id='btn-register']");
    private By heading = By.xpath("//h1[text()='Вход/Регистрация']");
    private By signUpError = By.xpath("(//*[@class='error'])[3]");

    private By signInButton = By.xpath("//span[@id='auth-select-login']");
    private By phoneSignInField = By.xpath("(//input[@type='tel'])[2]");
    private By emailSignInField = By.xpath("//input[@name='login-email']");
    private By rememberThePasswordButton = By.xpath("//button[@id='btnRecoveryPass']");
    private By passwordField = By.xpath("//*[@id='password_recovery']");
    private By passwordRecoveryButton = By.xpath("(//*[contains(@class, 'fadeIn')])[4]");
    private By passwordRecoveryError = By.xpath("//*[@class='input-status']/*[@class='error']");

    public AuthorisationPage typePhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
        return this;
    }

    public AuthorisationPage typeName(String name, String lastName) {
        driver.findElement(signUpNameField).sendKeys(name + " " + lastName);
        return this;
    }

    public AuthorisationPage typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    public AuthorisationPage signUp(String phone, String name, String lastName, String email) {
        this.typePhone(phone);
        this.typeName(name, lastName);
        this.typeEmail(email);
        clickSignUpButton();
        return new AuthorisationPage(driver);
    }

    private void isVisibility(By xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(xpath)));
    }

    public String getSignUpErrorText() {
        return driver.findElement(signUpError).getText();
    }

    public void signIn() {
        driver.findElement(signInButton).click();
    }

    public AuthorisationPage phoneSignIn(String phone) {
        isVisibility(phoneSignInField);
        driver.findElement(phoneSignInField).sendKeys(phone);
        return this;
    }

    public void clickRememberPasswordButton() {
        isVisibility(rememberThePasswordButton);
        driver.findElement(rememberThePasswordButton).click();
    }

    public AuthorisationPage rememberPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public void clickPasswordRecoveryButton() {
        // automatic pressing button
        isVisibility(passwordRecoveryButton);
        driver.findElement(passwordRecoveryButton).click();

    }



    public AuthorisationPage recoveryForm(String phone, String password) {
        this.signIn();
        this.phoneSignIn(phone);
        this.clickRememberPasswordButton();
        this.rememberPasswordField(password);
//        this.clickPasswordRecoveryButton();
        return new AuthorisationPage(driver);
    }

    public String getHeadingText() {
        return driver.findElement(heading).getText();
    }

    public String getSignInErrorText() {
        isVisibility(passwordRecoveryError);
        return driver.findElement(passwordRecoveryError).getText();
    }

}
