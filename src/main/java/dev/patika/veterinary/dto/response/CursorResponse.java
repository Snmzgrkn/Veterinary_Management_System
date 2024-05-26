package dev.patika.veterinary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursorResponse<T>{
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private List<T> items;

}
