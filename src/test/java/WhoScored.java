import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class WhoScored {


@DataProvider(name = "testdata",parallel = true)
public Object[][] data(){

    String[][] expectedValues={{"Lionel Messi","barcelona"},{"Karim Benzema","real madrid"},{"eden hazard","real madrid"}};
    return expectedValues;


}



    @Test(dataProvider = "testdata")
    public void verifyTeam(String name,String expectedTeam) throws MalformedURLException {

        String host=System.getProperty("HUB_HOST");
        System.out.println(host);
       // String browser="chrome";
//        if(System.getProperty("HUB_HOST")==null){
//            host="localhost";
//        }
//        else{
//            host=;
//        }

        ChromeOptions ch=new ChromeOptions();
        FirefoxOptions fx=new FirefoxOptions();
        DesiredCapabilities dc =null;
        if(System.getProperty("BROWSER")!=null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
            dc=DesiredCapabilities.firefox();
        }
        else{
            dc=new DesiredCapabilities(ch);
        }
        WebDriver  driver=new RemoteWebDriver(new URL("http://"+host+":4444/wd/hub"),dc);
        driver.get("https://1xbet.whoscored.com/");
        HomePage home=new HomePage(driver);
        SearchResultPage resultsPage=new SearchResultPage(driver);
        home.goToWhoScored();
        home.searchPlayer(name);
        resultsPage.waitTillSearchEnds();
        List<List<String>> actual=resultsPage.getSearchResults();

        for(List<String> act:actual){

            if(act.get(0).equalsIgnoreCase(name)){
                Assert.assertTrue(act.get(1).equalsIgnoreCase(expectedTeam));
                break;
            }
        }

        driver.quit();
    }




}
