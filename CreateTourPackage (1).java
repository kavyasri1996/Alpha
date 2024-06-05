package com.globalJourneys.travelHunk.adminTest;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.AdminHomePage;
import com.comcast.crm.objectrepositoryutility.CreateTourPackagePage;
import com.comcast.crm.objectrepositoryutility.Home;
import com.comcast.crm.objectrepositoryutility.UserHomepage;

@Listeners(com.comcast.crm.generic.listenerutility.ListImpClass.class)
public class CreateTourPackage extends BaseClass{

	@Test
	public  void CreateTouraPackageAndVerifyInUser() throws Throwable {
		UtilityClassObject.getTest().log(Status.INFO,"navigate to home page");
		Home hm=new Home(driver);
		
		UtilityClassObject.getTest().log(Status.INFO,"navigate to admin home page");
		hm.AdminLogin();
		AdminHomePage adm=new AdminHomePage(driver);
		WLib.mousemoveOnElement(driver,adm.getTourPackagelink());	
		adm.getCreateTourPackagelink().click();
		
		UtilityClassObject.getTest().log(Status.INFO,"create a tour paackage");
		CreateTourPackagePage page=new CreateTourPackagePage(driver);
		String PackageName = eLib.getDataFromExcel("Admin", 1, 1)+jLib.getRandomNumber();
		String PackageType = eLib.getDataFromExcel("Admin", 2, 1);
		String PackageLocation = eLib.getDataFromExcel("Admin", 3, 1);
		String PackagePriceinUSD = eLib.getDataFromExcel("Admin", 4, 1);
		String PackageFeatures = eLib.getDataFromExcel("Admin", 5, 1);
		String PackageDetails = eLib.getDataFromExcel("Admin", 6, 1);
		
		page.getPackagename().sendKeys(PackageName);
		page.getPackagetype().sendKeys(PackageType);
		page.getPackagelocation().sendKeys(PackageLocation);
		page.getPackageprice().sendKeys(PackagePriceinUSD);
		page.getPackagefeatures().sendKeys(PackageFeatures);
		page.getPackagedetails().sendKeys(PackageDetails);
		WebElement ele = driver.findElement(By.xpath("//input[@id='packageimage']"));
		ele.sendKeys("C://Users//Lenovo//Desktop//New folder (2)//images.jpeg");
		page.getCreatebutton().click();
		hm.AdminLogOut();
		adm.getBacktohome().click();
		
		UtilityClassObject.getTest().log(Status.INFO,"navigate to user home page");
		hm.UserLogin();
		UserHomepage user=new UserHomepage(driver);
		
		UtilityClassObject.getTest().log(Status.INFO,"verify tour package");
		user.getUserTourPackage().click();
		WLib.scrolltoelement(driver, user.getTourpackageDetailsbutton());
		Thread.sleep(2000);
		user.getTourpackageDetailsbutton().click();
		Thread.sleep(2000);
		String actualresult = user.getTourpackagename().getText();
		Assert.assertEquals(true, actualresult.contains(PackageName));
		
		
	}
	@Test
	public void CreateAndVerifyTourPackageCount() throws Throwable {
		UtilityClassObject.getTest().log(Status.INFO,"navigate to home page");
		Home hm=new Home(driver);
		UtilityClassObject.getTest().log(Status.INFO,"navigate to admin home page and get tourpakage count");
		hm.AdminLogin();
		AdminHomePage adm=new AdminHomePage(driver);
		String initialcount = adm.getTourPackageCount().getText();
//		System.out.println("element:"+ initialcount);
		
		UtilityClassObject.getTest().log(Status.INFO,"navigate to create tour package");
		WLib.mousemoveOnElement(driver,adm.getTourPackagelink());	
		adm.getCreateTourPackagelink().click();
		
		UtilityClassObject.getTest().log(Status.INFO,"create a tour paackage");
		CreateTourPackagePage page=new CreateTourPackagePage(driver);
		String PackageName = eLib.getDataFromExcel("Admin", 1, 1)+jLib.getRandomNumber();
		String PackageType = eLib.getDataFromExcel("Admin", 2, 1);
		String PackageLocation = eLib.getDataFromExcel("Admin", 3, 1);
		String PackagePriceinUSD = eLib.getDataFromExcel("Admin", 4, 1);
		String PackageFeatures = eLib.getDataFromExcel("Admin", 5, 1);
		String PackageDetails = eLib.getDataFromExcel("Admin", 6, 1);
		
		page.getPackagename().sendKeys(PackageName);
		page.getPackagetype().sendKeys(PackageType);
		page.getPackagelocation().sendKeys(PackageLocation);
		page.getPackageprice().sendKeys(PackagePriceinUSD);
		page.getPackagefeatures().sendKeys(PackageFeatures);
		page.getPackagedetails().sendKeys(PackageDetails);
		WebElement ele = driver.findElement(By.xpath("//input[@id='packageimage']"));
		ele.sendKeys("C://Users//Lenovo//Desktop//New folder (2)//images.jpeg");
		page.getCreatebutton().click();
		
		UtilityClassObject.getTest().log(Status.INFO,"get tour count after creating tour package");
		adm.getDashboard().click();
		String finalcount = adm.getTourPackageCount().getText();
		System.out.println("element"+ finalcount);
		if(finalcount!=initialcount) {
		System.out.println("tour package count is increased: pass");
		}else {
			System.out.println("tour package count is not increased: fail");
		}
	}
}
		
		
		
		
		
		
		
		
		
		
//		WebElement element= driver.findElement(By.xpath("//div[@class='room-bottom']/descendant::h4[.='Package Name: "+PackageName+"']"));
	
//		System.out.println("-- scroll --");
//		Actions act = new Actions(driver);
//		act.scrollToElement(element).perform();
//		
//		
//		System.out.println("package name"+ element.getText());
		//hm.UserLogOut();
		
//		List<WebElement> P_names = driver.findElements(By.xpath("/div[@class='room-bottom']/descendant::h4"));
//	
//		for(WebElement n:P_names)
//		{
//			String eachName = n.getText();
//			System.out.println(eachName);
//		}
	
	
	
	
