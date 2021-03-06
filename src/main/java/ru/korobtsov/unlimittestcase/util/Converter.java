package ru.korobtsov.unlimittestcase.util;

import ru.korobtsov.unlimittestcase.producerAndConsumer.Message;

public interface Converter {
    String convert(Message message);
}
