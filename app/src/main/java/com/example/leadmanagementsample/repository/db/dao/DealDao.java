package com.example.leadmanagementsample.repository.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.leadmanagementsample.repository.db.entity.Deal;

import java.util.List;

@Dao
public interface DealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Deal deal);

    @Query("SELECT * from deal_table WHERE id = :dealId")
    LiveData<Deal> getDeal(int dealId);

    @Query("DELETE FROM deal_table")
    void deleteAll();

    @Query("SELECT * from deal_table ORDER BY id DESC")
    LiveData<List<Deal>> getAllDeals();
}
