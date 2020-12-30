package com.example.kalomer.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Grocery.class,Category.class,Meal.class,GroceryMeal.class,User.class},version = 1)
public abstract class KalomerDatabase extends RoomDatabase {

    public abstract DatabaseDao daoEntity();

    private static volatile KalomerDatabase entityDatabase;
    public static KalomerDatabase getDatabase(final Context context){
        if(entityDatabase == null){
            synchronized (KalomerDatabase.class){
                if(entityDatabase==null){
                    entityDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            KalomerDatabase.class,"kalomer_database")
                            .allowMainThreadQueries() //dodato
                            .build();
                }
            }
        }
        return entityDatabase;
    }
}
