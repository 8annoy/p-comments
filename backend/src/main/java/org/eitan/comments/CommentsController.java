package org.eitan.comments;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.querydsl.QSort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequestMapping("/api/v1/comments")
public class CommentsController {

    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsController(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getAllComments() {
        log.debug("Fetching all comments");
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC,"timestamp"));
        return ResponseEntity.ok(toList(commentsRepository.findAll(
                new Sort(new Sort.Order(Sort.Direction.DESC, "timestamp")))));
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        log.debug("Fetching all comments");
        Comment createdComment = commentsRepository.save(comment);
        return ResponseEntity
                .created(createResourceUrl(createdComment.getId()))
                .body(createdComment);
    }

    private static URI createResourceUrl(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }


}
