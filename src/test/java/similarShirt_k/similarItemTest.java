package similarShirt_k;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.Assert;
import base.BaseTest;
import base.DriverFactory;
import similarShirt_k.similarItem;

public class similarItemTest extends BaseTest {
	
	similarItem item;
	WebElement bestSimilar;
	
	
  @Test(priority = 1)
  public void verify_similaritems() {
	item = new similarItem(DriverFactory.getDriver());
	System.out.println("Similar Items of the best product are : "  + item.similarProducts().size());
	
	List<WebElement> shirts = item.similarProducts();
	
	for(WebElement shirt : shirts) {
		
		List<WebElement> nameShirt = shirt.findElements(By.xpath(".//h3[contains(@class,'product-brand')]"));

		List<WebElement> priceShirt = shirt.findElements(By.xpath(".//span[contains(@class,'product-discountedPrice')]"));
	
		if(nameShirt.size() > 0 && priceShirt.size() > 0) {
			String name = nameShirt.get(0).getText();
			String price = priceShirt.get(0).getText();
		
			System.out.print(name + " | " + price);
		}
	
			System.out.println();
	
	}
  }
  
  @Test(priority  = 2)
  public void bestSimilarItem() {
	  List<WebElement> shirts = item.similarProducts();

	  double minimumPrice = Double.MAX_VALUE;
	  String bestProduct = "";

	  for(WebElement shirt : shirts) {

	      try {

	          List<WebElement> nameShirt = shirt.findElements(By.xpath(".//h3[contains(@class,'product-brand')]"));

	          List<WebElement> priceShirt = shirt.findElements(By.xpath(".//span[contains(@class,'product-discountedPrice')]"));

	          if(nameShirt.size() > 0 && priceShirt.size() > 0) {

	              String name = nameShirt.get(0).getText();
	              String priceText = priceShirt.get(0).getText();

	              priceText = priceText.replace("Rs.", "").trim();

	              int price = Integer.parseInt(priceText);

	              if(price < minimumPrice) {
	                  minimumPrice = price;
	                  bestProduct = name;
	                  bestSimilar = shirt;
	              }
	          }

	      } catch (Exception e) {
	          // skip
	      }
	  }

	  System.out.println("Cheapest Product: " + bestProduct + " | " + minimumPrice);
	  
	  String parentWindow = DriverFactory.getDriver().getWindowHandle();

	// click on anchor tag inside product
	WebElement productLink = bestSimilar.findElement(By.tagName("a"));
	productLink.click();

	// switch to new tab
	Set<String> windows = DriverFactory.getDriver().getWindowHandles();

	for(String window : windows){
	    if(!window.equals(parentWindow)){
	    	DriverFactory.getDriver().switchTo().window(window);
	    }
	}
		
	
  }
}