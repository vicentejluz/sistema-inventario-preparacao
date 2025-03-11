package com.stefanini.sistemainventariopreparacao.domain;

import java.util.Objects;

public class Analyst {
    private final Long id;
    private String name;

    public Analyst(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Analyst analyst = (Analyst) o;
        return Objects.equals(getId(), analyst.getId()) && Objects.equals(getName(), analyst.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
