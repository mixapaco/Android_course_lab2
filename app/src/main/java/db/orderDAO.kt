package db
import androidx.room.*
@Dao
interface OrderDAO {

    @Query("SELECT * FROM `order`")
    fun getAll(): List<Order>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(order: Order)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(orders: List<Order>)

    @Update
    fun update(order: Order)

    @Delete
    fun delete(order: Order)
}