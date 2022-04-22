package uz.pdp.lesson1task1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {

    @NotNull(message = "name should not be empty")
    private String name;

    @NotNull(message = "companyId should not be empty")
    private Integer companyId;
}
