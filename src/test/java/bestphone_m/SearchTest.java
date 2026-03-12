package bestphone_m;

import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;
import bestphone_m.Search;

public class SearchTest extends BaseTest {
  @Test
  public void verifySearch() {
	  Search s = new Search(DriverFactory.getDriver());
	  s.enterProduct("shoes");
	  s.clickBtn();
  }
}
