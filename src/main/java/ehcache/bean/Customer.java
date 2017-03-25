package ehcache.bean;

import java.io.Serializable;

/**
 * Created by sunny-chen on 2017/3/25.
 */
public class Customer implements Serializable {

    private String customerID;

    private String customerName;

    private int age;

    private int sex;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
