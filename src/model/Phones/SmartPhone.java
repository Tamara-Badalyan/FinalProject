package model.Phones;

import enums.PhoneBrandTypeEnum;
import exceptions.InputException;
import model.Technique;

import java.util.Objects;

public class SmartPhone extends Technique {
    private String operatingSystem = getBrandName() == PhoneBrandTypeEnum.APPLE.getName() ? "IOS" : "Android";
    private double screenSize;
    private int cameraMP;
    private int ram;
    private boolean NFC;
    private boolean wifiConnection;

    public SmartPhone(String modelName, String brandName, int price, int year, String country) {
        super(modelName, brandName, price, year, country);
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        if (operatingSystem != null && operatingSystem.length() > 2)
            this.operatingSystem = operatingSystem;
        else
            throw new InputException();
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        if (screenSize > 0)
            this.screenSize = screenSize;
        else
            throw new InputException();
    }

    public int getCameraMP() {
        return cameraMP;
    }

    public void setCameraMP(int cameraMP) {
        if (cameraMP > 0)
            this.cameraMP = cameraMP;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        if (ram > 0)
            this.ram = ram;
    }

    public boolean isNFC() {
        return NFC;
    }

    public void setNFC(boolean NFC) {
        this.NFC = NFC;
    }

    public boolean isWifiConnection() {
        return wifiConnection;
    }

    public void setWifiConnection(boolean wifiConnection) {
        this.wifiConnection = wifiConnection;
    }

//    public abstract boolean downloadingApps(String appName);
//
//    public abstract void sendMessage(String text, String phoneNumber);
//
//    public abstract void call(String phoneNumber);
//
//    @Override
//    public boolean equals(Object o) {
//        super.equals(o);
////        if (this == o) return true;
////        if (o == null || getClass() != o.getClass()) return false;
////        if (!super.equals(o)) return false;
////        SmartPhone that = (SmartPhone) o;
////        return Double.compare(that.screenSize, screenSize) == 0 &&
////                cameraMP == that.cameraMP &&
////                ram == that.ram &&
////                NFC == that.NFC &&
////                wifiConnection == that.wifiConnection &&
////                Objects.equals(operatingSystem, that.operatingSystem);
//    }
//
//    @Override
//    public int hashCode() {
//        super.hashCode();
//        //return this.getModelName().length()*getBrandName().length()*getYear();
//    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("OperatingSystem : " + getOperatingSystem());
        System.out.println("ScreenSize : " + getScreenSize());
        System.out.println("Camera : " + getCameraMP());
        System.out.println("RAM : " + getRam());
        System.out.println("NFC : " + isNFC());
        System.out.println("WifiConnection : " + isWifiConnection());
    }

    @Override
    public int compareTo(Object o) {
        SmartPhone s = (SmartPhone) o;
        return this.getModelName().compareTo(s.getModelName());
    }


}
