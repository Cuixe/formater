package org.cuixe.formater.vo;

public class Field {

    private int index = 0;
    private String name =null;
    private int size = -1;
    private Types type = Types.NONE;


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Types getType() {
        return type;
    }

    public boolean isComplete() {
        return name != null && size != -1 && type != Types.NONE;
    }
}
