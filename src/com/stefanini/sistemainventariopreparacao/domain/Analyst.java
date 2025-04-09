package com.stefanini.sistemainventariopreparacao.domain;

import java.util.Objects;

public class Analyst {
    private Long id;
    private String name;
    private Boolean active;

    public Analyst(String name) {
        this.name = name;
    }

    public Analyst(Long id, String name) {
        this(name);
        this.id = id;
    }

    public Analyst(Long id, String name, Boolean active) {
        this(id, name);
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Analyst analyst = (Analyst) o;
        return Objects.equals(getId(), analyst.getId()) && Objects.equals(getName(), analyst.getName()) && Objects.equals(getActive(), analyst.getActive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getActive());
    }
}
