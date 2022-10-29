package io.github.alice52.graphql.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import io.github.alice52.graphql.model.dto.EventInputDto;
import io.github.alice52.graphql.model.entity.EventEntity;
import io.github.alice52.graphql.service.EventService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zack <br>
 * @create 2022-10-27 22:44 <br>
 * @project graphql <br>
 */
@DgsComponent
public class EventFetcher {

    @Resource private EventService eventService;

    @DgsQuery
    public List<EventEntity> events() {

        return eventService.events();
    }

    @DgsMutation
    public EventEntity createEvent(@InputArgument("eventInput") EventInputDto dto) {

        return eventService.createEvent(dto);
    }
}
