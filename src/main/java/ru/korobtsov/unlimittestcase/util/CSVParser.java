package ru.korobtsov.unlimittestcase.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;
import ru.korobtsov.unlimittestcase.entity.Order;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CSVParser implements Parser{

    CSVReader reader;

    public List<List<String>> parse(String filename) throws IOException, CsvException {
        List<Order> orders = new ArrayList<>();
        reader = new CSVReader(new FileReader(filename));
        List<List<String>> res = new ArrayList<>();
        List<String[]> list = reader.readAll();
        for (String[] arr : list){
            res.add(Arrays.asList(arr));
        }
        return res;
    }
}
