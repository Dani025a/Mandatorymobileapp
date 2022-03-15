package com.example.mandatorymobileapp.Repository

import com.example.mandatorymobileapp.model.Item
import retrofit2.Call
import retrofit2.http.*

interface  ItemStoreService {
    @GET("items")
    fun getAllItems(): Call<List<Item>>

    @GET("items/{itemId}")
    fun getItemById(@Path("itemId") itemId: Int): Call<Item>

    @POST("items")
    fun saveItem(@Body item: Item): Call<Item>

    @DELETE("items/{id}")
    fun deleteItem(@Path("id") id: Int): Call<Item>

    @PUT("items/{id}")
    fun updateItem(@Path("id") id: Int, @Body item: Item): Call<Item>
}