package com.example.springtest.web;

import com.example.springtest.domain.posts.Posts;
import com.example.springtest.service.PostsService;
import com.example.springtest.web.dto.PostsResponseDto;
import com.example.springtest.web.dto.PostsSaveRequestDto;
import com.example.springtest.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping ("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);

        }
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

//    @GetMapping("/api/v1/posts2/{id}")
//    public Posts findById2 (@PathVariable Long id) {
//        return postsService.findById2(id);
//    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

}
