package ehcache;

import ehcache.bean.Product;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.math.BigDecimal;
import java.net.URL;

/**
 *
 * Created by sunny-chen on 2017/3/25.
 */
public class XmlApp {

    public static void main(String[] args) {
        final URL myUrl = XmlApp.class.getClass().getResource("/ehcache.xml");
        XmlConfiguration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        cacheManager.init();

        Product one = new Product();
        one.setProductID("010100101");
        one.setPrice(BigDecimal.TEN);
        one.setWeight(180.1f);
        one.setProductName("HUAWEI");

        Product two = new Product();
        two.setProductID("02022020202");
        two.setProductName("Apple");
        two.setWeight(200f);
        two.setPrice(BigDecimal.ONE);

        Cache<Long, Product> productCache = cacheManager.getCache("productCache", Long.class, Product.class);
        productCache.put(0L, one);
        productCache.put(1L, two);

        Product product = productCache.get(1L);
        System.out.println(product.toString());

        cacheManager.close();
    }
}
