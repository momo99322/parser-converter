package ru.korobtsov.unlimittestcase;

import org.junit.Test;
import ru.korobtsov.unlimittestcase.entity.Message;
import ru.korobtsov.unlimittestcase.entity.Order;
import ru.korobtsov.unlimittestcase.producerAndConsumer.Consumer;
import ru.korobtsov.unlimittestcase.util.ConverterImpl;

import java.math.BigDecimal;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConsumerTest {

    @Test
    public void run_Should_Print_Message() throws InterruptedException {
        BlockingQueue<Message> blockingQueue = new ArrayBlockingQueue<>(10);
        Consumer consumer = new Consumer(blockingQueue, new ConverterImpl());

        blockingQueue.put(new Message(new Order(1L, new BigDecimal("100"),
                "USD", "оплата заказа"), 1L,
                "src/test/resources/test.csv", "OK"));
        blockingQueue.put(new Message());

        consumer.run();
    }

    @Test(timeout = 100)
    public void run_Should_Stop() throws InterruptedException {
        BlockingQueue<Message> blockingQueue = new ArrayBlockingQueue<>(10);
        Consumer consumer = new Consumer(blockingQueue, new ConverterImpl());
        blockingQueue.put(new Message());

        consumer.run();
    }
}