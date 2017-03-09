package com.levymoreira.repository.search;

import com.levymoreira.domain.InstallmentGroup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the InstallmentGroup entity.
 */
public interface InstallmentGroupSearchRepository extends ElasticsearchRepository<InstallmentGroup, Long> {
}
