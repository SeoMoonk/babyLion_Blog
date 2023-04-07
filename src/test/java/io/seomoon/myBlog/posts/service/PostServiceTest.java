package io.seomoon.myBlog.posts.service;

import io.seomoon.myBlog.posts.Entity.Post;
import io.seomoon.myBlog.posts.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    //1. 포스트 생성
    //2. 포스트 삭제
    //3. 포스트 목록
    //4. 포스트 검색
    //5. 포스트 수정

    @Test
    @DisplayName("1. 포스트 생성 테스트")
    public void postCreateTest() {

        //given -> 포스트 생성에 필요한 정보가 주어졌울 때
        //when -> postService.save()를 호출하면
        Long saveId = postService.save("1번_생성_테스트", "포스트가_생성_됩니까?");

        //then -> 정말로 생성이 되는가?
        Assertions.assertThat(saveId).isEqualTo(1L);
    }

    @Test
    @DisplayName("2. 포스트 삭제 테스트")
    public void removeTest() {

        //given
        Long saveId = postService.save("title2", "body2");

        //when
        postService.removeById(saveId);
        Post findPost = postService.getById(saveId);

        //then
        assertThat(findPost).isNull();

    }

    @Test
    @DisplayName("3. 포스트 목록 테스트")
    public void listTest() {

        //given
        for (int i = 0; i < 5; i++) {
            postService.save("title" + i, "body" + i);
        }

        //when
        List<Post> postList = postService.findAll();

        //Then
        assertThat(postList.size()).isEqualTo(5);

    }

    @Test
    @DisplayName("4. 포스트 검색 테스트")
    public void searchTest() {

        //given
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                postService.save("post" + i, "body" + i);
            } else {
                postService.save("title" + i, "body" + i);
            }
        }

        //when
        List<Post> post = postService.findAllByTitle("post", PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id")));

        //then
        assertThat(post.size()).isEqualTo(3);

        for (Post post1 : post) {
            System.out.println("post = " + post1);
        }
    }

    @Test
    @DisplayName("5. 게시물 수정 테스트")
    public void updateTest() {

        //given
        Long saveId = postService.save("title1", "body1");

        //when
        postService.updateById(saveId, "updated", "content");
        Post findById = postService.getById(saveId);

        //then
        assertThat(findById.getTitle()).isEqualTo("updated");
        //assertThat(findById.getCreatedAt()).isNotEqualTo(findById.getUpdatedAt());

    }

}