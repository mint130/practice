package com.example.practice.todo;

import com.example.practice.member.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    private LocalDateTime deadline;

    @Column(nullable = false)
    private Boolean completed = false;

    @CreatedDate
    private LocalDateTime createdAt;

    public void toggle() {
        this.completed = !this.completed;
    }

    public void update(String title, String content, LocalDateTime deadline) {
        if(title != null) this.title = title;
        if(content != null) this.content = content;
        if(deadline != null) this.deadline = deadline;
    }

    @Builder
    public Todo(Member member, String title, String content, LocalDateTime deadline) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.deadline = deadline;
        this.completed = false;
    }
}
