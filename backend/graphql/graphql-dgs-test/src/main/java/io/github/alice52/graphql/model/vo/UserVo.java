package io.github.alice52.graphql.model.vo;

import cn.hutool.core.bean.BeanUtil;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import io.github.alice52.graphql.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Integer id;
    private String email;
    private String password;

    /**
     * @see io.github.alice52.graphql.fetcher.EventFetcher#eventsByUser(DgsDataFetchingEnvironment)
     */
    private List<EventVo> events;

    /**
     * @see
     *     io.github.alice52.graphql.fetcher.BookingFetcher#bookingsByUser(DgsDataFetchingEnvironment)
     */
    private List<BookingVo> bookings;

    public UserVo(UserEntity po) {
        BeanUtil.copyProperties(po, this);
    }
}
