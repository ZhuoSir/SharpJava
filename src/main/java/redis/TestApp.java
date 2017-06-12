package redis;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * Created by sunny on 17-6-12.
 */
public class TestApp {

    private Logger log = Logger.getLogger(TestApp.class);

    private Jedis jedis;

    @Before
    public void init() {
        jedis = new Jedis("localhost");
        log.info("Connnection to Server sucessfully.");
    }

    @Test
    public void connect() {
        log.info("Server is running: " + jedis.ping());
    }

    @Test
    public void testStringCase() throws InterruptedException {
        jedis.set("name", "chenzhuo");
        log.info("Stored String in redis:: " + jedis.get("name"));

        jedis.setnx("sex", "male");
        log.info("Stored String in redis if not exists:: " + jedis.get("sex"));

        jedis.append("name","* sunny");
        log.info("Stored String in redis:: " + jedis.get("name"));

        jedis.setex("work", 2, "programmer");
        log.info("Stored String in redis:: " + jedis.get("work"));
        Thread.sleep(3000);
        log.info("Stored String in redis:: " + jedis.get("work"));
    }

    @Test
    public void testListCase() {
        String list = "list";
        jedis.lpush(list, "redis");
        jedis.lpush(list,"mysql");
        jedis.lpush(list, "oracle");

        List<String> stringList = jedis.lrange(list, 0, 5);
        for (int i = 0; i < stringList.size(); i++) {
            log.info("Stored list in redis::" + stringList.get(i));
        }

        jedis.del(list);
        log.info("Delete list in redis::" + list);

        jedis.rpush(list, "redis");
        jedis.rpush(list, "mysql");
        jedis.rpush(list, "oracle");

        stringList = jedis.lrange(list, 0, 5);
        for (int i = 0; i < stringList.size(); i++) {
            log.info("Stored list in redis::" + stringList.get(i));
        }
    }

    @Test
    public void testSetCase() {
        String set = "mySet";
        jedis.sadd(set, "1");
        jedis.sadd(set, "2");
        jedis.sadd(set, "3");

        Set<String> sets = jedis.smembers(set);
        sets.forEach(str -> log.info("Stored set in redis::" + str));

        // 移除元素3
        jedis.srem(set, "3");
        sets = jedis.smembers(set);
        sets.forEach(str -> log.info("Stored set in redis::" + str));

        // 判断是否存在
        log.info(jedis.sismember(set, "4"));

        // 返回元素个数
        log.info(jedis.scard(set));
    }

    @Test
    public void testGetKeys() {
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            log.info(key);
        }
    }
}
