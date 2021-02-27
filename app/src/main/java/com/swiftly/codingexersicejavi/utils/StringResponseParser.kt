package com.swiftly.codingexersicejavi.utils

import android.util.Log
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.swiftly.core.data.ManagerSpecial
import com.swiftly.core.data.ManagerSpecialList
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type


class StringResponseParser {
    companion object{
        fun parseManagerSpecialResponse(response: String?): ManagerSpecialList? {

            try {
                val obj: JSONObject = JSONObject(response)
                val collectionType: Type = object : TypeToken<ManagerSpecialList?>() {}.type
                val gsonBuilder = GsonBuilder()
                val gson: Gson = gsonBuilder.create()
                return gson.fromJson(obj.toString(), collectionType)
            }catch (e: Exception){
                e.printStackTrace()
                return null
            }
        }
    }
}