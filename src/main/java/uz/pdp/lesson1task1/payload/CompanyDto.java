package uz.pdp.lesson1task1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CompanyDto {

    @NotNull(message = "corpName should not be empty")
    private String corpName;

    @NotNull(message = "directorName should not be empty")
    private String directorName;

    @NotNull(message = "addressId should not be empty")
    private Integer addressId;
}
