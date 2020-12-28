package service;

import interfaces.IActionService;
import model.Technique;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActionService implements IActionService<Technique> {
    @Override
    public Technique newestProduct(ArrayList<Technique> models) {
        int year = models.get(0).getYear();
        Technique model = null;
        for (var item : models) {
            if (item.getYear() > year) {
                year = item.getYear();
                model = item;
            }
        }
        return model;
    }

    @Override
    public Technique maxPrice(ArrayList<Technique> models) {
        int maxPrice = models.get(0).getPrice();
        Technique model = null;
        for (var item : models) {
            if (item.getPrice() > maxPrice) {
                maxPrice = item.getPrice();
                model = item;
            }
        }
        return model;
    }

    @Override
    public void orderByPrice(ArrayList<Technique> models) {
        Collections.sort(models, new Comparator<Technique>() {
            @Override
            public int compare(Technique o1, Technique o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });
    }

    @Override
    public void orderByYear(ArrayList<Technique> models) {
        Collections.sort(models, new Comparator<Technique>() {
            @Override
            public int compare(Technique o1, Technique o2) {
                return o1.getYear() - o2.getYear();
            }
        });
    }

    @Override
    public Technique cheapestProduct(ArrayList<Technique> models) {
        int minPrice = models.get(0).getPrice();
        Technique model = null;
        for (var item : models) {
            if (item.getPrice() < minPrice) {
                minPrice = item.getPrice();
                model = item;
            }
        }
        return model;
    }

}
