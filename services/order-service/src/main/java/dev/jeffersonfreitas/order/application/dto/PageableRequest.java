package dev.jeffersonfreitas.order.application.dto;


import java.util.Collections;
import java.util.List;

public record PageableRequest(
        int page,
        int size,
        List<SortOrder> sort
) {

    public static PageableRequest create(int page, int size, List<SortOrder> sort){
        return new PageableRequest(page, size, sort);
    }

    public static PageableRequest create(int page, int size){
        return new PageableRequest(page, size, Collections.emptyList());
    }
}
