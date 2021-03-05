package ru.korobtsov.unlimittestcase.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JSONParser implements Parser{
    public List<List<String>> parse(String filename) throws IOException, ParseException {

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();

        List<JSONObject> jsonObjectList = new ArrayList<>();
        String line;
        while (true){
            line = reader.readLine();
            if (line == null){
                break;
            }
            jsonObjectList.add((JSONObject) jsonParser.parse(line));
        }

        List<List<String>> res = new ArrayList<>();


        for (JSONObject jsonObject : jsonObjectList){
            List<String> list = new ArrayList<>();
            if (jsonObject.get("orderId")!=null) list.add(jsonObject.get("orderId").toString());
            if (jsonObject.get("amount")!=null) list.add(jsonObject.get("amount").toString());
            if (jsonObject.get("currency")!=null) list.add(jsonObject.get("currency").toString());
            if (jsonObject.get("comment")!=null) list.add(jsonObject.get("comment").toString());
            res.add(list);
        }
        return res;
    }
}
