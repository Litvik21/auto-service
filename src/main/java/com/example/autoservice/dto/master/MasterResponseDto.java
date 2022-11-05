package com.example.autoservice.dto.master;

import java.util.List;
import lombok.Data;

@Data
public class MasterResponseDto {
    private Long id;
    private String name;
    private List<Long> finishedOrdersId;
}
