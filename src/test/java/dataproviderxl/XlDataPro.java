package dataproviderxl;


import config.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class XlDataPro {


    @DataProvider( name = "Mustaxl")
    public Object[][] dataPro() throws IOException {

        Configuration config = new Configuration();

        XlReader xlr = new XlReader();

        String myXlPath = config.prop.getProperty("xlPath");
        String mySheet = config.prop.getProperty("sheetName");

        xlr.readXl(myXlPath,mySheet);

        Object[][] data = xlr.readXl(myXlPath,mySheet);

        return data;

    }


    @Test ( dataProvider = "Mustaxl")
    public void testOne(String first, String last){

        System.out.println( first +"    " + last);

        if (!(first==null) && !(last == null)){


            WebDriver driver = new ChromeDriver();
            driver.get("https://scaledupit.com/test2.html");
            driver.manage().window().maximize();

            driver.findElement(By.id("userm")).sendKeys(first);
            driver.findElement(By.id("passm")).sendKeys(last);


            driver.findElement(By.id("sone")).click();

            String expected = "Test"; // control f

            Assert.assertEquals(driver.getTitle(),expected);







    }






}}

