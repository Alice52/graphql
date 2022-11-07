package io.github.alice52.graphql.fetcher;

import com.google.common.collect.Lists;
import com.netflix.graphql.dgs.*;
import graphql.schema.DataFetchingEnvironment;
import io.github.alice52.graphql.model.vo.BookingVo;
import io.github.alice52.graphql.model.vo.UserVo;
import io.github.alice52.graphql.service.BookingService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@DgsComponent
public class BookingFetcher {

    @Resource private BookingService bookingService;

    @DgsQuery
    public List<BookingVo> bookings(DataFetchingEnvironment dfe) {

        return bookingService.bookings();
    }

    @DgsMutation
    public BookingVo bookEvent(@InputArgument Integer eventId, @InputArgument Integer userId) {

        return bookingService.bookEvent(eventId, userId);
    }

    @DgsMutation
    public BookingVo cancelBooking(@InputArgument Integer bookingId) {

        return bookingService.cancelBooking(bookingId);
    }

    @DgsData(parentType = "UserVo", field = "bookings")
    public List<BookingVo> bookingsByUser(DgsDataFetchingEnvironment dfe) {
        UserVo user = dfe.getSource();
        if (Objects.isNull(user)) {
            return Lists.newArrayList();
        }

        return bookingService.listByUserId(user.getId());
    }
}
