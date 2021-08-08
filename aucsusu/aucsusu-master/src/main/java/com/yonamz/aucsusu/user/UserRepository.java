package com.yonamz.aucsusu.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(@Param("name") String name);

    User findByUid(@Param("uid")String uid);

    boolean existsByUid(@Param("uid")String uid);

    boolean existsByEmail(@Param("email") String email);


    @Query("select u from User u where uid=:uid and password=:password")
    User findByPassword(@Param("uid") String uid,@Param("password") String password);

    @Transactional
    @Modifying
    @Query("update User u set status=1 where uid=:uid")
    void enterUser(@Param("uid") String uid);

    @Transactional
    @Modifying
    @Query("update User u set status=0 where uid=:uid")
    void exitUser(@Param("uid") String uid);

    @Query("update User user set user.report = user.report+1 where user.uid = :uid")
    @Modifying
    @Transactional
    void userReport(@Param("uid")String uid);

    @Query("select user.report from User user where user.uid = :uid")
    @Transactional
    int getUserReportNum(@Param("uid") String uid);
}
