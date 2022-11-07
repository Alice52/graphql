package io.github.alice52.graphql.model.vo;

import cn.hutool.core.bean.BeanUtil;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironment;
import io.github.alice52.graphql.model.entity.BookingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingVo {
    private Integer id;

    private Integer userId;
    /**
     * @see io.github.alice52.graphql.fetcher.UserFetcher#userById(DataFetchingEnvironment)
     */
    private UserVo user;

    private Integer eventId;
    /**
     * @see io.github.alice52.graphql.fetcher.EventFetcher#eventById(DgsDataFetchingEnvironment)
     */
    private EventVo event;

    private String updatedAt;
    private String createdAt;

    public BookingVo(BookingEntity entity) {
        BeanUtil.copyProperties(entity, this);
    }
}
