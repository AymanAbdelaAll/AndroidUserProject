package com.example.userproject.POJO;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;


    protected Address() { }

    /**
     *
     * @param street ,the name of street
     * @param suite ,the number of suite
     * @param city ,the name of city
     * @param zipcode ,the zipcode of the country
     */
    protected Address( @NotNull String street,@NotNull String suite,@NotNull String city,@NotNull String zipcode){
        this.setStreet(street);
        this.setSuite(suite);
        this.setCity(city);
        this.setZipcode(zipcode);
    }

    public static Address buildGeo(Geo geo,@NotNull String street,@NotNull String suite,@NotNull String city,@NotNull String zipcode){
        if (geo == null)
            return new Address(street,suite,city,zipcode);
        else
            return new GeoAddress(geo,street,suite,city,zipcode);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(@NotNull String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(@NotNull String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(@NotNull String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }

    @Override
    public boolean equals(@NotNull Object o) {
        Address address = (Address) o;
        return street.equals(address.street) && suite.equals(address.suite) &&
                city.equals(address.city) && zipcode.equals(address.zipcode) ;
    }
}
