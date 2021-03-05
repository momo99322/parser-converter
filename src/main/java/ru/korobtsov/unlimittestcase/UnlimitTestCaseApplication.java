package ru.korobtsov.unlimittestcase;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.korobtsov.unlimittestcase.producerAndConsumer.Consumer;
import ru.korobtsov.unlimittestcase.producerAndConsumer.Producer;
import ru.korobtsov.unlimittestcase.config.SpringConfig;

public class UnlimitTestCaseApplication {

    public static String[] ARGS;

    public static void main(final String[] args) {
        ARGS = args;
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        Thread producer = new Thread(context.getBean(Producer.class));
        Thread consumer = new Thread(context.getBean(Consumer.class));

        producer.start();
        consumer.start();
    }

}
