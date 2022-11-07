package io.github.alice52.graphql.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zack
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "booking")
public class BookingEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;
    private Integer eventId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
