package io.github.alice52.graphql.model.vo;

import cn.hutool.core.bean.BeanUtil;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import io.github.alice52.graphql.model.entity.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventVo {
    private String id;
    private String title;
    private String description;
    private Float price;
    private String date;

    private Integer userId;
    /**
     * @see
     *     io.github.alice52.graphql.fetcher.UserFetcher#eventsByUserAsync(DgsDataFetchingEnvironment)
     */
    private UserVo user;

    public EventVo(EventEntity entity) {
        BeanUtil.copyProperties(entity, this);
    }
}
