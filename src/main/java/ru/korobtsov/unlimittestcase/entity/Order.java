package ru.korobtsov.unlimittestcase.entity;


import java.math.BigDecimal;
import java.util.Objects;

public class Order {

    private Long id;
    private BigDecimal amount;
    private String currency;
    private String comment;


    public Order() {
    }


    public Order(Long id, BigDecimal amount, String currency, String comment) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getComment() {
        return comment;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(amount, order.amount) && Objects.equals(currency, order.currency) && Objects.equals(comment, order.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency, comment);
    }
}
