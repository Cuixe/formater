package org.cuixe.formater.vo;

public enum Types {

    INTEGER("entero", Integer.class),
    DECIMAL("decimal", Double.class),
    STRING("texto", String.class),
    NONE("", null);

    private String value;
    private Class type;

    Types(String value, Class type) {
        this.value = value;
        this.type = type;
    }

    public static Types getType(String name) {
        for(Types type : values()) {
            if (name.equals(type.value)) {
                return type;
            }
        }
        return NONE;
    }
}
