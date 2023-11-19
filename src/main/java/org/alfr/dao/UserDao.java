package org.alfr.dao;

import org.alfr.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<UserEntity, Integer> {
}
