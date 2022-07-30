import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstTest {


    public static final String BASIC_URL = "https://en.wikipedia.org/";

    WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.get(BASIC_URL);
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }

    @DataProvider(name ="search")
    public Object[][] getSearch () {
        return new Object[][]{
                {"fox"},
                {"dog"}
        };
    }

    @Test(dataProvider = "search")
    public void verifyWikiSearch (String searchValue) {
        //find input
        SearchForText(driver, searchValue);
        //Assertion
        String actualResult = driver.getCurrentUrl();
        Assert.assertTrue(actualResult.toLowerCase().contains(searchValue.toLowerCase()));

    }

    private void SearchForText(WebDriver driver, String searchValue) {
        By pathToSearch = By.className("vector-search-box-input");
        WebElement searchInput = driver.findElement(pathToSearch);
        searchInput.click();
        searchInput.sendKeys(searchValue);
        searchInput.sendKeys(Keys.ENTER);

    }
}
