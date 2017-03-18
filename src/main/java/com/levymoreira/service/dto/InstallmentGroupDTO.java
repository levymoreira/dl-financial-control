package com.levymoreira.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the InstallmentGroup entity.
 */
public class InstallmentGroupDTO implements Serializable {

    private Long id;

    private ZonedDateTime date;

    private String description;

    private Integer installments;

    private BigDecimal amount;

    private Long accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

        InstallmentGroupDTO installmentGroupDTO = (InstallmentGroupDTO) o;

        if ( ! Objects.equals(id, installmentGroupDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InstallmentGroupDTO{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", description='" + description + "'" +
            ", installments='" + installments + "'" +
            ", amount='" + amount + "'" +
            '}';
    }
}
