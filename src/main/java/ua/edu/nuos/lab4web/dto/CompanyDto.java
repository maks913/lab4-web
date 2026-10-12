package ua.edu.nuos.lab4web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ua.edu.nuos.lab4web.data.Company}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto implements Serializable {
    Integer id;
    @NotNull
    String name;
    @NotNull
    Integer quantityEmployees;
    LocalDateTime createdAt;
    String description;
}