import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by reford on 27.01.16.
 */
public class RegistrationTests {
    private static WebDriver driver;
    static  MainPage mainPage;
    static LoginPage loginPage;



    @BeforeClass
    public static void setUp() throws Exception {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        System.out.println("Browser open successful");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//magic
        driver.get("http://www.ellos.se/");
        System.out.println("Start test");

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

    }

    //@Test
    public  void emptyFields() throws Exception{
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

        mainPage.clickLogo();
        mainPage.switchToLoginPage();

        loginPage.fillEmailField("admin@gmail.com");
        loginPage.fillPasswordfield("Password01");

        loginPage.pressLoginButton();

        Assert.assertTrue("Incorrect login to the system with fake log/pass",
                loginPage.isErrorShown("ErrorMess"));

    }





    @AfterClass
    public  static void  tearDown() throws Exception{
        System.out.println("End test");
        driver.quit();
    }
}
