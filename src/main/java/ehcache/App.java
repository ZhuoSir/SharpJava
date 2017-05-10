package ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.PersistentCacheManager;
import org.ehcache.UserManagedCache;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.UserManagedCacheBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

import javax.management.StringValueExp;

/**
 *
 * Created by sunny-chen on 2017/3/21.
 */
public class App {

    public static void main(String[] args) {
        test2();
    }

    public static void test() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Integer.class, ResourcePoolsBuilder.heap(10)))
                .build();
        cacheManager.init();

        Cache<String, Integer> preConfigured = cacheManager
                .getCache("preConfigured", String.class, Integer.class);

        preConfigured.put("hello", 1);
        Integer value = preConfigured.get("hello");

        System.out.println(value);
        cacheManager.removeCache("preConfigured");

        cacheManager.close();
    }

    public static void test0() {
        UserManagedCache<String, String> userManagedCache =
                UserManagedCacheBuilder.newUserManagedCacheBuilder(String.class, String.class)
                        .build(false);
        userManagedCache.init();

        userManagedCache.put("one", "da one!");

        String value = userManagedCache.get("one");
        System.out.println(value);

        userManagedCache.close();
    }

    public static void test1() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("tieredCache", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, EntryUnit.ENTRIES).offheap(10, MemoryUnit.MB)))
                .build(true);

        Cache<String, String> tieredCache = cacheManager.getCache("tieredCache", String.class, String.class);

        tieredCache.put("two", "da two!");

        String value = tieredCache.get("two");
        System.out.println(value);

        cacheManager.close();
    }

    public static void test2() {
        PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence("/Users/sunny-chen/Documents/JAVA/SharpJava/src/main/java/ehcache/data"))
                .withCache("persistent-cache", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, EntryUnit.ENTRIES).disk(10, MemoryUnit.MB, true)))
                .build(true);

        Cache<String, String> persistent_cache = persistentCacheManager.getCache("persistent-cache", String.class, String.class);

        persistent_cache.put("three", "da three!");

        String value = persistent_cache.get("three");
        System.out.println(value);

        persistentCacheManager.close();
    }

    public static void test3() {
        CacheConfiguration<String, String> usesConfiguredInCacheConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.newResourcePoolsBuilder()
                .heap(10, MemoryUnit.KB)
                .offheap(10 , MemoryUnit.MB))
                .withSizeOfMaxObjectGraph(1000)
                .withSizeOfMaxObjectSize(1000, MemoryUnit.B)
                .build();

        CacheConfiguration<String, String> usesDefaultSizeOfEngineConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.newResourcePoolsBuilder()
                .heap(10, MemoryUnit.KB)
                .offheap(10, MemoryUnit.MB))
                .build();

        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withDefaultSizeOfMaxObjectSize(500, MemoryUnit.B)
                .withDefaultSizeOfMaxObjectGraph(2000)
                .withCache("usesConfiguredInCache", usesConfiguredInCacheConfig)
                .withCache("usesDefaultSizeOfEngine", usesDefaultSizeOfEngineConfig)
                .build(true);

        Cache<String, String> usesConfiguredInCache = cacheManager.getCache("usesConfiguredInCache", String.class, String.class);

        usesConfiguredInCache.put("four", "da four!");
        System.out.println(usesConfiguredInCache.get("four"));

        Cache<String, String> usesDefaultSizeOfEngine = cacheManager.getCache("usesDefaultSizeOfEngine", String.class, String.class);

        usesDefaultSizeOfEngine.put("five", "da five!");
        System.out.println(usesDefaultSizeOfEngine.get("five"));

        // Update ResourcePools
        ResourcePools pools = ResourcePoolsBuilder.newResourcePoolsBuilder()
                .heap(20L, EntryUnit.ENTRIES).build();
        usesConfiguredInCache.getRuntimeConfiguration().updateResourcePools(pools);

        cacheManager.close();
    }
}
