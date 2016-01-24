import org.apache.commons.exec.LogOutputStream;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * Created by reford on 17.01.16.
 */
public class MainPage {

    WebDriver driver;
    WebElementsActions web;

    //Logger log = Logger.getLogger(this.getClass());
    Logger log = Logger.getLogger(MainPage.class);

    public MainPage(WebDriver driver) {
        this.driver = driver;
        web = new WebElementsActions(driver);
    }

    public void clickLogo(){
        try {
            web.clickLink("Logo");
            log.info("click on logo link");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void  switchToLoginPage(){
       /* web.clickLink(".//a[@id='showlogin']");
        if (web.isElementPresent(".//input[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginUsername']")){
            System.out.println("SwitchTo Login Page was correct");
        } else {
            System.out.println("SwitchTo Login Page was Incorrect");
        }

    }*/

        try{
            web.clickButton("LoginLink");
            log.info("click on LoginLink");
        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            if (web.isElementPresent("EmailField")){
                log.info("Switch Login Page correct");
            } else {
                log.error("Switch Login Page was INCORRECT");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
