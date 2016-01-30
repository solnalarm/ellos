import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import exception.NoElementFound;
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
    private static final Logger log = Logger.getLogger(MainPage.class);

    public MainPage(WebDriver driver) {
        this.driver = driver;
        web = new WebElementsActions(driver);
    }

    public void clickLogo(){
        try {
            web.clickLink("Logo");
            log.info("click on logo link");
        } catch (NoElementFound noElementFound) {
            noElementFound.printStackTrace();
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
        } catch (NoElementFound noElementFound){
            noElementFound.printStackTrace();
        }

        try {
            if (web.isElementPresent("EmailField")){
                log.info("Switch Login Page correct");
            } else {
                log.error("Switch Login Page was INCORRECT");
            }
        } catch (NoElementFound noElementFound){
            noElementFound.printStackTrace();
        }
    }

    public boolean isUserLoggedIn() throws IOException{
        try{
            if(web.isElementPresent("LogoutLink")){
                log.info("Login was correct");
                return true;
            }else {
                log.error("Login was INcorrect");
                return false;
            }
        } catch(NoElementFound noElementFound){
            noElementFound.printStackTrace();
        }
        return false;
    }
    public boolean isUserLogOut(){
        try{
            if(web.isElementPresent("LoginLink")){
                log.info("LogOut was correct");
                return true;
            } else{
                log.error("LogOut was INcorrect");
                return false;
            }
        } catch (NoElementFound noElementFound) {
            noElementFound.printStackTrace();
        }
        return false;
    }
    public void userLogOut() throws IOException{
        web.waitForElementPresent("LogoutLink");
               try {
            web.clickLink("LogoutLink");
                   log.info("User Logout correct");
               } catch (NoElementFound noElementFound){
            noElementFound.printStackTrace();
        }

    }

    public void openMainPage(){
        web.openPage("http://www.ellos.se/");
    }


}
