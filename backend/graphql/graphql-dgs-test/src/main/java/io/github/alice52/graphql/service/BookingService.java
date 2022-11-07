package io.github.alice52.graphql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.alice52.graphql.model.entity.BookingEntity;
import io.github.alice52.graphql.model.vo.BookingVo;

import java.util.List;

public interface BookingService extends IService<BookingEntity> {
    List<BookingVo> listByUserId(Integer userId);

    List<BookingVo> bookings();

    BookingVo bookEvent(Integer eventId, Integer userId);

    BookingVo cancelBooking(Integer bookingId);
}
