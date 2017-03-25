package ehcache.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by sunny-chen on 2017/3/25.
 */
public class Product implements Serializable {

    private String productID;

    private String productName;

    private BigDecimal price;

    private float weight;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "productID: " + productID + "\nproductName: " + productName
                + "\nprice: " + price + "\nweight: " + weight;
    }
}
