package bestproduct_rk;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class BestProduct extends BasePage {

	public BestProduct(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@placeholder='Search for products, brands and more']")
	private WebElement search_box;

	public void searchWatch() {
		search_box.sendKeys("watch");
		search_box.sendKeys(Keys.ENTER);
	}

	int bestIndex = -1;

	public void findBestProducts() {
		List<WebElement> prices = driver.findElements(By.xpath("//span[@class='product-discountedPrice']"));
		List<WebElement> ratings = driver.findElements(By.xpath("//div[@class='product-ratingsContainer']/span"));
		List<WebElement> brands = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		int size = Math.min(Math.min(prices.size(), ratings.size()), brands.size());
		double bestRating = Double.MIN_VALUE;
		int bestPrice = Integer.MAX_VALUE;
		String bestBrand = "";
		System.out.println("------------------------------------");
		System.out.println("All Similar Product Details");
		for (int i = 0; i < size; i++) {
			String brand = brands.get(i).getText();
			int price = Integer.parseInt(prices.get(i).getText().replaceAll("[^0-9]", ""));
			String ratingText = ratings.get(i).getText().trim();

			if (ratingText.isEmpty()) {
				continue;
			}
			double rating = Double.parseDouble(ratingText.split("\\|")[0].trim());
			
			System.out.println(brand + " | Price: Rs." + price + " | Rating: " + rating);
			if (rating > bestRating || (rating == bestRating && price < bestPrice)) {

				bestRating = rating;
				bestPrice = price;
				bestBrand = brand;
			}
		}

		System.out.println("\nBEST PRODUCT");

		System.out.println(bestBrand + " | Price: Rs." + bestPrice + " | Rating: " + bestRating);
		System.out.println("------------------------------------------");
	}

	public void findBestProducts2() {
        List<WebElement> prices = driver.findElements(
                By.xpath("//span[@class='product-discountedPrice']")
        );
        List<WebElement> ratings = driver.findElements(
                By.xpath("//div[@class='product-ratingsContainer']/span")
        );
        List<WebElement> brands = driver.findElements(
                By.xpath("//h3[@class='product-brand']")
        );
        int size = Math.min(Math.min(prices.size(), ratings.size()), brands.size());
        double bestRating = Double.MIN_VALUE;
        int bestPrice = Integer.MAX_VALUE;
        String bestBrand = "";
        double score=0;
        double maxScore=Double.MIN_VALUE;
        System.out.println("------------------------------------");
        System.out.println("All Similar Product Details!");
        for (int i = 0; i < size; i++) {
            String brand = brands.get(i).getText();
            int price = Integer.parseInt(
                    prices.get(i).getText().replaceAll("[^0-9]", "")
            );
            String ratingText = ratings.get(i).getText().trim();

            if (ratingText.isEmpty()) {
                continue;
            }
            double rating = Double.parseDouble(ratingText.split("\\|")[0].trim());
            score=rating/price;
            
            System.out.println(
                    brand +
                    " | Price: Rs." + price +
                    " | Rating: " + rating+
                    " | Score: "+score
            );
            
            if (score>maxScore) {
                bestRating = rating;
                bestPrice = price;
                bestBrand = brand;
                maxScore=score;
            }
        }

        System.out.println("\nBEST PRODUCT");

        System.out.println(
                bestBrand +
                " | Price: Rs." + bestPrice +
                " | Rating: " + bestRating +
                " | MaxScore: "+maxScore
        );
        System.out.println("---------------------------------------");
    }

	public void clickBestProduct() {

		List<WebElement> products = driver.findElements(By.xpath("//li[@class='product-base']"));

		if (bestIndex != -1 && bestIndex < products.size()) {

			products.get(bestIndex).click();
		}
	}
	public void findSimilarProduct() {

	    List<WebElement> products = driver.findElements(By.xpath("//li[@class='product-base']"));

	    if (products.size() == 0) {
	        System.out.println("No products found");
	        return;
	    }

	    Actions act = new Actions(driver);
	    act.moveToElement(products.get(0)).perform();

	    WebElement viewSimilar = products.get(0)
	            .findElement(By.xpath(".//span[contains(text(),'VIEW SIMILAR')]"));

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", viewSimilar);

	    List<WebElement> brands = driver.findElements(By.xpath("//h3[@class='product-brand']"));
	    List<WebElement> prices = driver.findElements(By.xpath("//span[@class='product-discountedPrice']"));
	    List<WebElement> ratings = driver.findElements(By.xpath("//div[@class='product-ratingsContainer']/span"));

	    int size = Math.min(Math.min(brands.size(), prices.size()), ratings.size());

	    double maxScore = Double.MIN_VALUE;
	    double bestRating = 0;
	    int bestPrice = 0;
	    String bestBrand = "";

	    System.out.println("-------------------------------------------------");
	    System.out.println("\nALL SIMILAR PRODUCT");

	    for (int i = 0; i < size; i++) {

	        String brand = brands.get(i).getText();

	        String priceText = prices.get(i).getText().replaceAll("[^0-9]", "");

	        if (priceText.isEmpty()) {
	            continue;
	        }

	        int price = Integer.parseInt(priceText);

	        String ratingText = ratings.get(i).getText().trim();

	        if (ratingText.isEmpty()) {
	            continue;
	        }

	        double rating = Double.parseDouble(
	                ratingText.split("\\|")[0].trim()
	        );

	        double score = rating / price;

	        // print ALL similar products
	        System.out.println(
	                brand +
	                " | Price: Rs." + price +
	                " | Rating: " + rating +
	                " | Score: " + score
	        );

	        // calculate BEST
	        if (score > maxScore) {
	            maxScore = score;
	            bestRating = rating;
	            bestPrice = price;
	            bestBrand = brand;
	        }
	    }

	    System.out.println("\nBEST SIMILAR PRODUCT");

	    System.out.println(
	            bestBrand +
	            " | Price: Rs." + bestPrice +
	            " | Rating: " + bestRating +
	            " | Score: " + maxScore
	    );

	    System.out.println("----------------------------------------------------------");
	}
}