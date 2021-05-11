package com.hyeon.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Long> {            //jparepository<entity타입, pk타입>
}
