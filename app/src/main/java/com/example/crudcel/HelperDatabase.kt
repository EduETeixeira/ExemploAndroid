package com.example.crudcel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [Celular::class], version = 1)
abstract class HelperDatabase : RoomDatabase() {

    abstract fun  celularDao(): CelularDAO

    companion object{
        private var INSTANCE: HelperDatabase? = null

        fun getDatabase(context: Context): HelperDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HelperDatabase::class.java,
                    "celular_db"
                )   .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}