package com.example.lab2.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JsonData {
    @SerializedName("fromAddress")
    @Expose
    private var fromAddress: String? = null

    @SerializedName("toAddress")
    @Expose
    private var toAddress: String? = null

    @SerializedName("time")
    @Expose
    private var time: String? = null

    @SerializedName("carId")
    @Expose
    private var carId: Int? = null

    @SerializedName("price")
    @Expose
    private var price: Double? = null

    fun getFromAddress(): String? {
        return fromAddress
    }

    fun setFromAddress(fromAddress: String?) {
        this.fromAddress = fromAddress
    }

    fun getToAddress(): String? {
        return toAddress
    }

    fun setToAddress(toAddress: String?) {
        this.toAddress = toAddress
    }

    fun getTime(): String? {
        return time
    }

    fun setTime(time: String?) {
        this.time = time
    }

    fun getCarId(): Int? {
        return carId
    }

    fun setCarId(carId: Int?) {
        this.carId = carId
    }

    fun getPrice(): Double? {
        return price
    }

    fun setPrice(price: Double?) {
        this.price = price
    }
}