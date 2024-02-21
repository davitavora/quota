package io.vicarius.quota.repository;

import io.vicarius.quota.model.document.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticsearchUserRepository extends ElasticsearchRepository<UserDocument, String> {

}
