package interfaces;

import model.Phones.SmartPhone;
import model.Technique;

import java.util.ArrayList;

public interface IActionService<T> {
    Technique maxPrice(ArrayList<T> phones);
    Technique newestProduct(ArrayList<T> phones);
    void orderByPrice(ArrayList<T> phones);
    void orderByYear(ArrayList<T> phones);
    Technique cheapestProduct(ArrayList<T> phones);

}
