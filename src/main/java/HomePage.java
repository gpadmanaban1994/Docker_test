import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage  {


//    private WebDriver driver;
   private WebDriverWait wait;

    public HomePage(WebDriver driver){
//        this.driver=driver;
//        this.wait=new WebDriverWait(driver,40);
        wait=new WebDriverWait(driver,40);
        PageFactory.initElements(driver,this);


    }

    @FindBy(xpath = "//input[@id='search-box']")
    private WebElement searchBox;

    @FindBy(xpath = "//div[@id='search-button']")
    private WebElement searchButton;

    public void goToWhoScored(){

        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
    }
    public void searchPlayer(String player){
        searchBox.sendKeys(player);
        searchButton.click();
    }



}
