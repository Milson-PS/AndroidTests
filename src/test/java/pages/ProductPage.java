package pages;

import io.appium.java_client.android.AndroidDriver;
import locators.ProductPageLocators;

public class ProductPage extends BasePage {

    public ProductPage(AndroidDriver driver) {
        super(driver);
    }

    public ProductPage addToCart() {
        clickElement(ProductPageLocators.ADD_TO_CART_BUTTON);
        return this;
    }
}
