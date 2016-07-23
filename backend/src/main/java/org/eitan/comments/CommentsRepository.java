package org.eitan.comments;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends MongoRepository<Comment, String> {
}
