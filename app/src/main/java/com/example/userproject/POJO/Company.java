package com.example.userproject.POJO;

import org.jetbrains.annotations.NotNull;

public class Company {
    private String name;
    private  String catchphrase;
    private String bs;

    public Company() { }

    /**
     *
     * @param name ,the name of company
     * @param catchphrase ,the catchphrase(brand) of the company
     * @param bs ,
     */
    public Company(@NotNull String name,@NotNull String catchphrase,@NotNull String bs){
        this.setName(name);
        this.setCatchphrase(catchphrase);
        this.setBs(bs);
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public String getCatchphrase() {
        return catchphrase;
    }

    public void setCatchphrase(@NotNull String catchphrase) {
        this.catchphrase = catchphrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(@NotNull String bs) {
        this.bs = bs;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", catchphrase='" + catchphrase + '\'' +
                ", bs='" + bs + '\'' +
                '}';
    }

    @Override
    public boolean equals(@NotNull Object o) {
        Company company = (Company) o;
        return name.equals(company.name) && catchphrase.equals(company.catchphrase) && bs.equals(company.bs);
    }
}
