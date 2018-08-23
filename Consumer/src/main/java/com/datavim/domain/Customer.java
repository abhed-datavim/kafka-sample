package com.datavim.domain;

/**
 * 
 * @author abhed
 *
 */
public class Customer {

    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String dept) {
        this.id = dept;
    }

    public Customer() {
    }

    public Customer(String name, String dept) {

        this.name = name;
        this.id = dept;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Customer{");
        sb.append("name='").append(name).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
