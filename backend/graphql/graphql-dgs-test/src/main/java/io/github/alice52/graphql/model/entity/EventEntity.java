package io.github.alice52.graphql.model.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.alice52.graphql.model.dto.EventDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author zack
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "event")
public class EventEntity {
    @TableId(type = AUTO)
    private Integer id;

    private String title;
    private String description;
    private Float price;
    private LocalDateTime date;

    private Integer userId;

    public EventEntity(EventDto dto) {
        BeanUtil.copyProperties(dto, this);
    }
}
