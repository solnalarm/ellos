import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by reford on 17.01.16.
 */
public class LoginPage {

    WebDriver driver;
    WebElementsActions web;
    //private String inputLocator = ".//input[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginUsername']";
    //Logger log = Logger.getLogger(this.getClass());

    Logger log = Logger.getLogger(LoginTests.class);
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        web = new WebElementsActions(driver);
    }

    //public  void fillEmailField(String inputLocator, String email){

        //web.input(inputLocator, email);
    //}

    public void fillEmailField(String value){
        try{
            web.clearAndInput("EmailField",value);
            log.info("input to EmailField - " + value);
        } catch  (IOException e){
            e.printStackTrace();
        }
    }

    public void fillPasswordfield(String value){
        try{
            web.input("PassField", value);
            log.info("input to PassField - " + value);
        } catch(IOException e){
            e.printStackTrace();
        }

        //web.input(".//input[@id='LoginPasswordText']", "admin@gmail.com");
    }

    public void pressLoginButton(){
        try{
            web.clickButton("LoginButton");
        } catch (IOException e){
            e.printStackTrace();
        }
       // web.clickButton(".//a[@id='ctl00_ctl00_conMain_conMain_LoginControl_LoginButton']");
    }

    public boolean checkErrorShown(String locator) throws IOException{


        if(web.isElementPresent(locator)){
            log.info("Error is present");
            return true;
        } else {
            log.info("Error is not present");
            return false;
        }
    }
}
