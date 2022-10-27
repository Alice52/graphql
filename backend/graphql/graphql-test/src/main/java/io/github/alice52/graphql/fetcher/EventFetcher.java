package io.github.alice52.graphql.fetcher;

import com.google.common.collect.Lists;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

/**
 * @author zack <br>
 * @create 2022-10-27 22:44 <br>
 * @project graphql <br>
 */
@DgsComponent
public class EventFetcher {

    @DgsQuery
    public List<String> events() {

        return Lists.newArrayList("Reading", "Cooking", "Watch");
    }

    @DgsMutation
    public String createEvent(@InputArgument String name) {

        return name + " Created";
    }
}
