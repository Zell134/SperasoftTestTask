package automationpractice.registration.PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
    }

    protected void waitVisibility(WebElement elment) {
        wait.until(ExpectedConditions.visibilityOf(elment));
    }

    protected void buttonClick(WebElement element) {
        waitVisibility(element);
        element.click();
    }
    
    protected void enterText(WebElement element, String text){
        waitVisibility(element);
        element.sendKeys(text);
    }
    
    protected void enterSelectValue(WebElement element, String text){
        Select select = new Select(element);
        select.selectByValue(text);
    }
}
