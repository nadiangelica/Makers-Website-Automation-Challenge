import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MakersWebsite {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://makers.tech");
        WebElement cookiesButton = driver.findElement(By.id("hs-eu-confirmation-button"));
        cookiesButton.click();
    }

    @Test
    void getTitle() throws InterruptedException {
        WebElement getTitle = driver.findElement(By.className("LpHeroBannerTitle"));
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Building The Future"));
    }

    @Test
    void codeOfConductMakers() throws InterruptedException {
        WebElement codeOfConduct = driver.findElement(By.cssSelector("#hs_menu_wrapper_module_169685928414248_ > ul > li:nth-child(3) > a"));
        codeOfConduct.click();
        String currentURL = driver.getCurrentUrl();
        assertEquals(currentURL, "https://makers.tech/code-of-conduct");
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Code of conduct"));
    }

    @Test
    void faqsMakers() throws InterruptedException {
        WebElement faqs = driver.findElement(By.linkText("FAQs"));
        faqs.click();

        WebElement searchInput = driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input"));
        searchInput.click();
        searchInput.sendKeys("badger");
        searchInput.sendKeys(Keys.ENTER);

        WebElement searchResults = driver.findElement(By.className("kb-search-results__heading"));
        String searchResultsText = searchResults.getText();
        assertEquals(searchResultsText, "No results for \"badger\"");
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
