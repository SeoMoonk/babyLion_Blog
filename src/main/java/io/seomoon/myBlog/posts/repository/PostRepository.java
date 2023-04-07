package io.seomoon.myBlog.posts.repository;

import io.seomoon.myBlog.posts.Entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //title 이 포함된 객체들을 가져와라.
    List<Post> findAllByTitleContaining(String title, Pageable pageable);

}
