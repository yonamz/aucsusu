package com.hyeonhwa.blog.springboot.domain.user;

import com.hyeonhwa.blog.springboot.domain.posts.Posts;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUid(String uid);

    @Query("SELECT u FROM User u ORDER BY u.id DESC")
    List<User> findAllDesc();

    @Query("select u from User u where uid=:uid and password=:password")
    User findByPassword(String uid, String password);

    boolean existsByUid(String uid);

}
