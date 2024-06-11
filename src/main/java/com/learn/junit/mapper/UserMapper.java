package com.learn.junit.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.learn.junit.dto.UserDTO;
import com.learn.junit.entity.UserEntity;

@Mapper
public interface UserMapper {

	UserEntity toEntity(UserDTO userDTO);

	UserDTO toDTO(UserEntity userEntity);
	
	List<UserEntity> toEntityList(List<UserDTO> userDTOList);
	
	List<UserDTO> toDTOList(List<UserEntity> userEntityList);
	
}
