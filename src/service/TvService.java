package service;

import enums.ComputerBrandTypeEnum;
import enums.TvBrandTypeEnum;
import interfaces.CRDService;
import model.Computers.Computer;
import model.Phones.SmartPhone;
import model.TVs.TV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class TvService implements CRDService<TV> {

    @Override
    public void printInfoOfProduct(TV tv) {
        System.out.println("************************");
        System.out.println("ModelName : " + tv.getModelName());
        System.out.println("BrandName : " + tv.getBrandName());
        System.out.println("Price : " + tv.getPrice());
        System.out.println("Year : " + tv.getYear());
        System.out.println("Country : " + tv.getCountry());
        System.out.println("Digital Tv Tuner : " + tv.getDigitalTvTuner());
        System.out.println("OperatingSystem : " + tv.getOperatingSystem());
        System.out.println("RemoteControl : " + tv.getRemoteControl());
    }

    @Override
    public void printInfoOfProducts(ArrayList<TV> tvs) {
        for (TV tv :
                tvs) {
            printInfoOfProduct(tv);
            System.out.println("***********************");
        }
    }

    @Override
    public ArrayList<TV> readFromFile(String path) {
        File file = new File(path);
        String result = null;
        ArrayList<TV> tvs = new ArrayList<TV>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                result = scanner.nextLine();
                String[] items = result.split(",");
                TV model = new TV(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), items[4]);
                model.setDigitalTvTuner(items[5]);
                model.setOperatingSystem(items[6]);
                model.setRemoteControl(items[7]);
                tvs.add(model);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return tvs;
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
        result = addTV() + "\n";

        try {
            Files.write(Paths.get(path), result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private TV createTV() {
        Scanner s = new Scanner(System.in);
        System.out.println("***Please fill these fields***");
        System.out.println("ModelName : ");
        String modelName = s.next();
        System.out.println("BrandName : (Select)");
        System.out.println("1." + TvBrandTypeEnum.SAMSUNG.getName());
        System.out.println("2." + TvBrandTypeEnum.SONY.getName());
        System.out.println("3." + TvBrandTypeEnum.LG.getName());
        System.out.println("4." + TvBrandTypeEnum.PANASONIC.getName());
        int brandType = s.nextInt();
        String brandName = null;
        switch (brandType) {

            case 1:
                brandName = TvBrandTypeEnum.SAMSUNG.getName();
                break;
            case 2:
                brandName = TvBrandTypeEnum.SONY.getName();
                break;
            case 3:
                brandName = TvBrandTypeEnum.LG.getName();
                break;
            case 4:
                brandName = TvBrandTypeEnum.PANASONIC.getName();
                break;
            default:
                System.out.println("Wrong Number -> default value is 1.Samsung");
                brandName = TvBrandTypeEnum.SAMSUNG.getName();
        }
        System.out.println("Price : ");
        int price = s.nextInt();
        System.out.println("Year : ");
        int year = s.nextInt();
        System.out.println("Country : ");
        String country = s.next();
        TV aModel = new TV(modelName, brandName, price, year, country);
        System.out.println("Digital TV tuner : ");
        aModel.setDigitalTvTuner(s.next());
        System.out.println("Operating System : ");
        aModel.setOperatingSystem(s.next());
        System.out.println("Remote Control : ");
        aModel.setRemoteControl(s.next());
        return aModel;
    }

    private String addTV() {
        String result;
        var tv = createTV();
        result = modelToString(tv);

        return result;
    }

    private String modelToString(TV model) {
        String result = String.join(",",
                model.getModelName(),
                model.getBrandName(),
                Integer.toString(model.getPrice()),
                Integer.toString(model.getYear()),
                model.getCountry(),
                model.getDigitalTvTuner(),
                model.getOperatingSystem(),
                model.getRemoteControl());

        return result;
    }

    @Override
    public boolean containsProduct(String path) {
        ArrayList<TV> models = readFromFile(path);
        var tv=createTV();
        LinkedHashSet<TV> tvs = new LinkedHashSet<>(models);
        var res = tvs.contains(tv);
        return res;
    }
}
