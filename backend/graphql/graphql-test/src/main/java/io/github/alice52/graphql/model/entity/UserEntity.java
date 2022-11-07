package io.github.alice52.graphql.model.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.alice52.graphql.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zack
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class UserEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String email;
    private String password;

    public UserEntity(UserDto dto) {
        BeanUtil.copyProperties(dto, this);
    }
}
