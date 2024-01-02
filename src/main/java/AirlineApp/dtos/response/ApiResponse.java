package AirlineApp.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiResponse <T>{
    private T data;
}
