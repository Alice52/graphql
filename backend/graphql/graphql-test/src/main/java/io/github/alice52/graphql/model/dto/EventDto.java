package io.github.alice52.graphql.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private String title;
    private String description;
    private Float price;
    private String date;
    private Integer userId;
}
