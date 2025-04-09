package com.stefanini.sistemainventariopreparacao.domain;

import java.util.Objects;

public class AssetMovement {
    private Long id;
    private Long analystID;
    private String date;
    private String time;
    private String serialNumber;
    private Long equipmentModelID;
    private MovementType movementType;

    public AssetMovement(Long id, Long analystID,String serialNumber, Long equipmentModelID, MovementType movementType) {
        this.id = id;
        this.analystID = analystID;
        this.serialNumber = serialNumber;
        this.equipmentModelID = equipmentModelID;
        this.movementType = movementType;
    }

    public AssetMovement(Long id, Long analystID, String date, String time, String serialNumber, Long equipmentModelID, MovementType movementType) {
        this(id, analystID, serialNumber, equipmentModelID, movementType);
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public Long getAnalystID() {
        return analystID;
    }

    public void setAnalystID(Long analystID) {
        this.analystID = analystID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getEquipmentModelID() {
        return equipmentModelID;
    }

    public void setEquipmentModelID(Long equipmentModelID) {
        this.equipmentModelID = equipmentModelID;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AssetMovement that = (AssetMovement) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getAnalystID(), that.getAnalystID()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getSerialNumber(), that.getSerialNumber()) && Objects.equals(getEquipmentModelID(), that.getEquipmentModelID()) && getMovementType() == that.getMovementType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAnalystID(), getDate(), getTime(), getSerialNumber(), getEquipmentModelID(), getMovementType());
    }
}
