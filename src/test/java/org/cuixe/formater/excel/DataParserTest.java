package org.cuixe.formater.excel;

import org.junit.Before;
import org.junit.Test;

public class DataParserTest {

    LayoutParser layoutParser;
    DataParser dataParser;

    @Test
    public void setup() throws Exception {
        layoutParser = new LayoutParser("src/test/resources/formatos.xlsx");
        dataParser = new DataParser("src/test/resources/SDA016866171701041.txt", "src/test/resources/SDA016866171701041.xlsx");
        dataParser = new DataParser("src/test/resources/SPA010501781612301.txt", "src/test/resources/SPA010501781612301.xlsx");
        dataParser.setLayoutParser(layoutParser);
        dataParser.parse();
    }


}