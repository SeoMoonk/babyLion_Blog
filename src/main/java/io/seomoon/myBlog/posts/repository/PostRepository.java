package io.seomoon.myBlog.posts.repository;

import io.seomoon.myBlog.posts.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
