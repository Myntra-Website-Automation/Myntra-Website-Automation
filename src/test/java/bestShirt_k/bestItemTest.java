package bestShirt_k;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;
import bestShirt_k.bestItem;

public class 	bestItemTest extends BaseTest {

    bestItem item;
    WebElement bestElement;
    WebElement bestIcon;

    @BeforeMethod
    public void setup() {
        item = new bestItem(DriverFactory.getDriver());
    }

    @Test(priority = 1)
    public void verify_allShirts() {

        List<WebElement> itemList = item.allProducts();
        System.out.println(itemList.size());
        
        

        for (WebElement items : itemList) {

            List<WebElement> nameElement = items.findElements(By.xpath(".//h3[@class='product-brand']"));
            List<WebElement> priceElement = items.findElements(By.xpath(".//span[@class='product-discountedPrice']"));
            List<WebElement> rateElement = items.findElements(By.xpath(".//div[@class='product-ratingsContainer']//span[1]"));

            if (nameElement.size() > 0 && priceElement.size() > 0 && rateElement.size() > 0) {

                String name = nameElement.get(0).getText();
                String price = priceElement.get(0).getText();
                String rate = rateElement.get(0).getText();

                System.out.println(name + " | " + price + " | " + rate);
            }

            System.out.println();
        }
    }

    @Test(priority = 2)
    public void find_bestShirt() {

        List<WebElement> itemList = item.allProducts();

       
        double bestScore = 0;
        String bestProduct = "";
      
        
        for (WebElement items : itemList) {

            try {

                String name = items.findElement(By.xpath(".//h3[@class='product-brand']")).getText();

                String priceText = items.findElement(By.xpath(".//span[@class='product-discountedPrice']")).getText();
                priceText = priceText.replace("Rs.", "").replace("₹", "").replace(",", "").trim();
                int price = Integer.parseInt(priceText);

                String ratingText = items.findElement(By.xpath(".//div[@class='product-ratingsContainer']//span[1]"))
                                         .getText().split(" ")[0];
                double rating = Double.parseDouble(ratingText);

                double score = rating / price;

                if (score > bestScore) {
                    bestScore = score;
                    bestProduct = name;
                    bestElement = items;
                    
                    bestIcon = items.findElement(By.xpath(".//div[@class='image-grid-similarColorsCta product-similarItemCta']"));
                }

            } catch (Exception e) {
                // skip products without rating or price
            }
        }

        System.out.println("Best Shirt based on Price + Rating: " + bestProduct);
        Actions action = new Actions(DriverFactory.getDriver());
        action.moveToElement(bestElement).build().perform();
        bestIcon.click();
    }
   
}
