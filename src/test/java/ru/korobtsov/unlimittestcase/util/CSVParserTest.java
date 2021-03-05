package ru.korobtsov.unlimittestcase.util;

import com.opencsv.exceptions.CsvException;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CSVParserTest {

    @Test
    public void parse_Should_Write_And_Parse_Test_File_With_Several_Lines() throws IOException, CsvException, ParseException {
        Parser parser = new CSVParser();

        List<String> ord1 = Arrays.asList("1", "100", "USD", "оплата заказа");
        List<String> ord2 = Arrays.asList("3", "300", "EUR", "оплата заказа");


        List<List<String>> list = parser.parse("src/test/resources/test1.csv");

        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list.get(0), ord1);
        Assert.assertEquals(list.get(1), ord2);
    }


    @Test
    public void parse_Should_Write_And_Parse_Test_File_With_One_Line() throws IOException, CsvException, ParseException {
        Parser parser = new CSVParser();

        List<String> ord = Arrays.asList("1", "100", "USD", "оплата заказа");


        Assert.assertEquals(parser.parse("src/test/resources/test2.csv").get(0), ord);
    }

    @Test(expected = IOException.class)
    public void parse_Should_Throw_IO_Exception_If_File_Does_Not_Exist() throws IOException, CsvException, ParseException {
        Parser parser = new CSVParser();
        parser.parse("src/test/resources/incorrectPath.csv");
    }
}