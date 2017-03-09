package com.levymoreira.repository.search;

import com.levymoreira.domain.AccountType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AccountType entity.
 */
public interface AccountTypeSearchRepository extends ElasticsearchRepository<AccountType, Long> {
}
