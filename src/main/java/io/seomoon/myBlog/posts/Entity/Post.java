package io.seomoon.myBlog.posts.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Entity
public class Post {

    //번호
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //제목
    private String title;

    //내용
    @Column(columnDefinition = "TEXT")
    private String body;

    //언제 썼는지
    private LocalDateTime createdAt = LocalDateTime.now();

    //언제 수정되었는지
    private LocalDateTime updatedAt = LocalDateTime.now();

    //누가 썼는지


    public static Post createPost(String title, String body)
    {
        Post post = new Post();

        post.title = title;
        post.body = body;

        return post;
    }

    public void updatePost(String title, String body)
    {
        this.title = title;
        this.body = body;

        this.updatedAt = LocalDateTime.now();
    }


}
