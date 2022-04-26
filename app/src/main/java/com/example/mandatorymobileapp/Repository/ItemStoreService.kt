package com.example.mandatorymobileapp.Repository

import com.example.mandatorymobileapp.model.Item
import retrofit2.Call
import retrofit2.http.*

interface  ItemStoreService {
    @GET("resaleitems")
    fun getAllItems(): Call<List<Item>>

    @GET("resaleitems/{id}")
    fun getItemById(@Path("resaleitemId") resaleitemId: Int): Call<Item>

    @POST("resaleitems")
    fun saveItem(@Body item: Item): Call<Item>

    @DELETE("resaleitems/{id}")
    fun deleteItem(@Path("id") id: Int): Call<Item>

    @PUT("resaleitems/{id}")
    fun updateItem(@Path("id") id: Int, @Body item: Item): Call<Item>
}