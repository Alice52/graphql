package io.github.alice52.graphql.fetcher;

import com.google.common.collect.Lists;
import com.netflix.graphql.dgs.*;
import io.github.alice52.graphql.model.dto.EventDto;
import io.github.alice52.graphql.model.vo.BookingVo;
import io.github.alice52.graphql.model.vo.EventVo;
import io.github.alice52.graphql.model.vo.UserVo;
import io.github.alice52.graphql.service.EventService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author zack <br>
 * @create 2022-10-27 22:44 <br>
 * @project graphql <br>
 */
@DgsComponent
public class EventFetcher {

    @Resource private EventService eventService;

    @DgsQuery
    public List<EventVo> events() {

        return eventService.events();
    }

    @DgsMutation
    public EventVo createEvent(@InputArgument("eventDto") EventDto dto) {

        return eventService.createEvent(dto);
    }

    @DgsData(parentType = "UserVo", field = "events")
    public List<EventVo> eventsByUser(DgsDataFetchingEnvironment dfe) {
        UserVo user = dfe.getSource();

        if (Objects.isNull(user)) {
            return Lists.newArrayList();
        }

        return eventService.listByUserId(user.getId());
    }

    @DgsData(parentType = "BookingVo", field = "event")
    public EventVo eventById(DgsDataFetchingEnvironment dfe) {
        BookingVo booking = dfe.getSource();

        if (Objects.isNull(booking)) {
            return null;
        }

        return eventService.getById(booking.getEventId());
    }
}
