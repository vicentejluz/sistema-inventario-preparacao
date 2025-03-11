package com.stefanini.sistemainventariopreparacao.domain;

import java.util.Objects;

public class Inventory {
    private final String serialNumber;
    private Long equipmentModelId;
    private Boolean inPreparationRoom;

    public Inventory(String serialNumber, Long equipmentModelId) {
        this.serialNumber = serialNumber;
        this.equipmentModelId = equipmentModelId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Long getEquipmentModelId() {
        return equipmentModelId;
    }

    public Boolean getInPreparationRoom() {
        return inPreparationRoom;
    }

    public void setEquipmentModelId(Long equipmentModelId) {
        this.equipmentModelId = equipmentModelId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(getSerialNumber(), inventory.getSerialNumber()) && Objects.equals(getEquipmentModelId(), inventory.getEquipmentModelId()) && Objects.equals(getInPreparationRoom(), inventory.getInPreparationRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSerialNumber(), getEquipmentModelId(), getInPreparationRoom());
    }
}
