package io.github.alice52.graphql.fetcher.dataloader;

import com.netflix.graphql.dgs.DgsDataLoader;
import io.github.alice52.graphql.model.vo.UserVo;
import io.github.alice52.graphql.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.BatchLoader;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Slf4j
@DgsDataLoader(name = "users")
public class UsersDataLoader implements BatchLoader<Integer, UserVo> {

    @Resource private UserService userService;

    @Override
    public CompletionStage<List<UserVo>> load(List<Integer> userIds) {
        return CompletableFuture.supplyAsync(() -> userService.getByIds(userIds));
    }
}
