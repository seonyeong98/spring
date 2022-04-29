package com.example.springtest.domain.posts;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
//기본 생성자 자동 추가
@Entity
//테이블과 링크될 클래스임을 나타냄
//기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭
public class Posts {
    //여기서 Posts클래스는 Entity클래스라고도 함

    @Id
    //해당 테이블의 PK빌드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //PK의 생성 규칙을 나타냄
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "Text", nullable = false)
    private String content;

    private String author;

    @Builder
    //해당 클래스의 빌더 패턴 클래스 생성
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
