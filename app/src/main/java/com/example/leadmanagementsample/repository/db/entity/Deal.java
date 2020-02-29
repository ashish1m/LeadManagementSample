package com.example.leadmanagementsample.repository.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.leadmanagementsample.repository.db.converters.StatusConverter;

@Entity(tableName = "deal_table")
public class Deal {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "dealName")
    private String dealName;

    @ColumnInfo(name = "status")
    @TypeConverters(StatusConverter.class)
    private Status status;

    @ColumnInfo(name = "time")
    private long time;

    public Deal(String dealName) {
        this.dealName = dealName;
        this.status = Status.UNAPPROVED;
        this.time = System.currentTimeMillis();
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public enum Status {
        UNAPPROVED(0),
        APPROVED(1),
        REJECTED(2);

        private int code;

        Status(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
