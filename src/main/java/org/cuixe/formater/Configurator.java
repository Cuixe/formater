package org.cuixe.formater;

import org.cuixe.formater.vo.Field;
import org.cuixe.formater.vo.Types;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Configurator {

    private Properties properties = new Properties();
    private List<Field> fields = new ArrayList<>();
    private Map<String, Field> fieldMap = new HashMap<>();
    private int initLine = 0;

    public Configurator(String file) {
        loadFile(file);
    }

    private void loadFile(String file) {
        try {
            properties.load(new FileReader(new File(file)));
            parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parse() {
        Iterator<String> iterator = properties.stringPropertyNames().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (key.contains("campo")) {
                processField(key);
            }
        }
    }

    private void processField(String key) {
        String[] parts = key.split("\\.");
        if (key.contains(parts[0])) {
            if (!fieldMap.containsKey(parts[0])) {
                Field field = new Field();
                int index = Integer.valueOf(parts[0].replace("campo", ""));
                field.setIndex(index);
                fieldMap.put(parts[0], field);
            }
            Field field = fieldMap.get(parts[0]);
            String value = properties.getProperty(key);
            if (parts[1].equals("nombre")) {
                field.setName(value);
            } else if (parts[1].equals("longitud")) {
                field.setSize(Integer.valueOf(value));
            } else if (parts[1].equals("tipo")) {
                field.setType(Types.getType(value));
            }
        }
    }

    public List<Field> getFields() {
        if (fields.size() == 0) {
            for (String key : fieldMap.keySet()) {
                fields.add(fieldMap.get(key));
            }
        }

        Collections.sort(fields, new Comparator<Field>() {
            @Override
            public int compare(Field lhs, Field rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return Integer.compare(lhs.getIndex(), rhs.getIndex());
            }
        });

        return fields;
    }

}
