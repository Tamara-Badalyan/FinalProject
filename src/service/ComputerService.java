package service;

import enums.ComputerBrandTypeEnum;
import enums.PhoneBrandTypeEnum;
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

public class ComputerService implements CRDService<Computer> {

//    private String cpuType;
//    private int cacheMemory;
//    private int numberOfCores;
    @Override
    public void printInfoOfProduct(Computer computer) {
        System.out.println("************************");
        System.out.println("ModelName : " + computer.getModelName());
        System.out.println("BrandName : " + computer.getBrandName());
        System.out.println("Price : " + computer.getPrice());
        System.out.println("Year : " + computer.getYear());
        System.out.println("Country : " + computer.getCountry());
        System.out.println("CPU Type : " + computer.getCpuType());
        System.out.println("CacheMemory : " + computer.getCacheMemory());
        System.out.println("NumberOfCores : " + computer.getNumberOfCores());
    }

    @Override
    public void printInfoOfProducts(ArrayList<Computer> computers) {
        for (Computer computer :
                computers) {
            printInfoOfProduct(computer);
            System.out.println("***********************");
        }
    }

    @Override
    public ArrayList<Computer> readFromFile(String path) {
        File file = new File(path);
        String result = null;
        ArrayList<Computer> computers = new ArrayList<Computer>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                result = scanner.nextLine();
                String[] items = result.split(",");
                Computer model = new Computer(items[0], items[1],Integer.parseInt(items[2]),Integer.parseInt(items[3]), items[4]);
                model.setCpuType(items[5]);
                model.setCacheMemory(Integer.parseInt(items[6]));
                model.setNumberOfCores(Integer.parseInt(items[7]));
                computers.add(model);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return computers;
    }

    @Override
    public void clearAllData(String path) {
        try {
            Files.write(Paths.get(path),"".getBytes(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProductIntoFile(String path) {
        String result = null;
        Scanner s = new Scanner(System.in);
        result = addComputer() + "\n";

        try {
            Files.write(Paths.get(path), result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    private Computer createComputer(){
        Scanner s = new Scanner(System.in);
        System.out.println("***Please fill these fields***");
        System.out.println("ModelName : ");
        String modelName = s.next();
        System.out.println("BrandName : (Select)");
        System.out.println("1." + ComputerBrandTypeEnum.APPLE.getName());
        System.out.println("2." + ComputerBrandTypeEnum.ACER.getName());
        System.out.println("3." + ComputerBrandTypeEnum.ASUS.getName());
        System.out.println("4." + ComputerBrandTypeEnum.DELL.getName());
        System.out.println("5." + ComputerBrandTypeEnum.Lenovo.getName());
        int brandType = s.nextInt();
        String brandName = null;
        switch (brandType) {

            case 1:
                brandName = ComputerBrandTypeEnum.APPLE.getName();
                break;
            case 2:
                brandName = ComputerBrandTypeEnum.ACER.getName();
                break;
            case 3:
                brandName = ComputerBrandTypeEnum.ASUS.getName();
                break;
            case 4:
                brandName = ComputerBrandTypeEnum.DELL.getName();
                break;
            case 5:
                brandName = ComputerBrandTypeEnum.Lenovo.getName();
                break;
            default:
                System.out.println("Wrong Number -> default value is 1.Apple");
                brandName = ComputerBrandTypeEnum.APPLE.getName();
        }
        System.out.println("Price : ");
        int price = s.nextInt();
        System.out.println("Year : ");
        int year = s.nextInt();
        System.out.println("Country : ");
        String country = s.next();
        Computer aModel = new Computer(modelName,brandName ,price, year, country);
        System.out.println("Cpu Type : ");
        aModel.setCpuType(s.next());
        System.out.println("Cache memory : ");
        aModel.setCacheMemory(s.nextInt());
        System.out.println("Number of cores : ");
        aModel.setNumberOfCores(s.nextInt());
        return aModel;
    }
    private String addComputer() {
        String result;
        var computer=createComputer();
        result = modelToString(computer);

        return result;
    }

    private String modelToString(Computer model) {
        String result=String.join(",",
                model.getModelName(),
                model.getBrandName(),
                Integer.toString(model.getPrice()),
                Integer.toString(model.getYear()),
                model.getCountry(),
                model.getCpuType(),
                Integer.toString(model.getCacheMemory()),
                Integer.toString(model.getNumberOfCores()));

        return result;
    }
    @Override
    public boolean containsProduct( String path) {
        var computer=createComputer();
        ArrayList<Computer>  models = readFromFile(path);
        LinkedHashSet<Computer> computers=new LinkedHashSet<>(models);
        var res= computers.contains(computer);
        return res;
    }
}
