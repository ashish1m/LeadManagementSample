package com.example.leadmanagementsample.repository.db.converters;

import androidx.room.TypeConverter;

import com.example.leadmanagementsample.repository.db.entity.Deal;

public class StatusConverter {

    @TypeConverter
    public static Deal.Status toStatus(int status) {
        if (status == Deal.Status.UNAPPROVED.getCode()) {
            return Deal.Status.UNAPPROVED;
        } else if (status == Deal.Status.APPROVED.getCode()) {
            return Deal.Status.APPROVED;
        } else if (status == Deal.Status.REJECTED.getCode()) {
            return Deal.Status.REJECTED;
        } else {
            throw new IllegalArgumentException("Could not recognize status");
        }
    }

    @TypeConverter
    public static Integer toInteger(Deal.Status status) {
        return status.getCode();
    }
}
