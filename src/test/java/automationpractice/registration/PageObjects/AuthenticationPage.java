package automationpractice.registration.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage extends BasePage {

    @FindBy(xpath = "//div[@id='center_column']/h1[@class='page-heading' and text()='Authentication']")
    private WebElement header;
    
    @FindBy(id = "email_create")
    private WebElement emailField;

    @FindBy(id = "SubmitCreate")
    private WebElement submitButton;

    public AuthenticationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isAuthenticationPage() {
        waitVisibility(header);
        return header.getText().equalsIgnoreCase("Authentication");
    }

    public AuthenticationPage enterEmail(String email) {
        enterText(emailField, email);
        return this;
    }

    public CreateAccountPage submitButtonClick() {
        buttonClick(submitButton);
        return new CreateAccountPage(driver);
    }

}
