package model.Computers;

import exceptions.InputException;
import model.Phones.SmartPhone;
import model.Technique;

public class Computer extends Technique {
    private String cpuType;
    private int cacheMemory;
    private int numberOfCores;

    public Computer(String modelName, String brandName, int price, int year, String country) {
        super(modelName, brandName, price, year, country);
    }

    public String getCpuType() {
        return cpuType;
    }

    public void setCpuType(String cpuType) {
        if (cpuType != null && cpuType.length() > 2)
            this.cpuType = cpuType;
        else
            throw new InputException();
    }

    public int getCacheMemory() {
        return cacheMemory;
    }

    public void setCacheMemory(int cacheMemory) {
        if (cacheMemory > 0)
            this.cacheMemory = cacheMemory;
        else
            throw new InputException();
    }

    public int getNumberOfCores() {
        return numberOfCores;
    }

    public void setNumberOfCores(int numberOfCores) {
        if (numberOfCores > 0)
            this.numberOfCores = numberOfCores;
        else
            throw new InputException();
    }
    @Override
    public int compareTo(Object o) {
        Computer s = (Computer) o;
        return this.getModelName().compareTo(s.getModelName());
    }
}
