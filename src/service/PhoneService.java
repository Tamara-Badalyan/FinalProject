package service;

import enums.PhoneBrandTypeEnum;
import enums.TvBrandTypeEnum;
import model.Phones.SmartPhone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class PhoneService implements interfaces.CRDService<SmartPhone> {
    private static final String PHONE_PATH = "C:\\Info\\phone.txt";

    @Override
    public void printInfoOfProduct(SmartPhone phone) {
        System.out.println("************************");
        System.out.println("ModelName : " + phone.getModelName());
        System.out.println("BrandName : " + phone.getBrandName());
        System.out.println("Price : " + phone.getPrice());
        System.out.println("Year : " + phone.getYear());
        System.out.println("Country : " + phone.getCountry());
        System.out.println("OperatingSystem : " + phone.getOperatingSystem());
        System.out.println("ScreenSize : " + phone.getScreenSize());
        System.out.println("Camera : " + phone.getCameraMP());
        System.out.println("RAM : " + phone.getRam());
        System.out.println("NFC : " + phone.isNFC());
        System.out.println("WifiConnection : " + phone.isWifiConnection());
    }

    @Override
    public void printInfoOfProducts(ArrayList<SmartPhone> phones) {
        for (SmartPhone phone :
                phones) {
            printInfoOfProduct(phone);
            System.out.println("***********************");
        }
    }

    @Override
    public ArrayList<SmartPhone> readFromFile(String path) {
        File file = new File(path);
        String result = null;
        ArrayList<SmartPhone> phones = new ArrayList<SmartPhone>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                result = scanner.nextLine();
                String[] items = result.split(",");
                SmartPhone model = new SmartPhone(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), items[4]);
                model.setOperatingSystem(items[5]);
                model.setScreenSize(Double.parseDouble(items[6]));
                model.setCameraMP(Integer.parseInt(items[7]));
                model.setRam(Integer.parseInt(items[8]));
                model.setNFC(Boolean.parseBoolean(items[9]));
                model.setWifiConnection(Boolean.parseBoolean(items[10]));
                phones.add(model);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return phones;
    }

    @Override
    public void clearAllData(String path) {
        try {
            Files.write(Paths.get(path), "".getBytes(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProductIntoFile(String path) {
        String result = null;
        Scanner s = new Scanner(System.in);
        result = addPhone() + "\n";

        try {
            Files.write(Paths.get(path), result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsProduct(String path) {
        var phone = createPhone();
        ArrayList<SmartPhone> models = readFromFile(path);
        LinkedHashSet<SmartPhone> phones = new LinkedHashSet<>(models);
        var res = phones.contains(phone);
        return res;
    }

    private String addPhone() {
        String result;
        var phone = createPhone();
        result = modelToString(phone);

        return result;
    }

    private SmartPhone createPhone() {
        Scanner s = new Scanner(System.in);
        System.out.println("***Please fill these fields***");
        System.out.println("ModelName : ");
        String modelName = s.next();
        System.out.println("BrandName : (Select)");
        System.out.println("1." + PhoneBrandTypeEnum.APPLE.getName());
        System.out.println("2." + PhoneBrandTypeEnum.SAMSUNG.getName());
        System.out.println("3." + PhoneBrandTypeEnum.HUAWEI.getName());
        System.out.println("4." + PhoneBrandTypeEnum.XIAOMI.getName());
        System.out.println("5." + PhoneBrandTypeEnum.NOKIA.getName());
        int brandType = s.nextInt();
        String brandName = null;
        switch (brandType) {

            case 1:
                brandName = PhoneBrandTypeEnum.APPLE.getName();
                break;
            case 2:
                brandName = PhoneBrandTypeEnum.SAMSUNG.getName();
                break;
            case 3:
                brandName = PhoneBrandTypeEnum.HUAWEI.getName();
                break;
            case 4:
                brandName = PhoneBrandTypeEnum.XIAOMI.getName();
                break;
            case 5:
                brandName = PhoneBrandTypeEnum.NOKIA.getName();
                break;
            default:
                System.out.println("Wrong Number -> default value is 1.Apple");
                brandName = PhoneBrandTypeEnum.APPLE.getName();
        }
        System.out.println("Price : ");
        int price = s.nextInt();
        System.out.println("Year : ");
        int year = s.nextInt();
        System.out.println("Country : ");
        String country = s.next();
        SmartPhone aModel = new SmartPhone(modelName, brandName, price, year, country);
        System.out.println("Operating system : ");
        aModel.setOperatingSystem(s.next());
        System.out.println("ScreenSize : ");
        aModel.setScreenSize(s.nextInt());
        System.out.println("CameraMP : ");
        aModel.setCameraMP(s.nextInt());
        System.out.println("RAM : ");
        aModel.setRam(s.nextInt());
        System.out.println("NFC(true/false) : ");
        aModel.setNFC(s.nextBoolean());
        System.out.println("WifiConnection(true/false) : ");
        aModel.setWifiConnection(s.nextBoolean());

        return aModel;
    }

    private String modelToString(SmartPhone model) {
        String result = String.join(",",
                model.getModelName(),
                model.getBrandName(),
                Integer.toString(model.getPrice()),
                Integer.toString(model.getYear()),
                model.getCountry(),
                model.getOperatingSystem(),
                Double.toString(model.getScreenSize()),
                Integer.toString(model.getCameraMP()),
                Integer.toString(model.getRam()),
                Boolean.toString(model.isNFC()),
                Boolean.toString(model.isWifiConnection()));

        return result;
    }
}
