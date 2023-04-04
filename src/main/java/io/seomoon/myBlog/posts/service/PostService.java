package io.seomoon.myBlog.posts.service;


import io.seomoon.myBlog.posts.Entity.Post;
import io.seomoon.myBlog.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor    //필요로 하는(final 선언된) 변수들만을 가지고 생성자를 만듬. -> 생성자를 통한 DI
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(String title, String body, LocalDateTime createdAt, LocalDateTime updatedAt)
    {
        return postRepository.save(Post.createPost(title, body, createdAt, updatedAt)).getId();
    }

    //단순히 읽어오는 행위는 Transactional 어노테이션이 딱히 필요하지 않다.
    public Post getById(Long id)
    {
        Optional<Post> findPost = postRepository.findById(id);

        if(findPost.isPresent())
        {
            return findPost.get();
        }
        else
        {
            return null;
        }
    }

    @Transactional
    public void updateById(Long id, String title, String body)
    {
        Post findPost = getById(id);

        if(findPost == null)
        {
            return;
        }

        //EntityManager 가 영속성 컨텍스트를 관리해주기 떄문에 Setter 만 사용하고
        //별도로 delete, save 의 과정을 거치지 않아도 쿼리가 잘 수행된다.
        //스냅샷 -> 트랜잭션 수행 -> 더티체킹
        findPost.setTitle(title);
        findPost.setBody(body);
        findPost.setUpdatedAt(LocalDateTime.now());

    }

    @Transactional
    public void removeById(Long id)
    {
        Post findPost = getById(id);

        this.postRepository.delete(findPost);
        //OR
        //this.postRepository.deleteById(id);

    }

}
