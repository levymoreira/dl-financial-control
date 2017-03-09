package com.levymoreira.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A InstallmentGroup.
 */
@Entity
@Table(name = "installment_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "installmentgroup")
public class InstallmentGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "description")
    private String description;

    @Column(name = "installments")
    private Integer installments;

    @Column(name = "amount", precision=10, scale=2)
    private BigDecimal amount;

    @ManyToOne
    private AccountName account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public InstallmentGroup date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public InstallmentGroup description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInstallments() {
        return installments;
    }

    public InstallmentGroup installments(Integer installments) {
        this.installments = installments;
        return this;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public InstallmentGroup amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public AccountName getAccount() {
        return account;
    }

    public InstallmentGroup account(AccountName accountName) {
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
        InstallmentGroup installmentGroup = (InstallmentGroup) o;
        if (installmentGroup.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, installmentGroup.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InstallmentGroup{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", description='" + description + "'" +
            ", installments='" + installments + "'" +
            ", amount='" + amount + "'" +
            '}';
    }
}
