package com.ztktsn.moviego.room
import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase

abstract
class movieDb: RoomDatabase() {
    abstract fun movieDao(): movieEntityDao
    companion object {
        private var instance: movieDb? = null
        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = Room.databaseBuilder(
//            context,
//            movieDb:: class,java,
//            dbName
//        )
    }
}