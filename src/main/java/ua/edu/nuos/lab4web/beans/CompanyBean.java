package ua.edu.nuos.lab4web.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import ua.edu.nuos.lab4web.converters.CompanyConvertor;
import ua.edu.nuos.lab4web.dao.CompanyDao;
import ua.edu.nuos.lab4web.dto.CompanyDto;

import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class CompanyBean {
    private final Logger logger = Logger.getLogger(CompanyBean.class.getName());

    @Inject
    private CompanyDao companyDao;

    @Inject
    private CompanyConvertor convertor;

    @Getter
    @Setter
    private CompanyDto company = new CompanyDto();

    @Getter
    @Setter
    private CompanyDto updateCompany = new CompanyDto();

    public List<CompanyDto> getCompanies() {
        return companyDao.findAll()
                .stream()
                .map(convertor::convert)
                .toList();
    }

    public void createCompany() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        boolean valid = true;

        if (company.getName().isBlank() || company.getQuantityEmployees() <= 0) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please enter a name and quantity of employees"));
            valid = false;
        }
        if (company.getDescription().length() > 254) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Description too long"));
            valid = false;
        }
        if (!valid) return;
        companyDao.createCompany(convertor.convert(company));
        company = new CompanyDto();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Company created"));
    }

    public void deleteCompany(Integer id) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (id == null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid ID"));
            return;
        }
        companyDao.deleteById(id);
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Company deleted"));
    }

    public void setUpdateCompany(CompanyDto cd) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (cd == null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid company ID"));
            return;
        }
        updateCompany = cd;
    }

    public void updateCompany() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (updateCompany.getName().isBlank() || updateCompany.getQuantityEmployees() <= 0) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Name/Quantity of employees cannot be empty"));
            return;
        }
        companyDao.updateCompany(convertor.convert(updateCompany));
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Company updated"));
    }
}
