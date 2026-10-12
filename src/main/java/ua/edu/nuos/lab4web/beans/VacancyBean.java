package ua.edu.nuos.lab4web.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import ua.edu.nuos.lab4web.converters.VacancyConvertor;
import ua.edu.nuos.lab4web.dao.VacancyDao;
import ua.edu.nuos.lab4web.dto.VacancyDto;

import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class VacancyBean {
    private static final Logger LOGGER = Logger.getLogger(VacancyBean.class.getName());

    @Inject
    private VacancyConvertor convertorVacancy;

    @Inject
    private VacancyDao vacancyDao;

    @Getter
    @Setter
    private VacancyDto vacancy = new VacancyDto();

    @Getter
    @Setter
    private VacancyDto updateVacancy = new VacancyDto();

    public List<VacancyDto> getVacancies() {
        return vacancyDao.findAll()
                .stream()
                .map(convertorVacancy::convert)
                .toList();
    }

    public void createVacancy() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        boolean valid = true;

        if (vacancy.getCity().isBlank() || vacancy.getTitle().isBlank()) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please enter a city/title"));
            valid = false;
        }
        if (vacancy.getCompanyDto() == null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please enter a company"));
            valid = false;
        }
        if (!valid) return;
        vacancyDao.createVacancy(convertorVacancy.convert(vacancy));
        vacancy = new VacancyDto();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Vacancy Created"));
    }

    public void deleteVacancy(Integer id) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (id == null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please choose company to delete"));
            return;
        }
        vacancyDao.deleteById(id);
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Vacancy Deleted"));
    }

    public void setUpdateVacancy(Integer id) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (updateVacancy == null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cannot set vacancy to update"));
            return;
        }
        updateVacancy = convertorVacancy.convert(vacancyDao.findById(id));
    }

    public void updateVacancy() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        boolean valid = true;

        if (updateVacancy.getCity().isEmpty() || updateVacancy.getTitle().isEmpty()) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please enter a city/title"));
            valid = false;
        }
        if (updateVacancy.getCompanyDto().getId() == null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please enter a company"));
            valid = false;
        }
        if (!valid) return;
        vacancyDao.updateVacancy(convertorVacancy.convert(updateVacancy));
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Vacancy Updated"));
    }
}
