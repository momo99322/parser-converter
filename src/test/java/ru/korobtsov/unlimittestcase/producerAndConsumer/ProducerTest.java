package ru.korobtsov.unlimittestcase.producerAndConsumer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.korobtsov.unlimittestcase.entity.Message;
import ru.korobtsov.unlimittestcase.entity.Order;
import ru.korobtsov.unlimittestcase.producerAndConsumer.Producer;

import java.math.BigDecimal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerTest {


    private BlockingQueue<Message> blockingQueue;

    @Before
    public void init(){
        blockingQueue = new LinkedBlockingQueue<>();
    }

    @Test
    public void run_Should_Add_Message_To_Queue(){
        Producer producer = new Producer(blockingQueue, new String[]{"filename.csv"});
        producer.run();

        Assert.assertEquals(blockingQueue.size(), 2);
    }

    @Test
    public void run_Should_Add_Blank_Message_To_Queue() throws InterruptedException {
        Producer producer = new Producer(blockingQueue, new String[]{"filename.csv"});
        producer.run();

        blockingQueue.take();

        Assert.assertEquals(blockingQueue.take(), new Message());
    }

    @Test
    public void run_Should_Add_Valid_Csv_Message_To_Queue() throws InterruptedException {
        Producer producer = new Producer(blockingQueue, new String[]{"src/test/resources/test1.csv"});

        producer.run();

        Assert.assertEquals(blockingQueue.size(), 3);

        Assert.assertEquals(blockingQueue.take(), new Message(new Order(1L, new BigDecimal("100"), "USD", "оплата заказа"),
                1L, "src/test/resources/test1.csv", "OK"));
        Assert.assertEquals(blockingQueue.take(), new Message(new Order(3L, new BigDecimal("300"), "EUR", "оплата заказа"),
                2L, "src/test/resources/test1.csv", "OK"));
        Assert.assertEquals(blockingQueue.take(), new Message());
    }

    @Test
    public void run_Should_Add_Valid_Json_Message_To_Queue() throws InterruptedException {
        Producer producer = new Producer(blockingQueue, new String[]{"src/test/resources/test1.json"});

        producer.run();

        Assert.assertEquals(blockingQueue.size(), 3);

        Assert.assertEquals(blockingQueue.take(), new Message(new Order(2L, new BigDecimal("1.23"), "USD", "оплата заказа"),
                1L, "src/test/resources/test1.json", "OK"));
        Assert.assertEquals(blockingQueue.take(), new Message(new Order(3L, new BigDecimal("1.24"), "EUR", "оплата заказа"),
                2L, "src/test/resources/test1.json", "OK"));
        Assert.assertEquals(blockingQueue.take(), new Message());
    }

    @Test
    public void run_Should_Add_Csv_Message_With_Wrong_Format_To_Queue() throws InterruptedException {
        Producer producer = new Producer(blockingQueue, new String[]{"src/test/resources/test3.csv"});

        producer.run();

        Assert.assertEquals(blockingQueue.size(), 2);

        Assert.assertEquals(blockingQueue.take(), new Message(new Order(null, null, "USD", "оплата заказа"),
                1L, "src/test/resources/test3.csv", "Wrong format"));
        Assert.assertEquals(blockingQueue.take(), new Message());
    }

    @Test
    public void run_Should_Add_Json_Message_With_Wrong_Format_To_Queue() throws InterruptedException {
        Producer producer = new Producer(blockingQueue, new String[]{"src/test/resources/test3.json"});

        producer.run();

        Assert.assertEquals(blockingQueue.size(), 2);

        Assert.assertEquals(blockingQueue.take(), new Message(new Order(null, null, "USD", "оплата заказа"),
                1L, "src/test/resources/test3.json", "Wrong format"));
        Assert.assertEquals(blockingQueue.take(), new Message());
    }

    @Test
    public void run_Should_Add_Message_With_Invalid_Extension() throws InterruptedException {
        Producer producer = new Producer(blockingQueue, new String[]{"src/test/resources/test.txt"});

        producer.run();

        Assert.assertEquals(blockingQueue.size(), 2);

        Assert.assertEquals(blockingQueue.take(), new Message(new Order(null, null, null, null),
                null, "src/test/resources/test.txt", "Invalid file extension"));
        Assert.assertEquals(blockingQueue.take(), new Message());
    }

    @Test
    public void run_Should_Add_Message_When_File_Does_Not_Exist() throws InterruptedException {
        Producer producer = new Producer(blockingQueue, new String[]{"src/test/resources/doesnotexist.csv"});

        producer.run();

        Assert.assertEquals(blockingQueue.size(), 2);

        Assert.assertEquals(blockingQueue.take(), new Message(new Order(null, null, null, null),
                null, "src/test/resources/doesnotexist.csv", "File doesn't exist"));
        Assert.assertEquals(blockingQueue.take(), new Message());
    }

    @Test
    public void run_Should_Add_Csv_Message_If_Line_Has_Invalid_Number_Of_Parameters() throws InterruptedException {
        Producer producer = new Producer(blockingQueue, new String[]{"src/test/resources/test4.csv"});

        producer.run();

        Assert.assertEquals(blockingQueue.size(), 2);

        Assert.assertEquals(blockingQueue.take(), new Message(new Order(null, null, null, null),
                1L, "src/test/resources/test4.csv", "Invalid number of parameters"));
        Assert.assertEquals(blockingQueue.take(), new Message());
    }

    @Test
    public void run_Should_Add_Json_Message_If_Line_Has_Invalid_Number_Of_Parameters() throws InterruptedException {
        Producer producer = new Producer(blockingQueue, new String[]{"src/test/resources/test4.json"});

        producer.run();

        Assert.assertEquals(blockingQueue.size(), 2);

        Assert.assertEquals(blockingQueue.take(), new Message(new Order(null, null, null, null),
                1L, "src/test/resources/test4.json", "Invalid number of parameters"));
        Assert.assertEquals(blockingQueue.take(), new Message());
    }



}