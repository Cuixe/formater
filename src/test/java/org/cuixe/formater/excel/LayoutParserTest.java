package org.cuixe.formater.excel;

import org.cuixe.formater.excel.vo.Format;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class LayoutParserTest {

    LayoutParser layoutParser;

    @Before
    public void setup() throws Exception {
        layoutParser = new LayoutParser("src/test/resources/formatos.xlsx");
    }

    @Test
    public void loadExcel() {
        List<Format> formats = layoutParser.getFormats();
        Format format = formats.get(0);
        Assert.assertEquals(format.getType(), "0100");
        Assert.assertTrue(format.getFields().get(0).getIndex() == 1);
        Assert.assertTrue(format.getFields().get(1).getIndex() == 2);
        Assert.assertTrue(format.getFields().get(2).getIndex() == 3);
        Assert.assertTrue(format.getFields().get(3).getIndex() == 4);
        Assert.assertTrue(format.getFields().get(4).getIndex() == 5);
        Assert.assertTrue(format.getFields().get(5).getIndex() == 6);
        Assert.assertTrue(format.getFields().get(6).getIndex() == 7);
        Assert.assertTrue(format.getFields().get(1).getInit() == 5);
        Assert.assertTrue(format.getFields().get(1).getSize() == 30);
    }
}