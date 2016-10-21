package com.lrm.colline;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lrm.biz.BizHelper;
import com.lrm.data.*;

/*
 * Test case: login
 */
public class TC001 {

	public static void main(String[] args) {
		
DBHelper.getFctTestResult();
		
/*					
		BizHelper.startTime();	
		// selenium3+Firefox。在selenium3中，使用Firefox，需要添加驱动
		System.setProperty("webdriver.firefox.marionette", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");

		// TODO Auto-generated method stub
		WebDriver dr = new FirefoxDriver();

		// 设置timeout
		dr.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		// open colline link
		BizHelper.startTransaction("open COLLINE", System.currentTimeMillis());
		dr.get("http://54.183.168.17:8080/colline/");
		BizHelper.endTransaction(System.currentTimeMillis());

		// input username and password, then click the button Enter
		BizHelper.startTransaction("Login", System.currentTimeMillis());
		
		dr.findElement(By.name("j_username")).sendKeys("sa4");
		dr.findElement(By.name("j_password")).sendKeys("password");
		dr.findElement(By.name("submit")).click();
		
		BizHelper.endTransaction(System.currentTimeMillis());

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dr.quit();

		BizHelper.endTime();
		
*/
		
	}

}
