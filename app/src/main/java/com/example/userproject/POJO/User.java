package com.example.userproject.POJO;

import org.jetbrains.annotations.NotNull;


/**
 * The Users class contain all information about any user
 */
public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
    private static int countId =1; //this member to auto assign id from 1,2,...etc

    public User() {
        this.setId();
    }


    /**
     *
     * @param address ,the address of user
     * @param company ,the instance of a Company class
     * @param name ,the name of user
     * @param username ,the username of user
     * @param email ,the email of  user
     * @param phone ,the phone number of user
     * @param website ,the website of user
     */
    public User(@NotNull Address address, @NotNull Company company, @NotNull String name, @NotNull String username,
                @NotNull String email, @NotNull String phone , @NotNull String website){
        this.setId();
        this.setName(name);
        this.setUsername(username);
        this.setEmail(email);
        this.setAddress(address);
        this.setPhone(phone);
        this.setWebsite(website);
        this.setCompany(company);
    }

    /**
     * these method make the class auto assign id
     */
    private void setId() {
        this.id= getCountId();
        countId++;
    }

    public int getId() {
        return id;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(@NotNull Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setPhone(@NotNull String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setWebsite(@NotNull String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public void setCompany(@NotNull Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public static int getCountId() {
        return countId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", " + address.toString() +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        User user = (User) o;
        return id == user.id && name.equals(user.name) && username.equals(user.username) && email.equals(user.email) &&
                address.equals(user.address) && phone.equals(user.phone) && website.equals(user.website) && company.equals(user.company);
    }
}
