package ru.korobtsov.unlimittestcase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.korobtsov.unlimittestcase.producerAndConsumer.Consumer;
import ru.korobtsov.unlimittestcase.entity.Message;
import ru.korobtsov.unlimittestcase.producerAndConsumer.Producer;
import ru.korobtsov.unlimittestcase.UnlimitTestCaseApplication;
import ru.korobtsov.unlimittestcase.util.ConverterImpl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Configuration
public class SpringConfig {



    @Bean
    public ConverterImpl converterInit(){
        return new ConverterImpl();
    }

    @Bean
    public BlockingQueue<Message> blockingQueueInit(){return new ArrayBlockingQueue<>(10);}

    @Bean
    public Producer producerInit(){
        return new Producer(blockingQueueInit(), UnlimitTestCaseApplication.ARGS);
    }

    @Bean
    public Consumer consumerInit(){
        return new Consumer(blockingQueueInit(), converterInit());
    }

}
