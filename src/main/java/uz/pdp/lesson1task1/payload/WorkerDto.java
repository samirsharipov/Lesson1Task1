package uz.pdp.lesson1task1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {

    @NotNull(message = "name should not be empty")
    private String name;

    @NotNull(message = "phoneNumber should not be empty")
    private String phoneNumber;

    @NotNull(message = "addressId should not be empty")
    private Integer addressId;

    @NotNull(message = "departmentId should not be empty")
    private Integer departmentId;
}
