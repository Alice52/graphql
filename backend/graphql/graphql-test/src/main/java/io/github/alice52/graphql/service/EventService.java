package io.github.alice52.graphql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.alice52.graphql.model.dto.EventInputDto;
import io.github.alice52.graphql.model.entity.EventEntity;

import java.util.List;

public interface EventService extends IService<EventEntity> {
    List<EventEntity> events();

    EventEntity createEvent(EventInputDto dto);
}
