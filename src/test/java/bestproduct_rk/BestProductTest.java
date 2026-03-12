package bestproduct_rk;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;
import bestproduct_rk.BestProduct;

public class BestProductTest extends BaseTest {
	private BestProduct maxPriceProduct;

	@BeforeClass
	public void initPage() {
		maxPriceProduct = new BestProduct(DriverFactory.getDriver());
	}
	@Test(priority = 1)
	public void clickSearchBoxTest() {
		maxPriceProduct.searchWatch();
	}

	
	@Test(priority = 2)
	public void clickFindBestProductsTest() {
		maxPriceProduct.findBestProducts();;
	}
	
	@Test(priority = 3)
	public void clickFindBestProducts2Test() {
		maxPriceProduct.findBestProducts2();;
	}
	
	@Test(priority = 5)
	public void clickBestProductsTest() {
		maxPriceProduct.clickBestProduct();
	}
	
	@Test(priority = 4)
	public void clickFindSimilarProductTest() {
		maxPriceProduct.findSimilarProduct();
	}
}
