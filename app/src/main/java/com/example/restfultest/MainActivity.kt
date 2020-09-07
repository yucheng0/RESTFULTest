package com.example.restfultest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.okhttp.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var phpsessid = ""
    var client = OkHttpClient()                            //要一個實例
    var JSON = MediaType.parse("application/json; charset=utf-8")
    var body = RequestBody.create(JSON, "{\"account\":\"w06\" ,\"pw\":\"w\" , \"timezone\":8 }")
    lateinit var request: Request
    lateinit var response: Response
    lateinit var bodyData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        keyProc()
    }
    //keyProc
    fun keyProc()
    {

        btnRest.setOnClickListener {
            post3rd_rest()
        }
    }
    //=====================================================================================
    fun post3rd_rest() {
        GlobalScope.launch(Dispatchers.Default) {
            delay(1000)            //不加delay 好像不行 , 網路備好時間(min=300ms)
            val url = "http://192.168.0.102/REST/GetMachineStatus.ashx"
            client = OkHttpClient()                            //要一個實例
            // 記憶雙引號前加\
            JSON = MediaType.parse("application/json; charset=utf-8")
            request = Request.Builder()                    //建立需求
                    .url(url)
                    .build()
            try {
                response = client.newCall(request).execute()            // 取得回應到response 來
                bodyData = response.body()!!.string()
                println(bodyData)
                runOnUiThread {
                    textView.text = "${bodyData}"
                }
            } catch (e: Exception) {
                println("Error!!!!")
            }

        }

    }
}