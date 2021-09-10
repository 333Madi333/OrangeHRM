import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Myinfo {
    WebDriver driver = null;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\Selenium\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test(testName = "Orange")
    public void test(){

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");

        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        driver.findElement(By.xpath("//*[text()='My Info']")).click();

        List<WebElement> tabs = driver.findElements(By.xpath("//ul[@id='sidenav']//li"));
        List<String> tabsActual = new ArrayList<>();

        for (int i = 0; i < tabs.size(); i++) {
            tabsActual.add(tabs.get(i).getText());
        }

        List<String> tabsExpected = new ArrayList<>();
        tabsExpected.add("Personal Details");
        tabsExpected.add("Contact Details");
        tabsExpected.add("Emergency Contacts");
        tabsExpected.add("Dependents");
        tabsExpected.add("Immigration");
        tabsExpected.add("Job");
        tabsExpected.add("Salary");
        tabsExpected.add("Tax Exemptions");
        tabsExpected.add("Report-to");
        tabsExpected.add("Qualifications");
        tabsExpected.add("Memberships");

//       assertion if all buttons are present
        for (int i = 0; i < tabsActual.size(); i++) {
            Assert.assertEquals(tabsActual.get(i), tabsExpected.get(i), "All tabs are correct");
        }

//      assertion if all links are present
        for (int i = 0; i < tabs.size(); i++) {
//            System.out.println((tabs.get(i).findElement(By.xpath(".//a")).getAttribute("href")));
            Assert.assertTrue((tabs.get(i).findElement(By.xpath(".//a")).getAttribute("href")).contains("https://opensource-demo.orangehrmlive.com/index.php/pim/") , "all tabs have the links");
        }

        System.out.println(tabsActual);
        System.out.println(tabsExpected);
    }
}


