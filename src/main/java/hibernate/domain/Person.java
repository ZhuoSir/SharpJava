package hibernate.domain;

import javax.persistence.*;

/**
 * Created by sunny-chen on 16/11/21.
 */
@Entity
public class Person {
    private int idCard;
    private String name;
    private Integer sex;
    private String email;
    private Integer phone;
    private Integer addressId;
    private Address addressByAddressId;

    @Id
    @Column(name = "IDCard")
    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Phone")
    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "AddressID")
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (idCard != person.idCard) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (sex != null ? !sex.equals(person.sex) : person.sex != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (phone != null ? !phone.equals(person.phone) : person.phone != null) return false;
        if (addressId != null ? !addressId.equals(person.addressId) : person.addressId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCard;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "AddressID", referencedColumnName = "id")
    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

    @Override
    public String toString() {
        return "IDCard :" + idCard + " Name : " + name;
    }
}
