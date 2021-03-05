package ru.korobtsov.unlimittestcase.util;

import org.junit.Assert;
import org.junit.Test;
import ru.korobtsov.unlimittestcase.entity.Message;
import ru.korobtsov.unlimittestcase.entity.Order;

import java.math.BigDecimal;

public class ConverterImplTest {

    @Test
    public void convert_Should_return_valid_message() {
        ConverterImpl converterImpl = new ConverterImpl();
        Message message = new Message(new Order(1L, new BigDecimal("900"), "USD", "заказ"), 2L, "test.csv", "OK");
        Assert.assertEquals(converterImpl.convert(message), "{\"id\":1, \"amount\":900, \"comment\":\"заказ\", \"filename\":\"test.csv\", \"line\":2, \"result\":\"OK\" }");
    }

    @Test
    public void convert_Should_return_null_values() {
        ConverterImpl converterImpl = new ConverterImpl();
        Message message = new Message(new Order(null, null, null, null), null, "test.csv", "File does not exist");
        Assert.assertEquals(converterImpl.convert(message), "{\"id\":null, \"amount\":null, \"comment\":\"null\", \"filename\":\"test.csv\", \"line\":null, \"result\":\"File does not exist\" }");
    }
}