package io.github.alice52.graphql.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.alice52.graphql.mapper.EventEntityMapper;
import io.github.alice52.graphql.model.dto.EventInputDto;
import io.github.alice52.graphql.model.entity.EventEntity;
import io.github.alice52.graphql.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl extends ServiceImpl<EventEntityMapper, EventEntity>
        implements EventService {

    @Override
    public List<EventEntity> events() {
        return lambdaQuery().list();
    }

    @Override
    public EventEntity createEvent(EventInputDto dto) {

        EventEntity entity =
                EventEntity.builder()
                        .price(dto.getPrice())
                        .title(dto.getTitle())
                        .description(dto.getDescription())
                        .date(LocalDateTimeUtil.parse(dto.getDate()))
                        .build();

        save(entity);

        return entity;
    }
}
