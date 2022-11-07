package io.github.alice52.graphql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.alice52.graphql.model.dto.EventDto;
import io.github.alice52.graphql.model.entity.EventEntity;
import io.github.alice52.graphql.model.vo.EventVo;

import java.util.List;

public interface EventService extends IService<EventEntity> {
    List<EventVo> events();

    EventVo createEvent(EventDto dto);

    List<EventVo> listByUserId(Integer userId);

    EventVo getById(Integer id);
}
