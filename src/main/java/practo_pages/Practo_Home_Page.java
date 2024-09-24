package practo_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import practo_base.Practo_Base;

public class Practo_Home_Page extends Practo_Base 
{
	By city = By.xpath("//input[@data-qa-id='omni-searchbox-locality']");
    By cross_mark = By.xpath("//i[@class='icon-ic_cross_solid']");
    By specialization = By.xpath("//*[@id='c-omni-container']/div/div[2]/div[1]/input");
    public void enter_city() throws Exception {
        WebElement Enter_city = driver.findElement(city);
        Enter_city.click();
        Thread.sleep(2000); 
        driver.findElement(cross_mark).click();
        Thread.sleep(3000);
        Enter_city.sendKeys(properties.getProperty("city"));
        Thread.sleep(3000);
        Enter_city.sendKeys(Keys.ARROW_DOWN);
        Enter_city.sendKeys(Keys.ARROW_DOWN);
        Enter_city.sendKeys(Keys.ENTER);
    }
    public void enter_specialization() throws Exception {
        WebElement speciality = driver.findElement(specialization);
        speciality.click();
        Thread.sleep(3000); 
        speciality.sendKeys(properties.getProperty("specalization"));
        Thread.sleep(3000); 
        speciality.sendKeys(Keys.ARROW_DOWN);
        speciality.sendKeys(Keys.ENTER);
        Thread.sleep(3000); 
    }

}
