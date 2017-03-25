package ehcache.bean;

import org.ehcache.config.EvictionAdvisor;

/**
 *
 * Created by sunny-chen on 2017/3/25.
 */
public class MyEvictionAdvisor implements EvictionAdvisor<Product, Product> {

    @Override
    public boolean adviseAgainstEviction(Product product, Product product2) {
        System.out.println("against");
        return product.getPrice().compareTo(product2.getPrice()) == -1;
    }
}
