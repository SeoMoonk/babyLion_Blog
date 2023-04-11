package io.seomoon.myBlog.posts.controller;

import io.seomoon.myBlog.posts.Entity.Post;
import io.seomoon.myBlog.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {

    private final PostService postService;

    @PostMapping("/list")
    @ResponseBody
    public List<Post> getPostList() {
        List<Post> postList = postService.findAll();

        return postList;
    }

    //http://localhost:8083/

    @GetMapping("/write")
    public String getPostWriteView() {

        //thymeleaf 템플릿엔진의 prefix 값이 template 로 default 설정되어 있기 떄문에
        //자동으로 template 에서 html 파일을 가져올 수 있는 것이다.
        return "/posts/post_write";
    }

    @PostMapping("/write")
    public String writePost(@PathVariable(name="title") String title, @PathVariable(name="body") String body) {

        System.out.println("title = " + title);
        System.out.println("body = " + body);

        Long saveId = postService.save(title, body);

        System.out.println("saveId = " + saveId);

        // HTTP
        // Status Code
        // 2xx -> 성공했을 때, 문제 없을 때
        // 3xx -> 페이지가 이동되었을 때, 바뀌었을 때 (redirect 등 ... )
        // 4xx -> 요청 보낸 사람이 잘못 보냈을 경우
        // 5xx -> 서버에서 무언가 문제가 발생했을 경우


        return "redirect:/";
        // classpath:/te,plates/redirect:/    .html
    }

    // 1.URL 에서의 번호로서 포스트를 조회 -> PathVariable
    @GetMapping("/{id}")
    public String getPostById(@PathVariable(name="id") Long id, Model model) {

        Post findPost = postService.getById(id);

        model.addAttribute("post",findPost);    //Map<String, Object>

        return "/posts/post_detail";        //FIXME : 반환값 고치기
    }

}
