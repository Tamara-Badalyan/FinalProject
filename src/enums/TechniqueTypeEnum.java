package enums;

public enum TechniqueTypeEnum {
    phone("phone"),
    tv("tv"),
    computer("computer");

    private final String name;
    private TechniqueTypeEnum(String name) {
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
