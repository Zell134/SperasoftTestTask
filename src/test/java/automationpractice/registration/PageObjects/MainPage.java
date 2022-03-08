package automationpractice.registration.PageObjects;

import automationpractice.registration.properties.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {
    
    @FindBy(xpath = "//*[@id='create_account_error']")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[@class='header_user_info']/a[@class='login']")
    private WebElement loginButton;

    private String URL = ConfProperties.getProperty("mainPage");

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage openPage() {
        driver.get(URL);
        return this;
    }
    
    public boolean errorMessageIsShown(){
        waitVisibility(errorMessage);
        return  errorMessage.getText().equalsIgnoreCase("Invalid email address.");
    }

    public AuthenticationPage clickLogin() {
        buttonClick(loginButton);
        return new AuthenticationPage(driver);
    }

}
