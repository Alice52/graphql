package io.github.alice52.graphql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.alice52.graphql.model.dto.UserDto;
import io.github.alice52.graphql.model.entity.UserEntity;
import io.github.alice52.graphql.model.vo.UserVo;

import java.util.List;

public interface UserService extends IService<UserEntity> {
    UserVo create(UserDto userDto);

    List<UserVo> users();

    List<UserVo> getByIds(List<Integer> userIds);

    UserVo getById(Integer userId);
}
