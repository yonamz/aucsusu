package com.yonamz.aucsusu.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUid(String uid);

    boolean existsByUid(String uid);

    @Query("select u from User u where uid=:uid and password=:password")
    User findByPassword(String uid, String password);

    @Query("update User user set user.report = user.report+1 where user.uid = :uid")
    @Modifying
    @Transactional
    void userReport(String uid);

    @Query("select user.report from User user where user.uid = :uid")
    @Transactional
    int getUserReportNum(String uid);
}
