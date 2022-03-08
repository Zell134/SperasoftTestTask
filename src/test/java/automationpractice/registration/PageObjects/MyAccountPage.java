package automationpractice.registration.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    private WebDriver driver;
    
    @FindBy(xpath = "//a[@class = 'account']/span")
    private WebElement userName;
    
    @FindBy(xpath = "//a[@class = 'logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//div[@id = 'center_column']/h1")
    private WebElement title;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isMyAccountPage() {
        return title.getText().equalsIgnoreCase("My account");
    }

    public String getUserName() {
        return userName.getText();
    }
    
    public MainPage logout(){
        logoutButton.click();
        return new MainPage(driver);
    }
}
