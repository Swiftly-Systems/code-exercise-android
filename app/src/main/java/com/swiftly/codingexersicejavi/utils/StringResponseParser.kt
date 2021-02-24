package com.swiftly.codingexersicejavi.utils

import android.util.Log
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.swiftly.core.data.ManagerSpecial
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type


class StringResponseParser {
    companion object{
        fun parseManagerSpecialResponse(response: String?): List<ManagerSpecial>? {

            try {
                val obj: JSONObject = JSONObject(response)
                val array: JSONArray = obj.getJSONArray("managerSpecials")
                val collectionType: Type = object : TypeToken<List<ManagerSpecial?>?>() {}.type
                val gsonBuilder = GsonBuilder()
                val gson: Gson = gsonBuilder.create()
                return gson.fromJson(array.toString(), collectionType)
            }catch (e: Exception){
                e.printStackTrace()
                return null
            }
        }
    }
}