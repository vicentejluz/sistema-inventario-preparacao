package com.stefanini.sistemainventariopreparacao.domain;

import java.util.Objects;

public class Equipment {
    private final Long id;
    private String serialNumber;
    private Long equipmentModelID;

    public Equipment(Long id, String serialNumber, Long equipmentModelID) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.equipmentModelID = equipmentModelID;
    }

    public Long getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Long getEquipmentModelID() {
        return equipmentModelID;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setEquipmentModelId(Long equipmentModelId) {
        this.equipmentModelID = equipmentModelId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(getId(), equipment.getId()) && Objects.equals(getSerialNumber(), equipment.getSerialNumber()) && Objects.equals(getEquipmentModelID(), equipment.getEquipmentModelID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSerialNumber(), getEquipmentModelID());
    }
}
