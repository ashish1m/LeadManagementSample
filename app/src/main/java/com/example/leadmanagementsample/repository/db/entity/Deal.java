package com.example.leadmanagementsample.repository.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "deal_table")
public class Deal {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "dealName")
    private String dealName;

    @ColumnInfo(name = "status")
    private Status status;

    enum Status {
        UNAPPROVED,
        APPROVED,
        REJECTED;
    }

    public Deal(String dealName) {
        this.dealName = dealName;
        this.status = Status.UNAPPROVED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
