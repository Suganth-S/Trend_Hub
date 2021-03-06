package com.suganth.trendhub.database;

import android.content.Context;

import com.suganth.trendhub.dao.RepoDao;
import com.suganth.trendhub.model.RepoModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * RepoDatabase is one which stores and manipulate data according to DAO.
 */

@Database(entities = {RepoModel.class}, version = 1, exportSchema = false)
public abstract class RepoDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "RepoDataBase";

    public abstract RepoDao repoDao();

    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile RepoDataBase INSTANCE;

    public static RepoDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RepoDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, RepoDataBase.class, DATABASE_NAME)
                            .addCallback(roomDataBaseCallBack)
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDataBaseCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                RepoDao repoDao = INSTANCE.repoDao();
                repoDao.deleteAll();
            });
        }
    };
}
