package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_XPath {
		WebDriver driver;
		String projectPath = System.getProperty("user.dir");
		String osName = System.getProperty("os.name");
		
		@BeforeClass
		public void beforeClass() {
			if (osName.contains("MAC OS")) {
				System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			}
			else {
				System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			}

			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
		
		@Test
		public void TC_01_Empty_Data() {
			// Navigate to page
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			//Action
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click(); 
			
			//Verify
			Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
			Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
			Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
			Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
			Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
			Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");


			
			
		}
		
		@Test
		public void TC_02_Invalid_Email() {
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			//Action
			driver.findElement(By.id("txtFirstname")).sendKeys("PBou17386"); 
			driver.findElement(By.id("txtEmail")).sendKeys("123$5672"); 
			driver.findElement(By.id("txtCEmail")).sendKeys("123$5672"); 
			driver.findElement(By.id("txtPassword")).sendKeys("PBou17386"); 
			driver.findElement(By.id("txtCPassword")).sendKeys("PBou17386"); 
			driver.findElement(By.id("txtPhone")).sendKeys("08363759367"); 
			
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click(); 
			//Verify
			Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
			Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
			
		}
		@Test
		public void TC_03_Incorrect_Confirm_Email() {
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			//Action
			driver.findElement(By.id("txtFirstname")).sendKeys("PBou17386"); 
			driver.findElement(By.id("txtEmail")).sendKeys("John@gmai.net"); 
			driver.findElement(By.id("txtCEmail")).sendKeys("John@gmai.com"); 
			driver.findElement(By.id("txtPassword")).sendKeys("PBou17386"); 
			driver.findElement(By.id("txtCPassword")).sendKeys("PBou17386"); 
			driver.findElement(By.id("txtPhone")).sendKeys("08363759367"); 
			
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click(); 
			//Verify
			Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
			
		}

		@Test
		public void TC_04_Invalid_Password() {
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			//Action
			driver.findElement(By.id("txtFirstname")).sendKeys("PBou17386"); 
			driver.findElement(By.id("txtEmail")).sendKeys("John@gmai.net"); 
			driver.findElement(By.id("txtCEmail")).sendKeys("John@gmai.net"); 
			driver.findElement(By.id("txtPassword")).sendKeys("PB"); 
			driver.findElement(By.id("txtCPassword")).sendKeys("PB"); 
			driver.findElement(By.id("txtPhone")).sendKeys("08363759367");
			
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click(); 
			//Verify
			Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
			Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");


		}
		@Test
		public void TC_05_Incorrect_Confirm_Password() {
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			//Action
			driver.findElement(By.id("txtFirstname")).sendKeys("PBou17386"); 
			driver.findElement(By.id("txtEmail")).sendKeys("John@gmai.net"); 
			driver.findElement(By.id("txtCEmail")).sendKeys("John@gmai.net"); 
			driver.findElement(By.id("txtPassword")).sendKeys("PB12e8hh"); 
			driver.findElement(By.id("txtCPassword")).sendKeys("PB12e8hh21f1"); 
			driver.findElement(By.id("txtPhone")).sendKeys("08363759367");
			
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click(); 
		//Verify
			Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");

		}

		@Test
		public void TC_06_Invalid_PhoneNumber() {
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			//Action 1
			driver.findElement(By.id("txtFirstname")).sendKeys("PBou17386"); 
			driver.findElement(By.id("txtEmail")).sendKeys("John@gmai.net"); 
			driver.findElement(By.id("txtCEmail")).sendKeys("John@gmai.net"); 
			driver.findElement(By.id("txtPassword")).sendKeys("PB12e8hh"); 
			driver.findElement(By.id("txtCPassword")).sendKeys("PB12e8hh"); 
			driver.findElement(By.id("txtPhone")).sendKeys("083637593674");
			
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click(); 
			//Verify 1
			Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
			//Action 2
			driver.findElement(By.id("txtPhone")).clear();
			driver.findElement(By.id("txtPhone")).sendKeys("123456");
			//Verify 2
			Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

		}
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}




}
		
