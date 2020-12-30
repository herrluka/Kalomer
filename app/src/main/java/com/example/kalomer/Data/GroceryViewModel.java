package com.example.kalomer.Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

public class GroceryViewModel extends AndroidViewModel {

        private KalomerDatabase database;
        private DatabaseDao dao;
        //private LiveData<List<Grocery>> list;
        private List<Grocery> list;

        public GroceryViewModel(@NonNull Application application) {
            super(application);
            database = KalomerDatabase.getDatabase(application);
            dao = database.daoEntity();
            //list = dao.getAllGroceryEntities();
        }

//        public LiveData<List<Grocery>> getAllEntites(){
//            return list;
//        }

    public List<Grocery> getAllEntites(){
            return list;
    }

        public void insert(Grocery entity){
            new insertAsyncTask(dao).execute(entity);
        }

        private class insertAsyncTask extends AsyncTask<Grocery,Void,Void> {

            private DatabaseDao daoEntity;

            public insertAsyncTask(DatabaseDao daoEntity) {
                this.daoEntity = daoEntity;
            }

            @Override
            protected Void doInBackground(Grocery... entity1s) {
                entity1s[0].setFavourite(false);
                daoEntity.insertGrocery(entity1s[0]);
                return null;
            }
        }
    }
