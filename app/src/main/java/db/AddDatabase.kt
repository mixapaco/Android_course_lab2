package db
import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = arrayOf(Order::class), version = 1)
abstract class AddDatabase: RoomDatabase() {
    abstract fun userDao(): OrderDAO
}