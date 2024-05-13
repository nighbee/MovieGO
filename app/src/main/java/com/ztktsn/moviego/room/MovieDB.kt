package com.ztktsn.moviego.room
import androidx.room.RoomDatabase

abstract
class MovieDB: RoomDatabase() {
    abstract fun movieDao(): MovieEntityDao
    companion object {
        private var instance: MovieDB? = null
        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = Room.databaseBuilder(
//            context,
//            movieDb:: class,java,
//            dbName
//        )
    }
}