package com.pantanalshop.model;

import java.util.List;
import java.util.Objects;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String address;
    private List<String[]> cart;
    private Double total;
    private int key;

    public User(String id, String name, String email, String password, String address, List<String[]> cart) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.cart = cart;
        this.total = 0.0;
        this.key = this.hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String[]> getCart() {
        return cart;
    }

    public void setCart(List<String[]> cart) {
        this.cart = cart;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal() {
        this.total = cart.stream().mapToDouble(product -> Double.parseDouble(product[2]) * Double.parseDouble(product[6])).sum();
    }

    public int getKey() {
        return key;
    }

    public void setKey(){
        this.key = this.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    public boolean validate(String user, String pass){

        return this.key == Objects.hash(user, pass);
    }


}
