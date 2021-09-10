import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Admin {
    @Test(testName = "Admin")
    public void test3() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");

        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();

        driver.findElement(By.xpath("//*[text()='Admin']")).click();
        driver.findElement(By.xpath("//*[text()='Nationalities']")).click();
        driver.findElement(By.id("btnAdd")).click();
        driver.findElement(By.xpath("//input[@class='formInput']")).sendKeys("AAAKaz");
        driver.findElement(By.id("btnSave")).click();

        String expected = "AAAKaz";
        String actual = driver.findElement(By.xpath("//*[text()='AAAKaz']")).getText();
        Assert.assertEquals(actual, expected);

        driver.close();

    }

    @Test(testName = "User")
    public void test4() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");

        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();

        driver.findElement(By.xpath("//*[text()='Admin']")).click();
        driver.findElement(By.xpath("//*[text()='User Management']")).click();

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id("menu_admin_UserManagement"))).perform();
        driver.findElement(By.id("menu_admin_viewSystemUsers")).click();

        driver.findElement(By.id("btnAdd")).click();

        WebElement sel = driver.findElement(By.id("systemUser_userType"));
        Select select = new Select(sel);
        select.selectByValue("1");
        driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("Alice Duval");
        driver.findElement(By.id("systemUser_userName")).sendKeys("AmmAAbbbbaaabbbaabbaaa1234567809");
        driver.findElement(By.id("systemUser_password")).sendKeys("Abc+123+321");
        driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("Abc+123+321");
        Thread.sleep(1000);
        driver.findElement(By.id("btnSave")).click();

        Thread.sleep(2000);
        List <WebElement> users = driver.findElements(By.xpath("//tr/td/a"));
        int beforeDel = users.size();


        driver.findElement(By.xpath("//*[text()='AmmAAbbbbaaabbbaabbaaa1234567809']/parent::td/preceding-sibling::td")).click();
        driver.findElement(By.id("btnDelete")).click();
        driver.findElement(By.id("dialogDeleteBtn")).click();

        List <WebElement> users1 = driver.findElements(By.xpath("//tr/td/a"));
        int afterDel = users1.size();

        Assert.assertNotEquals(afterDel, beforeDel);
        System.out.println(beforeDel);
        driver.close();
    }

    @Test(testName = "AdminRole")
    public void test5() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");

        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();

        driver.findElement(By.xpath("//*[text()='Admin']")).click();
        driver.findElement(By.xpath("//*[text()='User Management']")).click();

        WebElement sel = driver.findElement(By.id("searchSystemUser_userType"));
        Select select = new Select(sel);
        select.selectByValue("1");

        driver.findElement(By.id("searchBtn")).click();

        String expected = "Admin";
        List<WebElement> role = driver.findElements(By.xpath("//tr/td[3]"));
        for (int i = 0; i < role.size(); i++) {
            Assert.assertEquals(expected, role.get(i).getText());
        }

        driver.close();
    }
}
