package com.levymoreira.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.levymoreira.domain.enumeration.TransactionType;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "ordination")
    private Integer ordination;

    @Column(name = "number")
    private String number;

    @Column(name = "description")
    private String description;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "amount", precision=10, scale=2)
    private BigDecimal amount;

    @Column(name = "is_divided")
    private Boolean isDivided;

    @Column(name = "is_transfer")
    private Boolean isTransfer;

    @ManyToOne
    private User user;

    @ManyToOne
    private InstallmentGroup installmentGroup;

    @ManyToOne
    private CreditCardInvoice creditCardInvoice;

    @ManyToOne
    private Transaction owner;

    @ManyToOne
    private Transaction source;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Client client;

    @ManyToOne
    private AccountName account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Transaction transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Transaction date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Integer getOrdination() {
        return ordination;
    }

    public Transaction ordination(Integer ordination) {
        this.ordination = ordination;
        return this;
    }

    public void setOrdination(Integer ordination) {
        this.ordination = ordination;
    }

    public String getNumber() {
        return number;
    }

    public Transaction number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public Transaction description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Transaction additionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Transaction amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean isIsDivided() {
        return isDivided;
    }

    public Transaction isDivided(Boolean isDivided) {
        this.isDivided = isDivided;
        return this;
    }

    public void setIsDivided(Boolean isDivided) {
        this.isDivided = isDivided;
    }

    public Boolean isIsTransfer() {
        return isTransfer;
    }

    public Transaction isTransfer(Boolean isTransfer) {
        this.isTransfer = isTransfer;
        return this;
    }

    public void setIsTransfer(Boolean isTransfer) {
        this.isTransfer = isTransfer;
    }

    public User getUser() {
        return user;
    }

    public Transaction user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InstallmentGroup getInstallmentGroup() {
        return installmentGroup;
    }

    public Transaction installmentGroup(InstallmentGroup installmentGroup) {
        this.installmentGroup = installmentGroup;
        return this;
    }

    public void setInstallmentGroup(InstallmentGroup installmentGroup) {
        this.installmentGroup = installmentGroup;
    }

    public CreditCardInvoice getCreditCardInvoice() {
        return creditCardInvoice;
    }

    public Transaction creditCardInvoice(CreditCardInvoice creditCardInvoice) {
        this.creditCardInvoice = creditCardInvoice;
        return this;
    }

    public void setCreditCardInvoice(CreditCardInvoice creditCardInvoice) {
        this.creditCardInvoice = creditCardInvoice;
    }

    public Transaction getOwner() {
        return owner;
    }

    public Transaction owner(Transaction transaction) {
        this.owner = transaction;
        return this;
    }

    public void setOwner(Transaction transaction) {
        this.owner = transaction;
    }

    public Transaction getSource() {
        return source;
    }

    public Transaction source(Transaction transaction) {
        this.source = transaction;
        return this;
    }

    public void setSource(Transaction transaction) {
        this.source = transaction;
    }

    public Category getCategory() {
        return category;
    }

    public Transaction category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Client getClient() {
        return client;
    }

    public Transaction client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public AccountName getAccount() {
        return account;
    }

    public Transaction account(AccountName accountName) {
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
        Transaction transaction = (Transaction) o;
        if (transaction.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, transaction.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + id +
            ", transactionType='" + transactionType + "'" +
            ", date='" + date + "'" +
            ", ordination='" + ordination + "'" +
            ", number='" + number + "'" +
            ", description='" + description + "'" +
            ", additionalInfo='" + additionalInfo + "'" +
            ", amount='" + amount + "'" +
            ", isDivided='" + isDivided + "'" +
            ", isTransfer='" + isTransfer + "'" +
            '}';
    }
}
