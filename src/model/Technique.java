package model;

import exceptions.InputException;

import java.util.Objects;

public abstract class Technique implements Comparable{
    private String modelName;
    private String brandName;
    private int price;
    private int year;
    private String country;

    public Technique(String modelName, String brandName, int price, int year, String country) {
        this.modelName = modelName;
        this.brandName = brandName;
        this.price = price;
        this.year = year;
        this.country = country;
    }

    private boolean isCorrectName(String name){
        if(name.length()>2 && name!=null )
            return true;
        return false;
    }

    private boolean isCorrectPrice(int price){
        if(price>5000 && price<5000000)
            return true;
        return false;
    }

    private boolean isCorrectYear(int year){
        if(year>2006 && year<2021)
            return true;
        return false;
    }
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        if(isCorrectName(modelName))
            this.modelName = modelName;
        else
           throw  new InputException();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if(isCorrectPrice(price))
            this.price = price;
        else
            throw  new InputException();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(isCorrectYear(year))
            this.year = year;
        else
           throw  new InputException();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if(isCorrectName(country))
            this.country = country;
        else
            throw  new InputException();
    }
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Technique technique = (Technique) o;
        return price == technique.price &&
                year == technique.year &&
                Objects.equals(modelName, technique.modelName) &&
                Objects.equals(brandName, technique.brandName) &&
                Objects.equals(country, technique.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }

    public  void printInfo(){
        System.out.println("************************");
        System.out.println("ModelName : "+ getModelName());
        System.out.println("Price : "+ getPrice());
        System.out.println("Year : "+ getYear());
        System.out.println("Country : "+ getCountry());
    }
}