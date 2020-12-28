package interfaces;

import model.Phones.SmartPhone;

import java.awt.*;
import java.util.ArrayList;

public interface CRDService<T> {
    void printInfoOfProduct(T phone);
    void printInfoOfProducts(ArrayList<T> phones);
    ArrayList<T> readFromFile(String path);
    void clearAllData(String path);
    void addProductIntoFile(String path);
    boolean containsProduct(String path);
}
