package bestphone_m;

import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;
import bestphone_m.BestPhone;
import bestphone_m.Search;

public class BestPhoneTest extends BaseTest {
  @Test
  public void verifyBestPhone() {
	  Search s =  new Search(DriverFactory.getDriver());
	  s.enterProduct("shoes");
	  s.clickBtn();
	  BestPhone p = new BestPhone(DriverFactory.getDriver());
	  p.findBestProduct();
	 
  }
}
