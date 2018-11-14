package com.proj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static WebDriver driver;

    public static void clickOnCreateComputer(){
        WebElement btn = driver.findElement(By.xpath("//*[@id='main']/form/div/input"));
        performActionClickBtn(btn);
        }

    public static void clickOnAddComputer(){
        driver.findElement(By.xpath("//*[@id='add']")).click();
    }

    public static void performActionSendKeys(WebElement element, String text){
        Actions performAct = new Actions(driver);
        performAct.sendKeys(element, text).build().perform();
    }

    public static void performActionClickBtn(WebElement element){
        Actions performAct = new Actions(driver);
        performAct.click(element).build().perform();
    }

    public static void addComputer(String compName){
        WebElement filed = driver.findElement(By.xpath("//*[@id='name']"));
        performActionSendKeys(filed, compName);


    }

    public static void addIntroducedDate(String intDate){
        WebElement filed = driver.findElement(By.xpath("//*[@id='introduced']"));
        performActionSendKeys(filed, intDate);
    }

    public static void addDiscontinuedDate(String discDate){
        WebElement filed = driver.findElement(By.xpath("//*[@id='discontinued']"));
        performActionSendKeys(filed, discDate);
    }

    public static void chooseCompany(String companyName){
        Select select = new Select(driver.findElement(By.id("company")));
        select.selectByVisibleText(companyName);
    }


    public static void setPreConditions(){
        System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://computer-database.herokuapp.com/computers");
        driver.manage().window().maximize();
    }

    @Test
    public void createComputer() throws InterruptedException {
            String compName = "comp1";
            String intrDate = "1999-12-31";
            String discDate = "2000-01-01";
            String company = "IBM";

            setPreConditions();
            clickOnAddComputer();

            addComputer(compName);
            addIntroducedDate(intrDate);
            addDiscontinuedDate(discDate);
            chooseCompany(company);
            clickOnCreateComputer();

            Assert.assertEquals(driver.findElement(By.xpath("//*[@id='main']/div[1]/strong")).getText(), "Done!");



//        String dep = driver.findElement(By.xpath("//*[@id='booking-form--flight-panel']/div[2]/div[2]/div[1]/div[2]")).getText();
//        String arr = driver.findElement(By.xpath("//*[@id='booking-form--flight-panel']/div[2]/div[2]/div[2]/div[2]")).getText();
//        //String dep = driver.findElement(By.id("air-date-departure")).getText();
//        System.out.println("DEP: " + dep);
//        System.out.println("ARR: " + arr);
//
//        convertDate(dep);
//        convertDate(arr);

        driver.quit();
    }
}






