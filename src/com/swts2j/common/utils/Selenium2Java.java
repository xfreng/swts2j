package com.swts2j.common.utils;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium2Java {
	private final static long PAUSE_TIME = 5000L;

	public static void get51JobCV(String baseDir, String userName, String userPass) {
		String osName = System.getProperty("os.name");
		String osVersion = System.getProperty("os.version");
		System.out.println("操作系统名称:" + osName);
		System.out.println("操作系统版本:" + osVersion);
		String url = "https://login.51job.com/login.php?lang=c&url=http%3A%2F%2Fwww.51job.com%2F";
		String key = "webdriver.chrome.driver";
		String value = baseDir + File.separator + "/chromedriver.exe";
		if (osName.contains("Linux")) {
			value = baseDir + File.separator + "chromedriver";
		}
		System.setProperty(key, value);
		WebDriver driver = new ChromeDriver();
		try {
			driver.manage().window().maximize();
			driver.get(url);
			try {// 等待加载完成
				Thread.sleep(PAUSE_TIME);
			} catch (InterruptedException e) {
				driver.quit();
			}
			driver.findElement(By.name("loginname")).sendKeys(userName);
			driver.findElement(By.name("password")).sendKeys(userPass);
			System.out.println(url);
			WebElement element = driver.findElement(By.id("login_btn"));
			element.submit();
			try {// 等待加载完成
				Thread.sleep(PAUSE_TIME);
			} catch (InterruptedException e) {
				driver.quit();
			}
			// 我的51job
			driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[3]/ul/li[1]/span/a")).click();
			try {// 等待加载完成
				Thread.sleep(PAUSE_TIME);
			} catch (InterruptedException e) {
				driver.quit();
			}
			// 简历中心
			driver.findElement(By.xpath("/html/body/div[3]/div[2]/a[2]/span")).click();
			try {// 等待加载完成
				Thread.sleep(PAUSE_TIME);
			} catch (InterruptedException e) {
				driver.quit();
			}
			// 简历名称
			driver.findElement(By.xpath("//*[@id=\"resume_62612481\"]/ul/li[1]/a")).click();
			try {// 等待加载完成
				Thread.sleep(PAUSE_TIME);
			} catch (InterruptedException e) {
				driver.quit();
			}
			// 关闭改版提示层
			WebElement layer = driver.findElement(By.xpath("//*[@id=\"layer_id\"]/div/div/span"));
			if (layer != null) {
				layer.click();
			}
			try {// 等待加载完成
				Thread.sleep(PAUSE_TIME);
			} catch (InterruptedException e) {
				driver.quit();
			}
			// 预览
			driver.findElement(By.xpath("//*[@id=\"resume\"]/div[3]/span[2]")).click();
			String currentWindow = driver.getWindowHandle();// 获取当前窗口句柄
			Set<String> handles = driver.getWindowHandles();// 获取所有窗口句柄
			Iterator<String> it = handles.iterator();
			while (it.hasNext()) {
				if (currentWindow == it.next()) {
					continue;
				}
				WebDriver window = driver.switchTo().window(it.next());// 切换到新窗口
				try {// 等待加载完成
					Thread.sleep(PAUSE_TIME);
				} catch (InterruptedException e) {
					driver.quit();
				}
				// 导出
				driver.findElement(By.xpath("/html/body/table[1]/tbody/tr/td[3]/a")).click();
				try {// 等待加载完成
					Thread.sleep(PAUSE_TIME);
				} catch (InterruptedException e) {
					driver.quit();
				}
				// 确定
				driver.findElement(By.xpath("//*[@id=\"this_export\"]")).click();
				try {// 简历下载完成
					Thread.sleep(PAUSE_TIME);
				} catch (InterruptedException e) {
					driver.quit();
				}
				window.close();
			}
			driver.switchTo().window(currentWindow);// 回到原来页面
		} catch (Exception e) {
			driver.quit();
		}
		try {// 所有操作完成后
			Thread.sleep(PAUSE_TIME);
			driver.quit();
		} catch (InterruptedException e) {
			driver.quit();
		}
	}
}
