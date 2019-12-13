package com.bayreact.marvindcomunity

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.btn_register
import kotlinx.android.synthetic.main.layout_login.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private var sharedPrefManager: SharedPrefManager? = null
    private var openHelper: SQLiteOpenHelper? = null
    private var db: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPrefManager = SharedPrefManager(this)
        openHelper = DatabaseHelper(this)

        if (sharedPrefManager?.sPSudahLogin!!){
            startActivity(
                Intent(this@RegisterActivity,SuccessActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                Intent.FLAG_ACTIVITY_NEW_TASK)
            )
            super.onBackPressed()
        }

        btn_register.onClick {
            db = openHelper?.readableDatabase
            val email = edt_email_register.text.toString().trim()
            val password = edt_password_register.text.toString().trim()

            sharedPrefManager?.saveSPString(SharedPrefManager.COL_2, email)
            sharedPrefManager?.saveSPString(SharedPrefManager.COL_3,password)
            if (email.isEmpty() || password.isEmpty()){
            }else{
                dataInsert(email,password)
                toast("Register Success")
                startActivity(intentFor<LoginActivity>())
            }
            if (validation()){
                return@onClick
            }
        }
    }
    private fun dataInsert(email:String?, password:String?){
        val contentValues = ContentValues()
        contentValues.put(DatabaseHelper.COL_2,email)
        contentValues.put(DatabaseHelper.COL_3,password)
        db?.insert(DatabaseHelper.TABLE_NAME,null,contentValues)
    }
    private fun validation():Boolean{
        when{
                edt_email.text.toString().isBlank() ->{
                    edt_email_register.requestFocus()
                    edt_email_register.error = "Email cannot be empty"
                    return false
                }
                edt_password.text.toString().isBlank() ->{
                    edt_password_register.requestFocus()
                    edt_password_register.error = "The password must not be blank"
                    return false
                }
                else -> return false
            }
        }

    override fun onClick(v: View?) {
        when(v){
            btn_login_register ->{
                startActivity<LoginActivity>()
            }
        }
    }
}
