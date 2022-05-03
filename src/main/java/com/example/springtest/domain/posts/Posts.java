package com.example.springtest.domain.posts;

import com.example.springtest.domain.BaseTimeEntity;
import com.example.springtest.web.dto.PostsUpdateRequestDto;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
//기본 생성자 자동 추가
@Entity
//테이블과 링크될 클래스임을 나타냄
//기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭
public class Posts extends BaseTimeEntity { //posts클래스가 BaseTimeEntity를 상속받도록 함
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

    //private Integer count;

    @Builder
    //해당 클래스의 빌더 패턴 클래스 생성
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //update posts set title = "제목2" where id = id
    public void update(String title, String content) {
        System.out.println(this.title);
        this.title = title;
    }

//    //update posts set count = count + 1 where id = id
//    public void updateCount() {
//        this.count = this.count + 1;
//    }
//
//    public void updateTest(PostsUpdateRequestDto dto) {
//        if (dto.getContent() != null) {
//            this.content = content;
//        }
//        if (dto.getTitle() != null) {
//            this.title = title;
//        }
//        if (dto.getAuthor() != null) {
//            this.author = author;
//        }
//        if (dto.getCount() != null) {
//            this.count = count;
//        }
//
//    }
}
