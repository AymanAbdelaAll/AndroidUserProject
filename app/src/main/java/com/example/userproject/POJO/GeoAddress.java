package com.example.userproject.POJO;

import org.jetbrains.annotations.NotNull;



public class GeoAddress extends Address {
    private Geo geo;

    protected GeoAddress() { }

    protected GeoAddress(@NotNull Geo geo, @NotNull String street, @NotNull String suite, @NotNull String city, @NotNull String zipcode) {
        super(street, suite, city, zipcode);
        this.geo = geo;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(@NotNull Geo geo) {
        this.geo = geo;
    }

    @Override
    public boolean equals(Object o) {
        GeoAddress geoAddress=(GeoAddress) o;
        return super.equals(geoAddress) && geo.equals(geoAddress.geo) ;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + super.getStreet() + '\'' +
                ", suite='" + super.getSuite() + '\'' +
                ", city='" + super.getCity() + '\'' +
                ", zipcode='" + super.getZipcode() + '\'' +
                ", " + geo.toString() +
                '}';
    }
}
