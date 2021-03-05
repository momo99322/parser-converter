package ru.korobtsov.unlimittestcase.util;

import com.opencsv.exceptions.CsvException;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JSONParserTest {
    @Test
    public void parse_Should_Write_And_Parse_Test_File_With_Several_Lines() throws IOException, CsvException, ParseException {
        Parser parser = new JSONParser();

        List<String> ord1 = Arrays.asList("2", "1.23", "USD", "оплата заказа");
        List<String> ord2 = Arrays.asList("3", "1.24", "EUR", "оплата заказа");

        List<List<String>> list = parser.parse("src/test/resources/test1.json");

        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list.get(0), ord1);
        Assert.assertEquals(list.get(1), ord2);
    }

    @Test
    public void parse_Should_Write_And_Parse_Test_File_With_One_Line() throws IOException, CsvException, ParseException {
        Parser parser = new JSONParser();

        List<String> ord = Arrays.asList("2", "1.23", "USD", "оплата заказа");


        Assert.assertEquals(parser.parse("src/test/resources/test2.json").get(0), ord);
    }

}