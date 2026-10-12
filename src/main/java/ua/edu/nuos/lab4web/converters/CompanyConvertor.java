package ua.edu.nuos.lab4web.converters;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import ua.edu.nuos.lab4web.data.Company;
import ua.edu.nuos.lab4web.dto.CompanyDto;

@Named
@ApplicationScoped
public class CompanyConvertor {

    public Company convert(CompanyDto companyDto) {
        Company c = new Company();
        c.setId(companyDto.getId());
        c.setName(companyDto.getName());
        c.setCreatedAt(companyDto.getCreatedAt());
        c.setDescription(companyDto.getDescription());
        c.setQuantityEmployees(companyDto.getQuantityEmployees());
        return c;
    }

    public CompanyDto convert(Company company) {
        CompanyDto c = new CompanyDto();
        c.setId(company.getId());
        c.setName(company.getName());
        c.setCreatedAt(company.getCreatedAt());
        c.setDescription(company.getDescription());
        c.setQuantityEmployees(company.getQuantityEmployees());
        return c;
    }
}
