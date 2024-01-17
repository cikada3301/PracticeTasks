package org.firsttask.model;

import org.firsttask.writer.annotations.CsvField;

public class BusinessObject {

    @CsvField(name = "id")
    private Long id;

    @CsvField(name = "name")
    private String name;

    @CsvField
    private String lastname;

    public BusinessObject() {

    }

    public BusinessObject(Long id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessObject that = (BusinessObject) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return lastname.equals(that.lastname);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + lastname.hashCode();
        return result;
    }
}
