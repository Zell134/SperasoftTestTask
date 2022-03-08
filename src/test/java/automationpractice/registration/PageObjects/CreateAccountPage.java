package automationpractice.registration.PageObjects;

import automationpractice.registration.models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage extends BasePage {

    @FindBy(xpath = "//div[@id='noSlide']/h1[@class='page-heading' and text()='Create an account']")
    private WebElement header;

    @FindBy(id = "create_account_error")
    private WebElement errorMessage;

    @FindBy(id = "customer_firstname")
    private WebElement firstName;
    @FindBy(id = "customer_lastname")
    private WebElement LastName;
    @FindBy(id = "email")
    private WebElement email;
    @FindBy(id = "passwd")
    private WebElement password;
    @FindBy(id = "days")
    private WebElement day;
    @FindBy(id = "months")
    private WebElement month;
    @FindBy(id = "years")
    private WebElement year;
    @FindBy(id = "address1")
    private WebElement address;
    @FindBy(id = "city")
    private WebElement city;
    @FindBy(id = "id_state")
    private WebElement stateId;
    @FindBy(id = "postcode")
    private WebElement postcode;
    @FindBy(id = "phone_mobile")
    private WebElement phone;
    @FindBy(id = "submitAccount")
    private WebElement submitButton;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isCreateAccountPage() {
        waitVisibility(header);
        return header.getText().equalsIgnoreCase("Create an account");
    }

    public boolean isErrorMessge() {
        waitVisibility(errorMessage);
        String errorText = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
        return errorMessage.getText().contains(errorText);
    }

    public CreateAccountPage enterFirstName(String text) {
        enterText(firstName, text);
        return this;
    }

    public CreateAccountPage enterLastName(String text) {
        enterText(LastName, text);
        return this;
    }

    public CreateAccountPage enterEmail(String text) {
        enterText(email, text);
        return this;
    }

    public CreateAccountPage enterPassword(String text) {
        enterText(password, text);
        return this;
    }

    public CreateAccountPage enterBirthDate(String date, String month, String year) {
        enterSelectValue(this.day, date);
        enterSelectValue(this.month, month);
        enterSelectValue(this.year, year);
        return this;
    }

    public CreateAccountPage enterAddress(String text) {
        enterText(address, text);
        return this;
    }

    public CreateAccountPage enterCity(String text) {
        enterText(city, text);
        return this;
    }

    public CreateAccountPage enterStateId(String text) {
        enterSelectValue(stateId, text);
        return this;
    }

    public CreateAccountPage enterPostCode(String text) {
        enterText(postcode, text);
        return this;
    }

    public CreateAccountPage enterPhone(String text) {
        enterText(phone, text);
        return this;
    }

    public MyAccountPage submitButtonClick() {
        buttonClick(submitButton);
        return new MyAccountPage(driver);
    }

    public CreateAccountPage fillAllUserData(User user) {
        enterFirstName(user.getFirstName())
                .enterLastName(user.getLastName())
                .enterPassword(user.getPassword())
                .enterBirthDate(String.valueOf(user.getBirthDate().getDayOfMonth()),
                        String.valueOf(user.getBirthDate().getMonthValue()),
                        String.valueOf(user.getBirthDate().getYear()))
                .enterAddress(user.getAddress())
                .enterCity(user.getCity())
                .enterStateId(user.getStateid())
                .enterPostCode(user.getPostcode())
                .enterPhone(user.getPhone());
        return this;
    }
}
