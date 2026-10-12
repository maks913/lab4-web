package ua.edu.nuos.lab4web.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "city", nullable = false)
    private String city;

    @NotNull
    @Column(name = "salary", precision = 10, scale = 2, nullable = false)
    private BigDecimal salary;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

}