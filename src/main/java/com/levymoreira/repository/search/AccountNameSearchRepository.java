package com.levymoreira.repository.search;

import com.levymoreira.domain.AccountName;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AccountName entity.
 */
public interface AccountNameSearchRepository extends ElasticsearchRepository<AccountName, Long> {
}
