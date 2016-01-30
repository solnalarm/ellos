package utils;
import exception.NoElementFound;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by reford on 20.01.16.
 */
public class ConfigData {
    //TODO Maven
    public static String uiMappingFile = "/UIMapping.properties";
    public static String getValueFromFile(String key,String fileName) throws NoElementFound {
        Properties p = new Properties();
        try {

            p.load(ConfigData.class.getResourceAsStream(uiMappingFile));
        } catch (IOException e){
            e.printStackTrace();
        }

        return (p.getProperty(key));

    }

    public static By ui(String key) throws NoElementFound{
        String[] partsOfLocators = getValueFromFile(key,uiMappingFile).split("\"");
        String findMethod = partsOfLocators[0].substring(0,partsOfLocators[0].length() - 1);
        String target = partsOfLocators[1];

        /*        System.out.println(key);                //Logo
        System.out.println(partsOfLocators[0]); //cssSelector(
        System.out.println(findMethod);          //cssSelector
        System.out.println(target);             //.ellos.active*/

        //Return By class with appropriate method and target
        if (findMethod.equals("id")){
            return By.id(target);
        } else {
            if(findMethod.equals("xpath")){
                return By.xpath(target);
            } else {
                if(findMethod.equals("name")){
                    return By.name(target);
                } else {
                    if(findMethod.equals("linkText")){
                        return By.linkText(target);
                    } else {
                        if(findMethod.equals("tagName")){
                            return By.tagName(target);
                        } else {
                            if (findMethod.equals("class")){
                                return By.className(target);
                            } else {
                                if(findMethod.equals("cssSelector")){
                                    return By.cssSelector(target);
                                } else {
                                    return By.partialLinkText(target);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
