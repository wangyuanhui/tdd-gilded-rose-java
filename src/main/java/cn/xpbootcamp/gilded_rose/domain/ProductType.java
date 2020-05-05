package cn.xpbootcamp.gilded_rose.domain;

public enum ProductType {
    COMMON,
    AGEDBRIE,
    BACKSTAGEPASS,
    SULFURAS;

    @Override
    public String toString() {
        return super.name();
    }
}
