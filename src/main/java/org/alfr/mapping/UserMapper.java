package org.alfr.mapping;

import org.alfr.dto.User;
import org.alfr.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<User> getUsersFrom(List<UserEntity> users);

    User getUserFrom(UserEntity user);
}
