package uz.pdp.lesson1task1.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class AddressDto {

    @NotNull(message = "street should not be empty")
    private String street;

    @NotNull(message = "homeNumber should not be empty")
    private String homeNumber;
}
