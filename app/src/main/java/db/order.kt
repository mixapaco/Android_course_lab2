package db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class Order (
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "from_address") val fromAddress: String?,
    @ColumnInfo(name = "to_address") val toAddress: String?,
    @ColumnInfo(name = "time") val time: String?,
    @ColumnInfo(name = "car_id") val carId: Int,
    @ColumnInfo(name = "price") val price: Double
){
    override fun toString(): String =
            "User(uid=$uid, fromAddress='$fromAddress', toAddress='$toAddress', time='$time', carId='$carId', price='$price')"
}