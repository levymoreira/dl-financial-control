package com.levymoreira.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CreditCardInvoice.
 */
@Entity
@Table(name = "credit_card_invoice")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CreditCardInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @ManyToOne
    private AccountName account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public CreditCardInvoice year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public CreditCardInvoice month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public AccountName getAccount() {
        return account;
    }

    public CreditCardInvoice account(AccountName accountName) {
        this.account = accountName;
        return this;
    }

    public void setAccount(AccountName accountName) {
        this.account = accountName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreditCardInvoice creditCardInvoice = (CreditCardInvoice) o;
        if (creditCardInvoice.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, creditCardInvoice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CreditCardInvoice{" +
            "id=" + id +
            ", year='" + year + "'" +
            ", month='" + month + "'" +
            '}';
    }
}
