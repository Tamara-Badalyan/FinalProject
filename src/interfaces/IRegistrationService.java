package interfaces;

import model.Users.RegistrationModel;

import java.util.ArrayList;

public interface IRegistrationService {
    void RegisterUser(RegistrationModel model);
    RegistrationModel createRegModel();
    ArrayList<RegistrationModel> GetList(String path);
    boolean isUserExist(String userName,String password);
}
