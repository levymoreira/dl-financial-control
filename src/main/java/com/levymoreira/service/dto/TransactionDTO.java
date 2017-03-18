package com.levymoreira.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.levymoreira.domain.enumeration.TransactionType;

/**
 * A DTO for the Transaction entity.
 */
public class TransactionDTO implements Serializable {

    private Long id;

    private TransactionType transactionType;

    private ZonedDateTime date;

    private Integer ordination;

    private String number;

    private String description;

    private String additionalInfo;

    private BigDecimal amount;

    private Boolean isDivided;

    private Boolean isTransfer;

    private Long userId;

    private Long installmentGroupId;

    private Long creditCardInvoiceId;

    private Long ownerId;

    private Long sourceId;

    private Long categoryId;

    private Long clientId;

    private Long accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
    public Integer getOrdination() {
        return ordination;
    }

    public void setOrdination(Integer ordination) {
        this.ordination = ordination;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Boolean getIsDivided() {
        return isDivided;
    }

    public void setIsDivided(Boolean isDivided) {
        this.isDivided = isDivided;
    }
    public Boolean getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(Boolean isTransfer) {
        this.isTransfer = isTransfer;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInstallmentGroupId() {
        return installmentGroupId;
    }

    public void setInstallmentGroupId(Long installmentGroupId) {
        this.installmentGroupId = installmentGroupId;
    }

    public Long getCreditCardInvoiceId() {
        return creditCardInvoiceId;
    }

    public void setCreditCardInvoiceId(Long creditCardInvoiceId) {
        this.creditCardInvoiceId = creditCardInvoiceId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long transactionId) {
        this.ownerId = transactionId;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long transactionId) {
        this.sourceId = transactionId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

        TransactionDTO transactionDTO = (TransactionDTO) o;

        if ( ! Objects.equals(id, transactionDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
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
