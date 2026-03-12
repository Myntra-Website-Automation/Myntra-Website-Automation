package searchShirt_k;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;
import searchShirt_k.searchItem;

public class searchItemTest extends BaseTest {
	
	searchItem search;
	
  @Test
  public void verify_search() {
	  search = new searchItem(DriverFactory.getDriver());
	  search.searchClick("Shirts for women");
	  Actions action = new Actions(DriverFactory.getDriver());
	  action.sendKeys(Keys.ENTER).build().perform();
	 Assert.assertEquals("Shirts For Women", search.heading());
  
  }
}
