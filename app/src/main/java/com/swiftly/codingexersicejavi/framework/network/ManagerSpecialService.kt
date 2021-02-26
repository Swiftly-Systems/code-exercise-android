package com.swiftly.codingexersicejavi.framework.network

import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.swiftly.codingexersicejavi.utils.StringResponseParser
import com.swiftly.core.data.ManagerSpecial
import com.swiftly.core.data.ManagerSpecialList
import com.swiftly.core.data.Resource
import com.swiftly.core.repo.ManagerSpecialDataSource
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ManagerSpecialService (private val mContext: Context, private val db: ManagerSpecialDataSource){

    private val TAG: String = "ManagerSpecialService";

    companion object {
        @Volatile
        private var INSTANCE: ManagerSpecialService? = null

        @Synchronized
        fun getInstance(ctx: Context, db: ManagerSpecialDataSource): ManagerSpecialService
                = INSTANCE ?: ManagerSpecialService(ctx, db).also { INSTANCE = it }
    }

    suspend fun fetchAllSpecials(): Resource<ManagerSpecialList?>? {
        Log.d(TAG, "fetching specials..")
        var resource = fetchManagerSpecials(mContext)
        if (resource != null && resource is Resource.Success)
            db.setAll(resource!!.data)

        return resource
    }

    private suspend fun fetchManagerSpecials(context: Context): Resource<ManagerSpecialList?> {
        return suspendCoroutine<Resource<ManagerSpecialList?>> { cont ->

            val queue = Volley.newRequestQueue(context)
            val url = "https://raw.githubusercontent.com/Swiftly-Systems/code-exercise-android/master/backup"

            if (url.isNullOrBlank())
                return@suspendCoroutine

            val stringRequest: StringRequest = fetchManagerSpecialsHelper(url, cont)
            stringRequest.setShouldCache(false);
            queue.add(stringRequest)
        }
    }

    private fun fetchManagerSpecialsHelper(url: String, cont: Continuation<Resource<ManagerSpecialList?>>): StringRequest{
       return object : StringRequest(Method.GET, url,
            Response.Listener<String> { response ->

                var list: ManagerSpecialList? = null
                list = StringResponseParser.parseManagerSpecialResponse(response)
                if (list != null && list.managerSpecials != null)
                    Log.d(TAG,"parsed list size of.." + list!!.managerSpecials!!.size)

                var specials = Resource.Success<ManagerSpecialList?>(data = list)
                cont.resume(specials)
                Log.d(TAG, response)

            },
            Response.ErrorListener { it ->

                var specials = Resource.Error<ManagerSpecialList?>(message = "Network Error")
                cont.resume(specials)

            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String> {
                val params: MutableMap<String, String> = HashMap()
                return params
            }

            override fun getParams(): MutableMap<String, String> {
                val params: MutableMap<String, String> = HashMap()
                return params
            }
        }


    }

}