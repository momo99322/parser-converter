package ru.korobtsov.unlimittestcase.util;

import ru.korobtsov.unlimittestcase.entity.Message;

public interface Converter {
    String convert(Message message);
}
