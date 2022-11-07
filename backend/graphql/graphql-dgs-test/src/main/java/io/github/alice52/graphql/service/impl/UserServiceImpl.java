package io.github.alice52.graphql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.alice52.graphql.mapper.UserEntityMapper;
import io.github.alice52.graphql.model.dto.UserDto;
import io.github.alice52.graphql.model.entity.UserEntity;
import io.github.alice52.graphql.model.vo.UserVo;
import io.github.alice52.graphql.service.UserService;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserEntityMapper, UserEntity>
        implements UserService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVo create(UserDto userDto) {

        // 1. check exist

        // 2. encode password: considering use BCryptPasswordEncoder

        // 3. save
        val po = new UserEntity(userDto);
        this.save(po);

        // 4. build respond
        return new UserVo(po);
    }

    @Override
    public List<UserVo> users() {

        return lambdaQuery().list().stream().map(UserVo::new).collect(Collectors.toList());
    }

    @Override
    public List<UserVo> getByIds(List<Integer> userIds) {

        return lambdaQuery().in(UserEntity::getId, userIds).list().stream()
                .map(UserVo::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserVo getById(Integer userId) {

        UserEntity one = lambdaQuery().eq(UserEntity::getId, userId).one();

        return new UserVo(one);
    }
}
