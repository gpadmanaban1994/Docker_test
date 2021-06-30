
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.util.ArrayList;
import java.util.List;

public class SearchResultPage {


   // private WebDriver driver;
    private WebDriverWait wait;

    public SearchResultPage(WebDriver driver){

        this.wait=new WebDriverWait(driver,30);
        PageFactory.initElements(driver,this);

    }


    @FindBy(xpath = "//div[@class='search-result']")
    private WebElement searchResults;


    @FindBy(xpath = "//tbody")
    private WebElement playerInfo;

    public void waitTillSearchEnds(){
        wait.until(ExpectedConditions.visibilityOf(searchResults));
    }

    public List<List<String>> getSearchResults(){

        List<List<String>> playerDetails=new ArrayList<>();
        List<String> headersName=new ArrayList<>();

        List<WebElement> rows=playerInfo.findElements(By.xpath("//tr"));

        for(WebElement header:rows.get(0).findElements(By.xpath("//th"))){

            headersName.add(header.getText());
        }

        playerDetails.add(headersName);


        for(int i=1;i<rows.size();i++){
            List<String> info=new ArrayList<>();
            for(WebElement rowEle:rows.get(i).findElements(By.xpath(".//td"))){
                info.add(rowEle.getText());


            }
            playerDetails.add(info);
        }



        return playerDetails;






    }









}
