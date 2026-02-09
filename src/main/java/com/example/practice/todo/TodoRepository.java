package com.example.practice.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {
    @Modifying(clearAutomatically = true)
    @Query("update Todo t " +
            "set t.completed = true " +
            "where t.deadline < :now " +
            "and t.completed = false")
    int completeExpireTodos(@Param("now")LocalDateTime now);
}
