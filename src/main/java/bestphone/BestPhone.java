package bestphone;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class BestPhone extends BasePage {

    public BestPhone(WebDriver driver) {
        super(driver);
    }

    // All product cards
    @FindBy(xpath = "//li[@class='product-base']")
    List<WebElement> products;

    public void findBestProduct() {

        double bestScore = 0;
        String bestProduct = "";
        double bestPrice = 0;
        double bestRating = 0;

        WebElement bestElement = null;

        for (WebElement product : products) {

            try {

                // Product Name
                String name = product.findElement(By.xpath(".//h3")).getText();

                // Price
                String priceText = product.findElement(
                        By.xpath(".//span[@class='product-discountedPrice']")).getText();

                double price = Double.parseDouble(
                        priceText.replace("Rs.", "").replace(",", "").trim());

                // Rating
                String ratingText = product.findElement(
                        By.xpath(".//div[@class='product-ratingsContainer']//span[1]")).getText();

                double rating = Double.parseDouble(ratingText);

                // Score Logic
                double score = rating / price;

                System.out.println("Product: " + name);
                System.out.println("Price: " + price);
                System.out.println("Rating: " + rating);
                System.out.println("Score: " + score);
                System.out.println("--------------------");

                if (score > bestScore) {

                    bestScore = score;
                    bestProduct = name;
                    bestPrice = price;
                    bestRating = rating;
                    bestElement = product;
                }

            } catch (Exception e) {
                System.out.println("Skipping product due to missing data");
            }
        }

        System.out.println("\n========== BEST PHONE ==========");
        System.out.println("Product: " + bestProduct);
        System.out.println("Price: " + bestPrice);
        System.out.println("Rating: " + bestRating);
        System.out.println("Score: " + bestScore);

        try {
            bestElement.findElement(By.xpath(".//h3")).click();
            System.out.println("Clicked on best product");
        } catch (Exception e) {
            System.out.println("Unable to click best product");
        }
    }
}