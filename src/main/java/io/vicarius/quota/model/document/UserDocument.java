package io.vicarius.quota.model.document;

import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Setter
@Getter
@Document(indexName = "user")
public class UserDocument {

    @Id
    String id;
    private String firstName;
    private String lastName;
    private LocalDateTime lastLoginTimeUtc = LocalDateTime.now();

}
