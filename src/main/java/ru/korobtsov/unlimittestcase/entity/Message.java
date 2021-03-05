package ru.korobtsov.unlimittestcase.entity;

import java.util.Objects;

public class Message {
    private Order order;
    private Long line;
    private String filename;
    private String result;

    public Message(Order order, Long line, String filename, String result) {
        this.order = order;
        this.line = line;
        this.filename = filename;
        this.result = result;
    }

    public Message() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getLine() {
        return line;
    }

    public void setLine(Long line) {
        this.line = line;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(order, message.order) && Objects.equals(line, message.line) && Objects.equals(result, message.result) && Objects.equals(filename, message.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, line, result, filename);
    }

    @Override
    public String toString() {
        return  "{" +
                "\"id\":" + order.getId() +
                ", \"amount\":" + order.getAmount() +
                ", \"comment\":\"" + order.getComment()+"\"" +
                ", \"filename\":\"" + filename +"\"" +
                ", \"line\":" + line +
                ", \"result\":\"" + result+ "\" " +
                '}';
    }
}
