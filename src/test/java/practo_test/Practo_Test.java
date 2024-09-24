package practo_test;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import practo_base.Practo_Base;
import practo_pages.Practo_Confirmation_Page;
import practo_pages.Practo_Doctors_Page;
import practo_pages.Practo_Home_Page;


public class Practo_Test extends Practo_Base
{
Practo_Home_Page home = new Practo_Home_Page();
Practo_Doctors_Page doctors = new Practo_Doctors_Page();
Practo_Confirmation_Page confirm = new Practo_Confirmation_Page();
ExtentTest test;
By actualslot = By.xpath("//*[@id='container']/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[2]/span[2]");
By actualdoctor=By.xpath("//*[@id='container']/div[2]/div/div[1]/div/div[1]/div[3]/div/div[2]/div[1]");
@Test(priority = 1)
public void check_speciality() throws Exception
{
	test=report.createTest("checking every doctor speciality");
	Practo_Base.Open_URL();
	home.enter_city();
	home.enter_specialization();
	String expectedSpecialization = "dentist";
    List<WebElement> doctor_names = driver.findElements(By.xpath("//div[@class=\"u-d-flex\"]/span"));
    boolean allMatched = true;
    for(WebElement we : doctor_names)
    {
    	String doctorSpecialization=we.getText();
    	if (!doctorSpecialization.equalsIgnoreCase(expectedSpecialization)) 
    	{
            System.out.println("failed");
            allMatched = false;
            test.log(Status.FAIL, "some doctors donot have the expected specialization");
            break;
        } 
    }
    Assert.assertTrue(allMatched, "Some doctors do not have the expected specialization");
    if(allMatched=true)
    {
    	test.log(Status.PASS, "All doctors have same speciality");
    }

}

@Test(priority = 2)

public void check_name_time_date() throws Exception
{
	test=report.createTest("checking name and time of selected doctor with the actual doctor");
	Practo_Base.Open_URL();
	home.enter_city();
	home.enter_specialization();
	String SelectedDoctor = doctors.select_doctor();
	String SelectedSlot = doctors.select_slot();
	String actual_slot=driver.findElement(actualslot).getText();
	System.out.println(actual_slot);
	String actual_doctor_name=driver.findElement(actualdoctor).getText();
	System.out.println(actual_doctor_name);
	LocalDate selectedDate = Practo_Confirmation_Page.validateDate();
	LocalDate today = LocalDate.now();   
	LocalDate tomorrow = today.plusDays(1);
	if(actual_doctor_name.matches(SelectedDoctor) && actual_slot.matches(SelectedSlot) && (selectedDate.equals(today) || selectedDate.equals(tomorrow)))
	{
		test.log(Status.PASS, "Doctor name,slot and date is matched");
		System.out.println("Doctor name & slot matched");
		
	}
	else
	{
		test.log(Status.FAIL,"Doctor name and slot is not matched");
	}
	
}
@Test(priority = 3)
public void check_filteration() throws Exception
{
	test=report.createTest("Applying filter and checking whether it applies or not");
	Practo_Base.Open_URL();
	home.enter_city();
	home.enter_specialization();
	doctors.apply_filter();
	Thread.sleep(5000);
	List<WebElement> doctorsfee = driver.findElements(By.xpath("//span[@data-qa-id='consultation_fee']"));
	boolean allDoctorsInRange = true;
    for (WebElement doctor : doctorsfee)
    {
          String feeText = doctor.getText(); 
          if (feeText.equalsIgnoreCase("Free Consultation")) 
          {
        	  test.log(Status.INFO, "Skipping 'Free Consultation' doctor");
              continue; 
          }
          
          int fee = Integer.parseInt(feeText.replaceAll("[^0-9]", ""));
          if (fee < 0 || fee > 500) 
          {
              allDoctorsInRange = false;
              System.out.println("failed");
              test.log(Status.FAIL,"All doctors doesnot have same range");
              break;
          }
      }
    if(allDoctorsInRange)
    {
    	test.log(Status.PASS,"All doctors are in the same range");
    }
      System.out.println("passed");
}
}
