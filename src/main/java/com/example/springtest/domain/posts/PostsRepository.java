//DB Layer 접근자
package com.example.springtest.domain.posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Long>{
    //기본적인 CRUDb메소드 자동 생성
}
