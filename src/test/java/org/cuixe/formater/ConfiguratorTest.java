package org.cuixe.formater;

import org.cuixe.formater.vo.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ConfiguratorTest {

    Configurator configurator;

    @Before
    public void setup() {
        configurator = new Configurator("src/test/resources/configuration.properties");
    }

    @Test
    public void load(){
        List<Field> fields = configurator.getFields();
        Assert.assertTrue(fields.size() == 3);
        Assert.assertTrue(fields.get(0).getIndex() == 1);
        Assert.assertTrue(fields.get(1).getIndex() == 2);
        Assert.assertTrue(fields.get(2).getIndex() == 3);
    }

}