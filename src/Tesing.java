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
	
	public static void check_cities(String url, BufferedWriter BW_void, Int check_full) throws IOException, InterruptedException {

		
	System.setProperty("Webdriver.chrome.driver", "C:\\selenium-2.51.0\\chromedriver.exe");
    
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    capabilities.setCapability("chrome.switches", Arrays.asList("--load-extension=C:\\Users\\admin\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\"));
    WebDriver driver = new ChromeDriver(capabilities);
  
    String TestFile = "result.txt";
    
    File FC = new File(TestFile);
       
   /* FC.createNewFile();
    FileWriter FW = new FileWriter(TestFile);
    BufferedWriter BW2 = new BufferedWriter(FW);

    Wini ini = new Wini(new File("settings.ini"));
    */
   // String url = ini.get("main settings", "url", String.class);
    driver.get(url);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
       
	Thread.sleep(WAIT_TIME);
	    
	String totalCity = driver.findElement(By.className("listingsTotal")).getText();    
	String totalCity_str = totalCity.replaceAll("[^\\d.]", "");  
	int totalCity_int = Integer.parseInt(totalCity_str);
	      
	int totalCity_split = 0;
	      
	List <WebElement> ElementCity = driver.findElements(By.className("cityBox"));
	       
	for (int k = 0; k < ElementCity.size(); k++) {
	       	
	       Thread.sleep(WAIT_TIME);
           
       ElementCity = driver.findElements(By.className("cityBox"));            
       ElementCity.get(k).click();
           
       Thread.sleep(WAIT_TIME);
           
       String actualTitle = driver.getTitle();
                   
       String AllCity = driver.findElement(By.className("allCityListingsTotal")).getText();        
       int AllCity_int = Integer.parseInt(AllCity);   
          
       int fullcheck = check_full;//ini.get("main settings", "full_check", int.class);
           
       if (fullcheck != 0) {   	
           
           String AllCityNum_str = driver.findElement(By.className("allCityListingsTotal")).getText();
           AllCityNum_str = AllCityNum_str.replaceAll("[^\\d.]", "");  
           int AllCityNum_int = Integer.parseInt(AllCityNum_str);
           driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);    
           int AllCityNumByZip_int3 = 0;
               
           Thread.sleep(WAIT_TIME);
               
           List <WebElement> Element = driver.findElements(By.className("zipNameBox"));         
               
           for (int j = 0; j < Element.size(); j++) {
               	
               Thread.sleep(WAIT_TIME);
                   
               Element = driver.findElements(By.className("zipNameBox"));    
                   
               Thread.sleep(WAIT_TIME);   
                   
               String AllCityNum_str2 = Element.get(j).getText();                 
               Element.get(j).click();
                   
               Thread.sleep(WAIT_TIME);
                   
               String AllCityNum_str1 = driver.findElement(By.className("listingsTotal")).getText();
               AllCityNum_str1 = AllCityNum_str1.replaceAll("[^\\d.]", "");
               int AllCityNum_int1 = Integer.parseInt(AllCityNum_str1);
               List<WebElement> drop = driver.findElements(By.className("zipListingsNum"));           
               java.util.Iterator<WebElement> i = drop.iterator();
               int AllCityNumByZip_int = 0;                 
                   
               while(i.hasNext()) {
                   	
                    Thread.sleep(WAIT_TIME);
                      
                    WebElement row = i.next();   
                    String AllCityNumByZip_str = row.getText().replaceAll("\\p{P}","");
                    AllCityNumByZip_int = Integer.parseInt(AllCityNumByZip_str) + AllCityNumByZip_int;
                                    }     
                   
                    if (AllCityNumByZip_int == AllCityNum_int1)  {
                    System.out.println(actualTitle+" splitted by type zip test: "+AllCityNum_str2+" passed "+AllCityNum_int1+"=="+AllCityNumByZip_int);
                //  BW2.write(actualTitle+" splitted by type zip test: passed "+AllCityNum_int1+"!="+AllCityNumByZip_int+ "\n");  
        
                   											 } 
                   	else {
		                    System.out.println(actualTitle+" splitted by type zip test: "+AllCityNum_str2+" failed "+AllCityNum_int1+"!="+AllCityNumByZip_int);
		                    BW_void.write(actualTitle+" splitted by type zip test: failed "+AllCityNum_int1+"!="+AllCityNumByZip_int+ "\n");    
                          	 }
                    AllCityNumByZip_int3 += AllCityNum_int1;
                   
                    Thread.sleep(WAIT_TIME);   
                   
                    driver.navigate().back();  
                   
                    Thread.sleep(WAIT_TIME);
                                                           } 
                    if (AllCityNumByZip_int3 == AllCityNum_int)  {
                   	
                       System.out.println(actualTitle+" splitted by zips city test: passed "+AllCityNumByZip_int3+"=="+AllCityNum_int);
                 //    BW2.write(actualTitle+" splitted by zips city test: passed "+AllCityNumByZip_int3+"!="+AllCityNum_int+ "\n");  
                  
                   											} 
                   	else {
	                        System.out.println(actualTitle+" splitted by zips city test: failed "+AllCityNumByZip_int3+"!="+AllCityNum_int);
	                        BW_void.write(actualTitle+" splitted by zips city test: failed "+AllCityNumByZip_int3+"!="+AllCityNum_int+ "\n");    
	                        
                             }
                   
                   Thread.sleep(WAIT_TIME);  
                   
                     }			// END if (fullcheck)
                 driver.navigate().back();        
                   //Thread.sleep(WAIT_TIME);
                   
             totalCity_split += AllCity_int;
             
       }

               if (totalCity_split == totalCity_int)  {
               	
	                System.out.println("all listings splitted by cities test: passed "+totalCity_split+"=="+totalCity_int);
	        //      BW2.write("all listings splitted by cities test: passed "+totalCity_split+"=="+totalCity_int);
	                									} 
               			else {
					              System.out.println("all listings splitted by cities test: failed "+totalCity_split+"!="+totalCity_int);
					              BW_void.write("all listings splitted by cities test: failed "+totalCity_split+"!="+totalCity_int+ "\n");
					             } 
               BW_void.close();
               }
	
	public static void main(String[] args) throws IOException, InterruptedException {

	    // For work in IE you must remove PROTECTED MODE from all zones !!!
	   /*  System.setProperty("webdriver.ie.driver", "C:\\selenium-2.51.0\\IEDriverServer.exe");
	       
	       WebDriver driver = new InternetExplorerDriver();*/
	       
	        System.setProperty("Webdriver.chrome.driver", "C:\\selenium-2.51.0\\chromedriver.exe");
	       
	         DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	         capabilities.setCapability("chrome.switches", Arrays.asList("--load-extension=C:\\Users\\admin\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\"));
	         WebDriver driver = new ChromeDriver(capabilities);
	       
	         String TestFile = "result.txt";
	         
	         File FC = new File(TestFile);
	            
	         FC.createNewFile();
	         FileWriter FW = new FileWriter(TestFile);
	         BufferedWriter BW2 = new BufferedWriter(FW);

	         Wini ini = new Wini(new File("settings.ini"));
	         String url1 = ini.get("main settings", "url1", String.class);
	         driver.get(url1);
	         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		        
	         Thread.sleep(WAIT_TIME);
		 
	         String totalSale = driver.findElement(By.className("listingsTotal")).getText();    
	         String totalSale_str = totalSale.replaceAll("[^\\d.]", "");  
	         int totalSale_int = Integer.parseInt(totalSale_str);
		 
	       		 
		 		if (totalSale_int > 1000)  {
		 			System.out.println("sale is more than 10.000 houses");
		 			BW2.write("sale is more than 10.000 houses"+ "\n");
		 		
         	               			   		} 
		 			else {
		 				System.out.println("sale is less than 10.000 houses");
		 				BW2.write("sale is less than 10.000 houses"+ "\n");
		 				}
		 			 			 	
		 				String url2 = ini.get("main settings", "url2", String.class);
		 				driver.get(url2);
		 				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 
		 				Thread.sleep(WAIT_TIME);
			 
		 				String totalRent = driver.findElement(By.className("listingsTotal")).getText();    
		 				String totalRent_str = totalRent.replaceAll("[^\\d.]", "");  
		 				int totalRent_int = Integer.parseInt(totalRent_str);
			 
			 		 
		 				if (totalRent_int > 700)  {
		 					System.out.println("rent is more than 700 houses");
		 					BW2.write("rent is more than 700 houses"+ "\n");
			 		
	         	               			   } 
		 				else {
		 					System.out.println("rent is less than 700 houses");
		 					BW2.write("rent is less than 700 houses"+ "\n");
	                  		}
			 	
			 	
			 				if (totalSale_int > totalRent_int)  {
			 					System.out.println("rent is less than sale");
			 					BW2.write("rent is less than sale"+ "\n");
			 		
	         	               			   } 
			 					else {
			 						System.out.println("sale is less than rent");
			 						BW2.write("sale is less than rent"+ "\n");
			 					}
					 	
			 				
			 					
			 				//driver.get(url1);
			 				//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 				
			 				
			 				
			 				check_cities("http://www.realtyadvisorselite.com/apartments-for-rent", BW2, check_full);
			 				
			 				
			 				
			 				check_cities("http://www.realtyadvisorselite.com/homes-for-sale", BW2, check_full);
			 				
			 				BW2.close();
			 				
			 				FW.close();
			                if(FC.length() == 0){
			 					new File(TestFile).delete();
			 									}
			 				
			 				
			 			//	String AllCityNum_str1 = driver.findElement(By.className("cityListingsNum")).getText();
			 			//	int AllCityNum_int1 = Integer.parseInt(AllCityNum_str1);
			 			//	System.out.println(AllCityNum_int1);
		
			 				
			 				
	}


	
}
