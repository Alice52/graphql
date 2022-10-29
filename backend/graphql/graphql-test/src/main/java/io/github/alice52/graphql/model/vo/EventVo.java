package io.github.alice52.graphql.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventVo {
    private String id;
    private String title;
    private String description;
    private Float price;
    private String date;
}
