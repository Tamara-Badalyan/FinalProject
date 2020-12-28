package enums;

public enum TvBrandTypeEnum {
    SAMSUNG("Samsung"),
    LG("LG"),
    PANASONIC("Panasonic"),
    SONY("Sony");

    private final String name;
    TvBrandTypeEnum(String name) {
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
