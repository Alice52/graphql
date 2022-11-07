package io.github.alice52.graphql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.alice52.graphql.mapper.EventEntityMapper;
import io.github.alice52.graphql.model.dto.EventDto;
import io.github.alice52.graphql.model.entity.EventEntity;
import io.github.alice52.graphql.model.vo.EventVo;
import io.github.alice52.graphql.service.EventService;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl extends ServiceImpl<EventEntityMapper, EventEntity>
        implements EventService {

    @Override
    public List<EventVo> events() {
        return lambdaQuery().list().stream().map(EventVo::new).collect(Collectors.toList());
    }

    @Override
    public EventVo createEvent(EventDto dto) {

        val po = new EventEntity(dto);
        save(po);

        return new EventVo(po);
    }

    @Override
    public List<EventVo> listByUserId(Integer userId) {

        return lambdaQuery().eq(EventEntity::getUserId, userId).list().stream()
                .map(EventVo::new)
                .collect(Collectors.toList());
    }

    @Override
    public EventVo getById(Integer id) {

        EventEntity one = lambdaQuery().eq(EventEntity::getId, id).one();

        return new EventVo(one);
    }
}
