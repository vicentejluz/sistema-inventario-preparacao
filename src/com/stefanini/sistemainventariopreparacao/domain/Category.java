package com.stefanini.sistemainventariopreparacao.domain;

import java.util.Objects;

public class Category {
    private final Long id;
    private String name;
    private Boolean deleted;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(Long id, String name, boolean deleted) {
        this(id, name);
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(getId(), category.getId()) && Objects.equals(getName(), category.getName()) && Objects.equals(getDeleted(), category.getDeleted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDeleted());
    }
}
