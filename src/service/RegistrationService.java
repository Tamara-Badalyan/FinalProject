package service;

import interfaces.IRegistrationService;
import model.Users.RegistrationModel;
import statics.Connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistrationService implements IRegistrationService {
    @Override
    public void RegisterUser(RegistrationModel model) {
        String result = RegModelToString(model)+"\n";
        try {
            Files.write(Paths.get(Connection.DB_CONTEXT_PATH), result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Registered!!!");
    }

    public RegistrationModel createRegModel() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter this fields");

        System.out.println("Full name -> (John Smith)");
        String fullName = s.nextLine();
        s.nextLine();
        System.out.println("Username -> length>10 and not duplicate");
        String username = s.next();
        System.out.println("Email -> example@gmail.com");
        String email = s.next();
        System.out.println("Password -> at least 2 uppercase,3 digits,length >10");
        String password = s.next();
        return new RegistrationModel(fullName, username, email, password);
    }

    @Override
    public ArrayList<RegistrationModel> GetList(String path) {
        File file = new File(path);
        String result = null;
        ArrayList<RegistrationModel> models = new ArrayList<RegistrationModel>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                result = scanner.nextLine();
                String[] items = result.split(",");
                RegistrationModel model = new RegistrationModel(items[0], items[1], items[2], items[3]);
                models.add(model);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return models;
    }

    @Override
    public boolean isUserExist(String userName, String password) {
        var listOfUsers = GetList(Connection.DB_CONTEXT_PATH);
        for (var item :
                listOfUsers) {
            if (item.getUserName().compareTo(userName) == 0 && item.getPassword().compareTo(password) == 0)
                return true;
        }
        return false;
    }

    private String RegModelToString(RegistrationModel model) {
        String result = String.join(",",
                model.getFullName(),
                model.getUserName(),
                model.getEmail(),
                model.getPassword());

        return result;
    }
}
