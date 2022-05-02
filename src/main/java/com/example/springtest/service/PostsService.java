package com.example.springtest.service;
import com.example.springtest.domain.posts.Posts;
import com.example.springtest.domain.posts.PostsRepository;
import com.example.springtest.web.dto.PostsResponseDto;
import com.example.springtest.web.dto.PostsSaveRequestDto;
import com.example.springtest.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto((entity));
    }

//    @Transactional
//    public Long update(Long id, PostsUpdateRequestDto requestDto) {
//        Optional<Posts> optionalPosts = postsRepository.findById(id);
//        Posts posts = optionalPosts.get();
//        //posts.update(requestDto.getTitle());
//        //posts.updateTest(requestDto);
//        //entity.setTitle("123123123123");
//        //
//        return id;
//        //update post set title = "제목2" where id = id
//    }
//
//    public PostsResponseDto findById(Long id) {
//        PostsUpdateRequestDto dto = new PostsUpdateRequestDto();
//        Optional<Posts> optionalPosts = postsRepository.findById(id);
//        dto.setCount(optionalPosts.get().getCount() + 1);
//        optionalPosts.get().updateTest(dto);
//        return new PostsResponseDto(optionalPosts.get());
//    }
//
//    public Posts findById2(Long id) {
//        PostsUpdateRequestDto dto = new PostsUpdateRequestDto();
//        Optional<Posts> optionalPosts = postsRepository.findById(id);
//        dto.setCount(optionalPosts.get().getCount() + 1);
//        optionalPosts.get().updateTest(dto);
//        return optionalPosts.get();
//    }

}
