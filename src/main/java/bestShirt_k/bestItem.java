package bestShirt_k;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeMethod;

import base.BasePage;

public class bestItem extends BasePage{

	public bestItem(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	

	@FindBy(xpath = "//li[@class = 'product-base']")
	List<WebElement> products; 
	
	public List<WebElement> allProducts(){
		return products;
	}

	
	@FindBy(xpath = "li[id='40202903'] div[class='image-grid-similarColorsCta product-similarItemCta']")
	WebElement similarItem;
	
	public void clickSimilarItem() {
		wait.waitForElementToBeVisible(similarItem);
		similarItem.click();
	}
	
	
}
