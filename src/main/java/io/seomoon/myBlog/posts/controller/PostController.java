package io.seomoon.myBlog.posts.controller;

import io.seomoon.myBlog.posts.Entity.Post;
import io.seomoon.myBlog.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    //포스트 조회
    //http://localhost:8083/
    @GetMapping("/posts/get/{id}")
    public String getPost(@PathVariable(name = "id") Long id) {

        Post getById = postService.getById(id);

        return "얻어온 포스트 : " + getById.toString();
    }

    @GetMapping("/posts/write")
    public String getPostWriteView() {

        //thymeleaf 템플릿엔진의 prefix 값이 template 로 default 설정되어 있기 떄문에
        //자동으로 template 에서 html 파일을 가져올 수 있는 것이다.
        return "/posts/post_write";
    }

    @PostMapping("/posts/write")
    public String writePost(@PathVariable(name="title") String title, @PathVariable(name="body") String body) {

        return "제목 : " + title + "내용 : " + body ;
    }

}
