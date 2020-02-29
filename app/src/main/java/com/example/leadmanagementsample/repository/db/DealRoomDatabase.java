package com.example.leadmanagementsample.repository.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.leadmanagementsample.LeadManagement;
import com.example.leadmanagementsample.repository.db.dao.DealDao;
import com.example.leadmanagementsample.repository.db.entity.Deal;

@Database(entities = {Deal.class}, version = 1, exportSchema = false)
public abstract class DealRoomDatabase extends RoomDatabase {

    @VisibleForTesting
    public static final String DATABASE_NAME = "deal.db";
    private static volatile DealRoomDatabase INSTANCE;

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            LeadManagement.getInstance()
                    .getAppExecutor()
                    .diskIO()
                    .execute(new Runnable() {
                        @Override
                        public void run() {
                            // Populate the database in the background
                            // If you want to start with more words, just add them.
                            DealDao dao = INSTANCE.dealDao();
                            dao.deleteAll();

                            Deal deal = new Deal("Sample Deal");
                            dao.insert(deal);

                        }
                    });
        }
    };

    private final MediatorLiveData<Boolean> mIsDatabaseCreated = new MediatorLiveData<>();

    public static DealRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (DealRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context.getApplicationContext());
                    INSTANCE.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    private static DealRoomDatabase buildDatabase(Context applicationContext) {

        return Room.databaseBuilder(applicationContext, DealRoomDatabase.class, DATABASE_NAME)
                .addCallback(roomCallback)
                .build();
    }

    public abstract DealDao dealDao();

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

}
