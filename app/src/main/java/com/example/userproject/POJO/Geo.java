package com.example.userproject.POJO;

import org.jetbrains.annotations.NotNull;

public class Geo {
    private Double lat;
    private Double lng;

    public Geo() {
        this.lat=0.0;
        this.lng=0.0;
    }

    /**
     *
     * @param lat
     * @param lng
     */
    public Geo(@NotNull Double lat,@NotNull Double lng) {
        this.setLat(lat);
        this.setLng(lng);
    }

    public double getLat() {
        return lat;
    }

    public void setLat(@NotNull Double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(@NotNull Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    @Override
    public boolean equals(@NotNull Object o) {
        Geo geo = (Geo) o;
        return lat.equals(geo.lat) && lng.equals(geo.lng);
    }

}
