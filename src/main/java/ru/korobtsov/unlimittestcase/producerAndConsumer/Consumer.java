package ru.korobtsov.unlimittestcase.producerAndConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import ru.korobtsov.unlimittestcase.util.ConverterImpl;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    private final BlockingQueue<Message> blockingQueue;

    @Autowired
    private final ConverterImpl converterImpl;

    public Consumer(BlockingQueue<Message> blockingQueue, ConverterImpl converterImpl) {
        this.blockingQueue = blockingQueue;
        this.converterImpl = converterImpl;
    }

    public void run() {
        try {
            while (true) {
                Message message = blockingQueue.take();
                if (message.equals(new Message()))
                    break;
                consume(message);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void consume(Message message) {
        System.out.println(converterImpl.convert(message));
    }
}
