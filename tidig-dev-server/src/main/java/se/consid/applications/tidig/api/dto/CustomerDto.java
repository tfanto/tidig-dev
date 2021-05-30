package se.consid.applications.tidig.api.dto;

import java.util.Objects;

public class CustomerDto {
    Long customerId;
    String name;

    public CustomerDto() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, name);
    }
}
