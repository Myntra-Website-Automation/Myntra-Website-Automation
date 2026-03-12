package similarShirt_k;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class similarItem extends BasePage {

	public similarItem(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	@FindBy(xpath = "//ul[@class = 'results-base results-similarGrid']//li[@class = 'product-base']")
	List<WebElement> similarItemsName;
	
	public List<WebElement> similarProducts(){
		return similarItemsName;
	}
	
	
}
