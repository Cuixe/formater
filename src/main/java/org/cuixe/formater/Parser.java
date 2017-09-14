package org.cuixe.formater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Parser {

    private int parsedLine = 0;
    private Configurator configurator;

    public void parse(String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            bufferedReader.lines().forEach(this::parseLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parseLine(String line) {
        parsedLine++;
    }

    public void setConfigurator(Configurator configurator) {
        this.configurator = configurator;
    }
}
