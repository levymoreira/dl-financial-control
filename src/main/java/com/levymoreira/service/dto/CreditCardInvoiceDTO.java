package com.levymoreira.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CreditCardInvoice entity.
 */
public class CreditCardInvoiceDTO implements Serializable {

    private Long id;

    private Integer year;

    private Integer month;

    private Long accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountNameId) {
        this.accountId = accountNameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CreditCardInvoiceDTO creditCardInvoiceDTO = (CreditCardInvoiceDTO) o;

        if ( ! Objects.equals(id, creditCardInvoiceDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CreditCardInvoiceDTO{" +
            "id=" + id +
            ", year='" + year + "'" +
            ", month='" + month + "'" +
            '}';
    }
}
