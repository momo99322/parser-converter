package ru.korobtsov.unlimittestcase.util;

import ru.korobtsov.unlimittestcase.entity.Message;

public class ConverterImpl  implements Converter{


    public String convert(Message message){
        return message.toString();
    }
}
