package com.example.springtest.service;
import com.example.springtest.domain.posts.Posts;
import com.example.springtest.domain.posts.PostsRepository;
import com.example.springtest.web.dto.PostsListResponseDto;
import com.example.springtest.web.dto.PostsResponseDto;
import com.example.springtest.web.dto.PostsSaveRequestDto;
import com.example.springtest.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository; //dao

    @Transactional //CUD시 사용, begin, commit을 자동 수행, 예외 발생시 rollback 수행
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

    public PostsResponseDto findById (Long id) { //read작업이라 @Transaction 사용하지 않음
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) //transaction 범위는 유지하되 조회기능만 남겨두어 조회속도가 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) //.map(posts -> new PostsListResponseDto(posts)) 와 같음
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts); //엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수 있음, 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제
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