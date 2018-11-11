/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotdbdii;

import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.Jedis;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Usuario Dell
 */
public class Redis {

    /**
     * @param args the command line arguments
     */
    public static Map<String, String> putMap(Account account) {
        Map<String, String> hash = new HashMap<>();
        hash.put("username", account.getUsserName());
        hash.put("password", account.getPassword());
        hash.put("email", account.getEmail());
        return hash;
    }

    public static void main(String[] args) {
        // TODO code application logic here

        Jedis jedis = new Jedis("localhost", 6379);
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not 
        System.out.println("Server is running: " + jedis.ping());

        //redis string
        jedis.set("name:1", "Carlos");
        jedis.set("name:2", "Rivera");
        jedis.set("date", "11/11/1997");

        System.out.println("String name1: " + jedis.get("name:1"));
        System.out.println("String name2: " + jedis.get("name:2"));
        System.out.println("String date: " + jedis.get("date"));

        //redis list
        jedis.lpush("categoria", "A");
        jedis.lpush("categoria", "B");
        jedis.lpush("categoria", "C");
        jedis.lpush("categoria", "D");

        List list = jedis.lrange("categoria", 2, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("List: " + list.get(i));
        }
        jedis.rpush("categoria2", "A");
        jedis.rpush("categoria2", "B");
        jedis.rpush("categoria2", "C");
        jedis.rpush("categoria2", "D");

        List list2 = jedis.lrange("categoria", 2, 2);
        for (int i = 0; i < list2.size(); i++) {
            System.out.println("List: " + list2.get(i));
        }

        //redis hash
        Account account = new Account("carlos", "12345", "carlos@gmail.com");
        jedis.hmset("account", putMap(account));
        Map<String, String> properties = jedis.hgetAll("account");
        System.out.println("Account: " + properties.get("email") + "/"
                + properties.get("username") + "/" + properties.get("password"));

        // redis set
        jedis.sadd("set", "set-value1", "set-value2", "set-value3");
        Set setlist = jedis.smembers("set");
        // access via iterator
        Iterator iterator = setlist.iterator();
        while (iterator.hasNext()) {
            String element = (String) iterator.next();
            System.out.println("set: " + element);
        }

        // acces via for loop
        /*for (Object object : setlist) {
            String element = (String) object;
            System.out.println("set: " + element);
        }*/
        jedis.zadd("zset", 100, "zset-value1");
        jedis.zadd("zset", 900, "zset-value2");
        jedis.zadd("zset", 50, "zset-value3");
        jedis.zadd("zset", 0, "zset-value4");
        jedis.zadd("zset", 200, "zset-value5");
        Set<String> zsetlist = jedis.zrange("zset", 0, 4);

        // access via iterator
        Iterator ziterator = zsetlist.iterator();
        while (ziterator.hasNext()) {
            String element = (String) ziterator.next();
            System.out.println("zset" + element);
        }
        // acces via for loop
        /*for (Object object : zsetlist) {
            String element = (String) object;
            System.out.println("zset: " + element);
        }*/

        //??????
        System.out.println("key db" + jedis.dbSize());
        Set<String> listkey = jedis.keys("*");
        System.out.println("set key " + listkey.toString());
        //get data
        System.out.println("fish: " + jedis.get("fish"));
        jedis.pfmerge("fish", "temp");
        System.out.println("temp" + jedis.get("temp"));
        System.out.println("HyperLoglog Count: " + jedis.pfcount("fish"));
    }

}
