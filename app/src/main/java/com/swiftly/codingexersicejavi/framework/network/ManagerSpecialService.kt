package com.swiftly.codingexersicejavi.framework.network

import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.swiftly.codingexersicejavi.utils.StringResponseParser
import com.swiftly.core.data.ManagerSpecial
import com.swiftly.core.repo.ManagerSpecialDataSource
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ManagerSpecialService(private val mContext: Context) : ManagerSpecialDataSource {

    private val TAG: String = "ManagerSpecialService";
//    private var uwJob = Job()
//    private val uwScope = CoroutineScope(Dispatchers.Main + uwJob)


    companion object {
        @Volatile
        private var INSTANCE: ManagerSpecialService? = null

        @Synchronized
        fun getInstance(ctx: Context): ManagerSpecialDataSource
                = INSTANCE ?: ManagerSpecialService(ctx).also { INSTANCE = it }
    }

    init {

    }

    override suspend fun getAll(): List<ManagerSpecial>? {

        Log.d(TAG, "fetching specials..")
        val url = "https://raw.githubusercontent.com/Swiftly-Systems/code-exercise-android/master/backup"

        return fetchManagerSpecials(mContext, url)?.data

    }

    private suspend fun fetchManagerSpecials(context: Context, url: String): ManagerSpecialResponse? {
        return suspendCoroutine<ManagerSpecialResponse?> { cont ->

            val queue = Volley.newRequestQueue(context)
            val urlparam = url

            if (urlparam.isNullOrBlank())
                return@suspendCoroutine

            val stringRequest: StringRequest = test(url, cont)

            queue.add(stringRequest)
        }
    }

    private fun test(url: String, cont: Continuation<ManagerSpecialResponse?>): StringRequest{
       return object : StringRequest(Method.GET, url,
            Response.Listener<String> { response ->

                var list: List<ManagerSpecial>? = null
                list = StringResponseParser.parseManagerSpecialResponse(response)
                if (list!=null)
                    Log.d(TAG,"parsed list size of.." + list.size)

                var specials = ManagerSpecialResponse(data = list)
                cont.resume(specials)
                Log.d(TAG, response)

            },
            Response.ErrorListener { it ->

                var specials = ManagerSpecialResponse(error = 1)
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