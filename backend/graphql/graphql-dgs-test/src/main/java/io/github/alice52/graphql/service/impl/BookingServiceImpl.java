package io.github.alice52.graphql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.alice52.graphql.mapper.BookingEntityMapper;
import io.github.alice52.graphql.model.entity.BookingEntity;
import io.github.alice52.graphql.model.vo.BookingVo;
import io.github.alice52.graphql.service.BookingService;
import io.github.alice52.graphql.service.EventService;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl extends ServiceImpl<BookingEntityMapper, BookingEntity>
        implements BookingService {

    @Resource private EventService eventService;

    @Override
    public List<BookingVo> listByUserId(Integer userId) {

        return lambdaQuery().eq(BookingEntity::getUserId, userId).list().stream()
                .map(BookingVo::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingVo> bookings() {

        return lambdaQuery().list().stream().map(BookingVo::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookingVo bookEvent(Integer eventId, Integer userId) {

        val po =
                BookingEntity.builder()
                        .eventId(eventId)
                        .userId(userId)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();

        save(po);

        return new BookingVo(po);
    }

    @Override
    public BookingVo cancelBooking(Integer bookingId) {

        lambdaUpdate().eq(BookingEntity::getId, bookingId).remove();

        return null;
    }
}
