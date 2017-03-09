package com.levymoreira.repository;

import com.levymoreira.domain.CreditCardInvoice;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CreditCardInvoice entity.
 */
@SuppressWarnings("unused")
public interface CreditCardInvoiceRepository extends JpaRepository<CreditCardInvoice,Long> {

}
