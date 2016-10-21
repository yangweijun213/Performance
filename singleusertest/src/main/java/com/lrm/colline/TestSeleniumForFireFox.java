package com.lrm.colline;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/*
 * selenium3+Firefox
 */
public class TestSeleniumForFireFox {
	
	public static void main(String[] args) {
		
		//selenium3+Firefox。在selenium3中，使用Firefox，需要添加驱动
		System.setProperty("webdriver.firefox.marionette","C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
		
		// TODO Auto-generated method stub
		WebDriver dr =  new FirefoxDriver();
		
		//设置timeout
		dr.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		
		dr.get("http://www.baidu.com");
		dr.findElement(By.id("kw")).sendKeys("hello Selenium");
		dr.findElement(By.id("su")).click();
		try {
		Thread.sleep(3000);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		dr.quit();
		}

}

