package com.yonamz.aucsusu.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUid(String uid);

    boolean existsByUid(String uid);

    @Query("select u from User u where uid=:uid and password=:password")
    User findByPassword(String uid, String password);
}
