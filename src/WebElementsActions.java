import exception.NoElementFound;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigData;

import java.io.IOException;
import java.util.List;

/**
 * Created by reford on 16.01.16.
 */
public class WebElementsActions {

    public WebDriver driver;
   private static final Logger log = Logger.getLogger(WebElementsActions.class);
    public static WebDriverWait waitForElement;
    //private final ConfigData;



    public WebElementsActions(WebDriver driver) {

        this.driver = driver;
        waitForElement = new WebDriverWait(driver,20);
    }

    public void openPage(String url){
        driver.get(url);
    }

    public WebElement getElement(String elementLocator){
        try{
            return driver.findElement(ConfigData.ui(elementLocator));
        } catch (NoElementFound noElementFound){
            noElementFound.printStackTrace();
        return null;
        }
    }

    public List<WebElement> getElements(String elementsLocator){
        try{
            return driver.findElements(ConfigData.ui(elementsLocator));
        } catch (NoElementFound noElementFound){
            noElementFound.printStackTrace();
            return null;
        }
    }

    /**
     * Click a button
     */
    public void clickButton(String buttonLocator) throws NoElementFound {
        driver.findElement(ConfigData.ui(buttonLocator)).click();
        log.info("Click on Button " + buttonLocator);
    }

    /**
     * Click link
     */
    public void clickLink(String linkLocator) throws NoElementFound {
        driver.findElement(ConfigData.ui(linkLocator)).click();
        log.info("Click on Link " + linkLocator);
    }

    /**
     * Insert value into text input HTML field
     */
    public void input(String inputLocator, String inputData) throws NoElementFound {
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(inputData);
        log.info("Input in " + inputLocator + ", value - " + inputData);
    }

    public void clearAndInput(String inputLocator, String inputData) throws NoElementFound{
        driver.findElement(ConfigData.ui(inputLocator)).clear();
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(inputData);
        log.info("Input and clear in" + inputLocator + ",value -" + inputData);
    }

    /**
     * Insert value into text input HTML field and Click ENTER for Field which used "Value" in the xpath expression
     */
    public void inputAndClickEnter(String inputLocator, String inputData) throws NoElementFound{
        driver.findElement(ConfigData.ui(inputLocator)).clear();
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(inputData);
        driver.findElement(ConfigData.ui(inputLocator)).sendKeys(Keys.ENTER);
    }

    /**
     * Select/deselect the checkbox, the second parameter should be "Y" or "N"
     */
    public void selectCheckbox(String checkboxLocator, String ischeckboxSelect) throws NoElementFound {
        if (driver.findElement(ConfigData.ui(checkboxLocator)).isSelected() & ischeckboxSelect.equals("N")) {
            driver.findElement(ConfigData.ui(checkboxLocator)).click();
        }
        if (!driver.findElement(ConfigData.ui(checkboxLocator)).isSelected() & ischeckboxSelect.equals("Y")) {
            driver.findElement(ConfigData.ui(checkboxLocator)).click();
        }
    }

    /**
     * Method is used to check that element is present on page.
     */
    public boolean isElementPresent(String elementLocator) throws NoElementFound {
       if (!driver.findElement(ConfigData.ui(elementLocator)).isDisplayed()){
           return false;
       }
        return true;
    }
    /**
     * This method is used to agree messages on pop-up windows
     */
    public boolean isAlertPresentAndAccept() {
        boolean alertPresence = false;
        try {
            Alert alert = driver.switchTo().alert();
            alertPresence = true;
            alert.accept();
        } catch (NoAlertPresentException ex) {
            ex.printStackTrace();
            return alertPresence;
        }
        return alertPresence;
    }



    /**
     * This method is used to get text from pop-up windows
     */
    public String getAlertText() {
        String alertText;
        try {
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
            alert.accept();
            log.info("Alert text: " + alertText);
        } catch (NoAlertPresentException ex) {
            alertText = "Alert is not found";
            log.error("Alert is not found");
            ex.printStackTrace();
        }
        return alertText;
    }


    public void moveToElementAndClick(String moveToLocator, String clickToElement) throws NoElementFound {
        WebElement webElement = null;
        webElement = driver.findElement(ConfigData.ui(moveToLocator));

        Actions actions = new Actions(driver);
        actions.moveToElement(webElement);
        actions.perform();
        clickButton(clickToElement);

        log.info("moved To Element " + moveToLocator + "and clicked on" + clickToElement);
    }

    /**
     *Method#1 for refresh page
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void clickElement(String locator) throws NoElementFound{
        driver.findElement(ConfigData.ui(locator));
    }

    /**
     * Wait the element on page specified time
     */
    public void waitElementNotVisible(String elementLocator,int timeoutInS){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeoutInS);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(ConfigData.ui(elementLocator)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to wait for getting response from all Ajax requests
     */
    public boolean waitForAjaxResponse(int timeoutSeconds) throws InterruptedException {
        if (driver instanceof JavascriptExecutor) {
            JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

            for (int i = 0; i < timeoutSeconds; i++) {
                Long numberOfConnections = (Long) jsDriver.executeScript("return jQuery.active");
                if (numberOfConnections instanceof Long) {
                    log.debug("Number of active jQuery Ajax calls is <" + numberOfConnections + ">");

                    if (numberOfConnections == 0)
                        break;
                }
                // stay(1);
            }
            return false;
        } else {
            log.info("WebElementsActions Driver: <" + driver + "> cann't execute JavaScript");
            return false;
        }
    }
    /**
     * An expectation for checking that an element is present on the DOM of a page and visible.
     * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
     * Advantages of this method over isElementPresent(By elementLocator); is that it expects the appearance of an element.
     */
    public boolean waitForElementPresent(String elementLocator) {
        try {
            waitForElement.until(ExpectedConditions.visibilityOfElementLocated(ConfigData.ui(elementLocator)));
            log.info("WaitForElement _" + elementLocator + "_ Present");
        } catch (NoElementFound noElementFound) {
            log.error("Waiting for the appearance of the element _" + elementLocator + "_ was not successful " +
                    "WaitForElement _" + elementLocator + "_ Present");
            noElementFound.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * An expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param elementLocator used to find the element
     * @return the WebElement once it is located and clickable (visible and enabled)
     */
    public boolean waitForElementToBeClickable(String elementLocator) {
        try {
            waitForElement.until(ExpectedConditions.elementToBeClickable(ConfigData.ui(elementLocator)));
        } catch (NoElementFound noElementFound) {
            noElementFound.printStackTrace();
            return false;
        }
        return true;
    }


}



