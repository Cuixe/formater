package org.cuixe.formater;

import org.cuixe.formater.excel.DataParser;
import org.cuixe.formater.excel.LayoutParser;

import java.io.File;

public class Main {

    private static LayoutParser layoutParser;

    public static void main(String args[]) {
        try {
            File directory = new File("./files");
            if (!directory.isDirectory()) {
                System.out.printf("La carpeta " + directory.getAbsolutePath() + " no existe");
                System.exit(1);
            }
            layoutParser = new LayoutParser("files/formatos.xlsx");
            String[] files = directory.list();
            for (String file : files ) {
                if (file.contains(".txt")) {
                    execute("./files/" + file);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void execute(String file) throws Exception {
        System.out.println("Transformando archivo: "  + file);
        String result = file.replace(".txt", ".xlsx");
        DataParser dataParser = new DataParser(file, result);
        dataParser.setLayoutParser(layoutParser);
        dataParser.parse();
        System.out.println("Terminado: "  + file);
    }
}
