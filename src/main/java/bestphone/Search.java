package bestphone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class Search extends BasePage {
	public Search(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Search for products, brands and more']")
	WebElement search;
	
	public void enterProduct(String text) {
		wait.waitForElementToBeVisible(search);
		search.sendKeys(text);
	}
	
	@FindBy(xpath="//span[@class='myntraweb-sprite desktop-iconSearch sprites-search']")
	WebElement btn;
	
	public BestPhone clickBtn() {
		wait.waitForElementToBeClickable(btn);
		btn.click();
		return new BestPhone(driver);
	}
}
