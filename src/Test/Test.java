package Test;

import enums.TechniqueTypeEnum;
import interfaces.CRDService;
import interfaces.IActionService;
import model.Technique;
import service.*;

import java.util.Scanner;

import static statics.Connection.*;

public class Test {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        RegistrationService regService = new RegistrationService();
        IActionService<Technique> actionService = new ActionService();
        String type = null, path = null;
        System.out.println("Hi please select -> 1.Registration, 2.Login");
        int r = s.nextInt();
        if (r == 1) {
            var model = regService.createRegModel();
            regService.RegisterUser(model);
        } else {
            System.out.println("Enter username : ");
            String userName = s.next();
            System.out.println("Enter password");
            String password = s.next();
            if (regService.isUserExist(userName,password)) {
                System.out.println("Succes");
            }
            else{
                System.out.println("Log in failed");
                return;
            }
        }

        System.out.println("***_Menu_***");
        System.out.println("***_Select the type of product***");
        System.out.println("1.Phones");
        System.out.println("2.Computers");
        System.out.println("3.TVs");
        System.out.println("4.Exit");
        System.out.println("*************");

        boolean isActive = true;
        while (isActive) {
            System.out.println("Enter number");
            CRDService service = null;
            int number = s.nextInt();
            switch (number) {
                case 1:
                    service = new PhoneService();
                    type = TechniqueTypeEnum.phone.getName();
                    path = PHONE_PATH;
                    break;
                case 2:
                    service = new ComputerService();
                    type = TechniqueTypeEnum.computer.getName();
                    path = COMPUTER_PATH;
                    break;
                case 3:
                    service = new TvService();
                    type = TechniqueTypeEnum.tv.getName();
                    path = TV_PATH;
                    System.out.println("************");
                    break;
                case 4:
                    System.out.println("---Exit---");
                    System.out.println("************");
                    isActive = false;
                    break;
                default:
                    System.out.println("Invalid input,please enter again!!");
            }
            System.out.println("***_Please select an action_***");
            System.out.println("1.Add " + type);
            System.out.println("2.Print all " + type + "s");
            System.out.println("3.Maximum Price of " + type);
            System.out.println("4.Cheapest " + type);
            System.out.println("5.Order By Price");
            System.out.println("6.Order By Year");
            System.out.println("7.Check contains element or no?");
            System.out.println("8.Exit");
            boolean isActive1 = true;
            while (isActive1) {
                System.out.println("Enter number");

                int number1 = s.nextInt();
                switch (number1) {
                    case 1:

                        service.addProductIntoFile(path);
                        System.out.println("*****Done*******");
                        break;
                    case 2:
                        service.printInfoOfProducts(service.readFromFile(path));
                        System.out.println("*****Done*******");
                        break;
                    case 3:

                        var products = service.readFromFile(path);
                        System.out.println("Max price is : " + actionService.maxPrice(products));
                        System.out.println("*****Done*******");
                        break;
                    case 4:
                        service.printInfoOfProduct(actionService.cheapestProduct(service.readFromFile(path)));
                        System.out.println("*****Done*******");
                        break;
                    case 5:
                        var result = service.readFromFile(path);
                        actionService.orderByPrice(result);
                        service.printInfoOfProducts(result);
                        System.out.println("*****Done*******");
                        break;
                    case 6:
                        var result1 = service.readFromFile(path);
                        actionService.orderByYear(result1);
                        service.printInfoOfProducts(result1);
                        System.out.println("*****Done*******");
                        break;
                    case 7:
                        System.out.println("Please enter a product you wants to check");
                        System.out.println(service.containsProduct(path));
                        System.out.println("*****Done*******");
                        break;
                    case 8:
                        System.out.println("---Exit---");
                        System.out.println("************");
                        isActive1 = false;
                        break;
                    default:
                        System.out.println("Invalid input, please enter again!!");
                }

            }
        }
    }
}
