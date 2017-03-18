package com.levymoreira.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AccountType entity.
 */
public class AccountTypeDTO implements Serializable {

    private Long id;

    private String description;

    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccountTypeDTO accountTypeDTO = (AccountTypeDTO) o;

        if ( ! Objects.equals(id, accountTypeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AccountTypeDTO{" +
            "id=" + id +
            ", description='" + description + "'" +
            ", code='" + code + "'" +
            '}';
    }
}
