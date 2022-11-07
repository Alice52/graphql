package io.github.alice52.graphql.fetcher;

import com.netflix.graphql.dgs.*;
import graphql.schema.DataFetchingEnvironment;
import io.github.alice52.graphql.fetcher.dataloader.UsersDataLoader;
import io.github.alice52.graphql.model.dto.UserDto;
import io.github.alice52.graphql.model.vo.BookingVo;
import io.github.alice52.graphql.model.vo.EventVo;
import io.github.alice52.graphql.model.vo.UserVo;
import io.github.alice52.graphql.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@DgsComponent
public class UserFetcher {

    @Resource private UserService userService;

    @DgsMutation
    public UserVo createUser(@InputArgument UserDto userDto) {

        return userService.create(userDto);
    }

    @DgsQuery
    public List<UserVo> users(DataFetchingEnvironment dfe) {
        log.debug("env: {}", dfe);
        return userService.users();
    }

    @DgsData(parentType = "BookingVo", field = "user")
    public UserVo userById(DataFetchingEnvironment dfe) {

        BookingVo booking = dfe.getSource();
        return userService.getById(booking.getUserId());
    }

    /**
     * @see #eventsByUserAsync
     * @param dfe
     * @return
     */
    @Deprecated
    // @DgsData(parentType = "EventVo", field = "user")
    public UserVo userByUserId(DataFetchingEnvironment dfe) {

        EventVo event = dfe.getSource();
        return userService.getById(event.getUserId());
    }

    @DgsData(parentType = "EventVo", field = "user")
    public CompletableFuture<UserVo> eventsByUserAsync(DgsDataFetchingEnvironment dfe) {
        EventVo event = dfe.getSource();
        log.info("Fetching creator wit id: {}", event.getUserId());
        DataLoader<Integer, UserVo> dataLoader = dfe.getDataLoader(UsersDataLoader.class);

        return dataLoader.load(event.getUserId());
    }
}
