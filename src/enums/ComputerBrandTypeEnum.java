package enums;

public enum ComputerBrandTypeEnum {
    APPLE("Apple"),
    ASUS("Asus"),
    DELL("Dell"),
    Lenovo("Lenovo"),
    ACER("Acer");

    private final String name;
    ComputerBrandTypeEnum(String name) {
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
