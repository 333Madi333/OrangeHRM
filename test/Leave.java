import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Leave {


    @Test(testName = "Leave")
    public void test() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");

        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();

        driver.findElement(By.xpath("//*[text()='Leave']")).click();
        String expected = "Leave List";
        String actual = driver.findElement(By.xpath("//div[@class='head']")).getText();
        Assert.assertEquals(actual, expected);

        driver.findElement(By.xpath("//*[text()='Apply']")).click();

        String expected1 = "No Leave Types with Leave Balance";
        String actual1 = driver.findElement(By.xpath("//div[@class='message warning']")).getText();
        Assert.assertEquals(actual1, expected1);

        driver.close();

    }

    @Test(testName = "Apply")
    public void test1() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");

        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();

        driver.findElement(By.xpath("//*[text()='Leave']")).click();
        driver.findElement(By.xpath("//*[text()='Apply']")).click();

        String expected = "No Leave Types with Leave Balance";
        String actual = driver.findElement(By.xpath("//div[@class='message warning']")).getText();
        Assert.assertEquals(actual, expected);

        driver.close();

    }


}
