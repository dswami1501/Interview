package com.swami.swamimvc.dao;

import com.swami.swamimvc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUserNameIgnoreCase(String username);

}
