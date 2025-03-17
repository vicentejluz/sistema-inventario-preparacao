package com.stefanini.sistemainventariopreparacao.domain;

import java.util.Objects;

public class Inventory {
    private final Long id;
    private Long equipmentID;
    private final Boolean inPreparationRoom;

    public Inventory(Long id, Long equipmentID, Boolean inPreparationRoom) {
        this.id = id;
        this.equipmentID = equipmentID;
        this.inPreparationRoom = inPreparationRoom;
    }

    public Long getId() {
        return id;
    }

    public Long getEquipmentID() {
        return equipmentID;
    }

    public Boolean getInPreparationRoom() {
        return inPreparationRoom;
    }

    public void setEquipmentID(Long equipmentID) {
        this.equipmentID = equipmentID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(getId(), inventory.getId()) && Objects.equals(getEquipmentID(), inventory.getEquipmentID()) && Objects.equals(getInPreparationRoom(), inventory.getInPreparationRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEquipmentID(), getInPreparationRoom());
    }
}
