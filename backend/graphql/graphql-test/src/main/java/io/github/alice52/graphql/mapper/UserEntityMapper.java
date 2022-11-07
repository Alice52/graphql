package io.github.alice52.graphql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.alice52.graphql.model.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserEntityMapper extends BaseMapper<UserEntity> {}
