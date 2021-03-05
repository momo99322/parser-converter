package ru.korobtsov.unlimittestcase.producerAndConsumer;

import com.opencsv.exceptions.CsvException;
import org.json.simple.parser.ParseException;
import ru.korobtsov.unlimittestcase.entity.Message;
import ru.korobtsov.unlimittestcase.entity.Order;
import ru.korobtsov.unlimittestcase.util.CSVParser;
import ru.korobtsov.unlimittestcase.util.JSONParser;
import ru.korobtsov.unlimittestcase.util.Parser;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    private final BlockingQueue<Message> blockingQueue;
    private final String[] args;


    public Producer(BlockingQueue<Message> blockingQueue, String[] args) {
        this.blockingQueue = blockingQueue;
        this.args = args;
    }

    public void run() {
        try {
            for (String arg : args) {
                List<Message> messages = produce(arg);
                for(Message message :messages )
                    blockingQueue.put(message);
            }
            blockingQueue.put(new Message());
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    protected List<Message> produce(String filename) {
        Parser parser;
        long i = 1L;
        List<Message> messages = new ArrayList<>();
        List<List<String>> lines;

        if (filename.contains(".csv")) {
            parser = new CSVParser();
        } else if (filename.contains(".json")) {
            parser = new JSONParser();
        } else {
            messages.add(new Message(new Order(null, null, null, null), null, filename, "Invalid file extension"));
            return messages;
        }

        try {
            lines = parser.parse(filename);
        } catch (IOException ex) {
            messages.add(new Message(new Order(null, null, null, null), null, filename, "File doesn't exist"));
            return messages;
        } catch (CsvException | ParseException ex) {
            messages.add(new Message(new Order(null, null, null, null), null, filename, ex.getMessage()));
            return messages;
        }

        for (List<String> line : lines) {
            Order order = new Order();
            Message message = new Message();
            message.setLine(i);
            message.setFilename(filename);

            if (line.size() != 4){
                message = new Message(new Order(null, null, null, null), i, filename, "Invalid number of parameters");
                messages.add(message);
                continue;
            }
            message.setResult("OK");

            try{
                order.setId(Long.parseLong(line.get(0)));
            }
            catch (NumberFormatException ex){
                order.setId(null);
                message.setResult("Wrong format");
            }
            try{
                order.setAmount(new BigDecimal(line.get(1)));
            }
            catch (NumberFormatException ex){
                order.setAmount(null);
                message.setResult("Wrong format");
            }
            order.setCurrency(line.get(2));
            order.setComment(line.get(3));
            message.setOrder(order);

            i++;
            messages.add(message);
        }

        return messages;
    }
}
