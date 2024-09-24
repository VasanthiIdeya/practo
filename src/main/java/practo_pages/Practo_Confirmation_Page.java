package practo_pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import practo_base.Practo_Base;

public class Practo_Confirmation_Page extends Practo_Base
{
	//method to confirm the date
	public static LocalDate validateDate() 
	{
	    
	    WebElement dateElement = driver.findElement(By.xpath("//*[@id=\"container\"]/div[2]/div/div[1]/div/div[1]/div[2]/div/div"));
	    String dateText = dateElement.getText();  
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
	       
	        LocalDate Date = LocalDate.parse(dateText, formatter);
	        return Date;
	}}
	 
