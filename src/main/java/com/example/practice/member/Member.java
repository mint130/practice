package com.example.practice.member;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String name;

    public void update(String email, String name) {
        if(email != null) this.email = email;
        if(name != null) this.name = name;
    }

    @Builder
    public Member(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
