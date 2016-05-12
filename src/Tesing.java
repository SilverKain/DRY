import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

import org.ini4j.Wini;

public class Tesing {

	public static final Integer WAIT_TIME = 1000;

	public static void check_cities(String url, BufferedWriter BW_void, WebDriver driver)
			throws IOException, InterruptedException {

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(WAIT_TIME);

		int AllCityNumByZip_int4 = 0;

		List<WebElement> Elements = driver.findElements(By.className("cityListingsNum"));
		for (int h = 0; h < Elements.size(); h++) {
			// Thread.sleep(WAIT_TIME);
			Elements = driver.findElements(By.className("cityListingsNum"));
			String AllCity_str = Elements.get(h).getText();
			int AllCity_int = Integer.parseInt(AllCity_str);
			AllCityNumByZip_int4 += AllCity_int;
		}

		String totalCity = driver.findElement(By.className("listingsTotal")).getText();
		String totalCity_str = totalCity.replaceAll("[^\\d.]", "");
		int totalCity_int = Integer.parseInt(totalCity_str);

		/*
		 * if (url == "http://www.realtyadvisorselite.com/homes-for-sale") {
		 * AllCityNumByZip_int4 +=1; }
		 */
		if (AllCityNumByZip_int4 == totalCity_int) {

			System.out.println(
					"all listings splitted by cities test: passed " + AllCityNumByZip_int4 + "==" + totalCity_int);
			// BW_void.write("all listings splitted by cities test: passed
			// "+AllCityNumByZip_int4+"=="+totalCity_int);
		} else {
			System.out.println(
					"all listings splitted by cities test: failed " + AllCityNumByZip_int4 + "!=" + totalCity_int);
			BW_void.write("all listings splitted by cities test: failed " + AllCityNumByZip_int4 + "!=" + totalCity_int
					+ "\n");
		}

	}

	public static void main(String[] args) throws IOException, InterruptedException {

		System.setProperty("Webdriver.chrome.driver", "C:\\selenium-2.51.0\\chromedriver.exe");

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.switches", Arrays.asList(
				"--load-extension=C:\\Users\\admin\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\"));
		WebDriver driver = new ChromeDriver(capabilities);

		String TestFile = "result.txt";

		File FC = new File(TestFile);

		FC.createNewFile();
		FileWriter FW = new FileWriter(TestFile);
		BufferedWriter BW2 = new BufferedWriter(FW);

		Wini ini = new Wini(new File("settings.ini"));
		String url_sale = ini.get("main settings", "url_sale", String.class);
		driver.get(url_sale);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(WAIT_TIME);

		String totalSale = driver.findElement(By.className("listingsTotal")).getText();
		String totalSale_str = totalSale.replaceAll("[^\\d.]", "");
		int totalSale_int = Integer.parseInt(totalSale_str);

		int amount_sale = ini.get("main settings", "amount_sale", int.class);

		if (totalSale_int > amount_sale) {
			System.out.println("sale is more than 10.000 houses");
			// BW2.write("sale is more than 10.000 houses"+ "\n");

		} else {
			System.out.println("sale is less than 10.000 houses");
			BW2.write("sale is less than 10.000 houses" + "\n");
		}

		String url_rent = ini.get("main settings", "url_rent", String.class);
		driver.get(url_rent);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(WAIT_TIME);

		String totalRent = driver.findElement(By.className("listingsTotal")).getText();
		String totalRent_str = totalRent.replaceAll("[^\\d.]", "");
		int totalRent_int = Integer.parseInt(totalRent_str);

		int amount_rent = ini.get("main settings", "amount_rent", int.class);

		if (totalRent_int > amount_rent) {
			System.out.println("rent is more than 700 houses");
			// BW2.write("rent is more than 700 houses"+ "\n");
		} else {
			System.out.println("rent is less than 700 houses");
			BW2.write("rent is less than 700 houses" + "\n");
		}

		if (totalSale_int > totalRent_int) {
			System.out.println("rent is less than sale");
			// BW2.write("rent is less than sale"+ "\n");
		} else {
			System.out.println("sale is less than rent");
			BW2.write("sale is less than rent" + "\n");
		}

		check_cities(url_sale, BW2, driver);
		check_cities(url_rent, BW2, driver);

		BW2.close();
		FW.close();

		if (FC.length() == 0) {
			new File(TestFile).delete();
		}

	}

}
