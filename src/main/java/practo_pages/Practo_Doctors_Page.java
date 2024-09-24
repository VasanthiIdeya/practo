package practo_pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import practo_base.Practo_Base;

	public class Practo_Doctors_Page extends Practo_Base
	{

	    //Locator for the book button (Book Clinic Visit)
	    By Doctors = By.xpath("//button[@data-qa-id=\"book_button\"]");
	    By days=By.xpath("//div[@class='u-pos-rel c-slots-header__daybar ']/div");
	    By slots=By.xpath("//div[@data-qa-id=\"slot_time\"]");
	    By filter=By.xpath("//*[@id=\"container\"]/div/div[3]/div/div/header/div[1]/div/div[4]");
	    
	    //By fee=By.xpath("//input[@type='radio' and value = '₹0-₹500']");
	    By fee=By.xpath("//*[@id=\"container\"]/div/div[3]/div/div/header/div[2]/div/div[2]/div/label[1]");
	    // Method to select a random doctor and return the doctor's name
	    public String select_doctor() throws Exception 
	    {
	        // Fetch all doctor booking buttons
	        List<WebElement> doctors_list = driver.findElements(Doctors);
	        Random rand = new Random();
	        int randomDoctorIndex = rand.nextInt(doctors_list.size()); 
	        doctors_list.get(randomDoctorIndex).click();
	        List<WebElement> doctor_names = driver.findElements(By.xpath("//h2[@class=\"doctor-name\"]"));
	        String selectedDoctorName = doctor_names.get(randomDoctorIndex-1).getText(); // Get the doctor's name at the random index
	        return selectedDoctorName; 
	    }
	    
	    //Method to select a slot 
	    public String select_slot() throws Exception
		{
			List<WebElement> day_list=driver.findElements(days);
			Thread.sleep(3000);
			String text="No Slots Available";
			//to select the the particular day
			for (int i = 0; i < day_list.size(); i++) 
			{
			    String text1 = driver.findElement(By.xpath("//div[@class='u-pos-rel c-slots-header__daybar ']/div[" + (i + 1) + "]/div[2]")).getText();
			    if (!text.equals(text1)) 
			    {
			        driver.findElement(By.xpath("//div[@class='u-pos-rel c-slots-header__daybar ']/div[" + (i + 1) + "]/div[1]")).click();
			        break;
			    }
			}
			Thread.sleep(3000);
			List<WebElement> slots_list=driver.findElements(slots);
			String slot_time=slots_list.get(0).getText();
			slots_list.get(0).click();
			slot_time = slot_time.replaceFirst("^0", "");
			return slot_time;
		}
		
	    //method for filteration
	    public void apply_filter()
	    {
	    	driver.findElement(filter).click();
	    	driver.findElement(fee).click();
	    }
	}
