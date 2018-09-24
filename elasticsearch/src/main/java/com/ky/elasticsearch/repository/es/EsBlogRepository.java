package com.ky.elasticsearch.repository.es;

import com.ky.elasticsearch.domain.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {

    Page<EsBlog> findByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary,
                                                                             String content, PageRequest pageRequest);
}
