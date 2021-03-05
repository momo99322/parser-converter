package ru.korobtsov.unlimittestcase.util;

import com.opencsv.exceptions.CsvException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface Parser {
    List<List<String>> parse(String filename) throws IOException, CsvException, ParseException;
}
