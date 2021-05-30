package se.consid.applications.tidig.api.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class ReportedTimeResultDto
{
    Long timeRowId;
    LocalDateTime date;
    ArticleDto article;
    Double hours;
    CustomerDto customer;
    Long project;
    Long activity;
    Long caseNumber;
    String description;
    Boolean isSubmitted;
    String empId;


    public ReportedTimeResultDto() {
    }

    public Long getTimeRowId() {
        return timeRowId;
    }

    public void setTimeRowId(Long timeRowId) {
        this.timeRowId = timeRowId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ArticleDto getArticle() {
        return article;
    }

    public void setArticle(ArticleDto article) {
        this.article = article;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    public Long getActivity() {
        return activity;
    }

    public void setActivity(Long activity) {
        this.activity = activity;
    }

    public Long getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(Long caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(Boolean submitted) {
        isSubmitted = submitted;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportedTimeResultDto that = (ReportedTimeResultDto) o;
        return Objects.equals(timeRowId, that.timeRowId) && Objects.equals(date, that.date) && Objects.equals(article, that.article) && Objects.equals(hours, that.hours) && Objects.equals(customer, that.customer) && Objects.equals(project, that.project) && Objects.equals(activity, that.activity) && Objects.equals(caseNumber, that.caseNumber) && Objects.equals(description, that.description) && Objects.equals(isSubmitted, that.isSubmitted) && Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeRowId, date, article, hours, customer, project, activity, caseNumber, description, isSubmitted, empId);
    }
}
