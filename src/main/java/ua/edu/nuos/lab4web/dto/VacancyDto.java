package ua.edu.nuos.lab4web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.edu.nuos.lab4web.data.Company;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link ua.edu.nuos.lab4web.data.Vacancy}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto implements Serializable {
    Integer id;
    @NotBlank
    String title;
    @NotBlank
    String city;
    @NotNull
    BigDecimal salary;
    @NotNull
    CompanyDto companyDto;
}