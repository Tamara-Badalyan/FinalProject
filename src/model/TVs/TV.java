package model.TVs;

import exceptions.InputException;
import model.Phones.SmartPhone;
import model.Technique;

public class TV extends Technique {
    private String digitalTvTuner;
    private String operatingSystem;
    private String remoteControl;

    public TV(String modelName,String brandName, int price, int year, String country) {
        super(modelName,brandName, price, year, country);
    }

    public String getDigitalTvTuner() {
        return digitalTvTuner;
    }

    public void setDigitalTvTuner(String digitalTvTuner) {
        if (digitalTvTuner != null && digitalTvTuner.length() > 2)
            this.digitalTvTuner = digitalTvTuner;
        else
            throw  new InputException();

    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        if (operatingSystem != null && operatingSystem.length() > 2)
            this.operatingSystem = operatingSystem;
        else
            throw  new InputException();
    }

    public String getRemoteControl() {
        return remoteControl;
    }

    public void setRemoteControl(String remoteControl) {
        if (remoteControl != null && remoteControl.length() > 2)
            this.remoteControl = remoteControl;
        else
            throw  new InputException();
    }
    @Override
    public int compareTo(Object o) {
        TV s = (TV) o;
        return this.getModelName().compareTo(s.getModelName());
    }
}
