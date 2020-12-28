package enums;

public enum PhoneBrandTypeEnum {
    APPLE("Apple"),
    SAMSUNG("Samsung"),
    HUAWEI("Huawei"),
    XIAOMI("Xiaomi"),
    NOKIA("Nokia");

    private final String name;
    private PhoneBrandTypeEnum(String name) {
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
