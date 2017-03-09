package com.levymoreira.repository.search;

import com.levymoreira.domain.CreditCardInvoice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CreditCardInvoice entity.
 */
public interface CreditCardInvoiceSearchRepository extends ElasticsearchRepository<CreditCardInvoice, Long> {
}
