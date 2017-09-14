package org.cuixe.formater.excel.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Format {

    private String type = null;
    private List<Field> fields = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Field> getFields() {
        Collections.sort(fields, (lhs, rhs) -> {
            return Integer.compare(lhs.getIndex(), rhs.getIndex());
        });
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
