package ua.edu.nuos.lab4web.converters;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ua.edu.nuos.lab4web.data.Vacancy;
import ua.edu.nuos.lab4web.dto.VacancyDto;

@Named
@ApplicationScoped
public class VacancyConvertor {
    @Inject
    private CompanyConvertor companyConvertor;

    public Vacancy convert(VacancyDto vacancyDto) {
        Vacancy v = new Vacancy();

        v.setId(vacancyDto.getId());
        v.setCity(vacancyDto.getCity());
        v.setTitle(vacancyDto.getTitle());
        v.setSalary(vacancyDto.getSalary());
        v.setCompany(companyConvertor.convert(vacancyDto.getCompanyDto()));
        return v;
    }

    public VacancyDto convert(Vacancy vacancy) {
        VacancyDto dto = new VacancyDto();
        dto.setId(vacancy.getId());
        dto.setCity(vacancy.getCity());
        dto.setTitle(vacancy.getTitle());
        dto.setSalary(vacancy.getSalary());
        dto.setCompanyDto(companyConvertor.convert(vacancy.getCompany()));
        return dto;
    }
}
