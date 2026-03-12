package searchShirt_k;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class searchItem extends BasePage {

	public searchItem(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//input[@placeholder='Search for products, brands and more']")
	WebElement searchbox;
	
	public void searchClick(String name) {
		wait.waitForElementToBeVisible(searchbox);
		searchbox.sendKeys(name);
	}
	
	@FindBy(xpath = "//h1[normalize-space()='Shirts For Women']")
	WebElement headShirt;
	
	public String heading() {
		wait.waitForElementToBeVisible(headShirt);
		return headShirt.getText();
	}
	
	
}
