package practo_pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import practo_base.Practo_Base;

public class Practo_Confirmation_Page extends Practo_Base
{
	//method to check the date
	public static LocalDate validateDate() 
	{
	    
	    WebElement dateElement = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[1]/span[2]"));
	    String dateText = dateElement.getText();  
	    System.out.println(dateText);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);  
	    try 
	    {
	        LocalDate Date = LocalDate.parse(dateText, formatter);
	        System.out.println("Parsed LocalDate: " + Date);
	        return Date;
	    } 
	    catch (DateTimeParseException e) 
	    {
	        System.out.println("Failed to parse date: " + e.getMessage());
	        return null;  	    
	        }
	}
}
	 
