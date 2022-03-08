package automationpractice.registration;

import automationpractice.registration.models.User;
import automationpractice.registration.PageObjects.AuthenticationPage;
import automationpractice.registration.PageObjects.CreateAccountPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import automationpractice.registration.PageObjects.MainPage;
import automationpractice.registration.PageObjects.MyAccountPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.LocalDate;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class RegistrationTest {

    private WebDriver driver;
    private MainPage mainPage;
    private User user;

    @BeforeEach
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);

        user = new User("firstname",
                "lastname",
                String.valueOf(System.currentTimeMillis()) + "@mail.com",
                "123456",
                LocalDate.of(1985, 11, 27),
                "address",
                "city",
                "1",
                "12345",
                "+71234567890"
        );
    }

    @Test
    public void validAccountDataTest() {

        AuthenticationPage authenticationPage;
        CreateAccountPage createAccountPage;
        MyAccountPage myAccountPage;

        authenticationPage = mainPage.openPage().clickLogin();

        assertTrue(authenticationPage.isAuthenticationPage(), "It is not the authentication page!");

        createAccountPage = authenticationPage.enterEmail(user.getEmail()).submitButtonClick();
        assertTrue(createAccountPage.isCreateAccountPage(), "It is not the create account page!");

        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        myAccountPage = createAccountPage.fillAllUserData(user).submitButtonClick();

        assertTrue(myAccountPage.isMyAccountPage(), "It's not My account page!");
        assertEquals(firstName + " " + lastName, myAccountPage.getUserName(), "Incorrect user name is displayed!");

        myAccountPage.logout();
    }

    //Parametrized test does not need here. It is just to show my knowledge of it.   
    @ParameterizedTest
    @MethodSource("streamOfEmail")
    public void registrationWithAlreadyCreatedAccoubtTest(String email) {

        CreateAccountPage createAccountPage;

        user.setEmail(email);

        createAccountPage = mainPage.openPage().clickLogin()
                .enterEmail(user.getEmail()).submitButtonClick();
        assertTrue(createAccountPage.isCreateAccountPage(), "It is not the create account page!");

        createAccountPage.fillAllUserData(user);

        createAccountPage = createAccountPage.submitButtonClick().logout()
                .clickLogin().enterEmail(user.getEmail()).submitButtonClick();

        assertTrue(createAccountPage.isErrorMessge(), "Error message is not displayed!");
    }

    @AfterEach
    public void exit() {
        driver.quit();
    }

    private static Stream<String> streamOfEmail() {
        return Stream.of(String.valueOf(System.currentTimeMillis()) + "@mail.com");

    }
}
