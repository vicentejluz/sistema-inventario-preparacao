package com.stefanini.sistemainventariopreparacao.domain;

import java.util.Objects;

public class EquipmentModel {
    private final Long id;
    private String name;
    private Long categoryId;

    public EquipmentModel(Long id, String name, Long categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentModel equipmentModel = (EquipmentModel) o;
        return Objects.equals(getId(), equipmentModel.getId()) && Objects.equals(getName(), equipmentModel.getName()) && Objects.equals(getCategoryId(), equipmentModel.getCategoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCategoryId());
    }
}
