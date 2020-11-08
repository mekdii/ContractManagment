package com.gebeya.contractm.activities.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import com.gebeya.contractm.R
import kotlinx.android.synthetic.main.activity_form_contract.*
import kotlinx.android.synthetic.main.activity_form_contract.citiy
import kotlinx.android.synthetic.main.activity_form_contract.collector
import kotlinx.android.synthetic.main.activity_form_contract.country
import kotlinx.android.synthetic.main.activity_form_contract.laddress
import kotlinx.android.synthetic.main.activity_form_contract.late_charges
import kotlinx.android.synthetic.main.activity_form_contract.lemail
import kotlinx.android.synthetic.main.activity_form_contract.lfname
import kotlinx.android.synthetic.main.activity_form_contract.llast
import kotlinx.android.synthetic.main.activity_form_contract.lphone
import kotlinx.android.synthetic.main.activity_form_contract.postalcode
import kotlinx.android.synthetic.main.activity_form_contract.rental_amount
import kotlinx.android.synthetic.main.activity_form_contract.security_deposit
import kotlinx.android.synthetic.main.activity_form_contract.state
import kotlinx.android.synthetic.main.activity_form_contract.temail
import kotlinx.android.synthetic.main.activity_form_contract.tfirst
import kotlinx.android.synthetic.main.activity_form_contract.tlast
import kotlinx.android.synthetic.main.activity_form_contract.tphone
import kotlinx.android.synthetic.main.activity_show_form.*

class ShowForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_form)

        submit_fuck.visibility = GONE
        val id  = intent.getStringExtra("id")
        lfname.setText(intent.getStringExtra("lFname"))
        llast.setText(intent.getStringExtra("lLname"))
        lemail.setText(intent.getStringExtra("lemail"))
        lphone.setText(intent.getStringExtra("lphone"))
        laddress.setText(intent.getStringExtra("address"))
        tfirst.setText(intent.getStringExtra("tFname"))
        tlast.setText(intent.getStringExtra("tLname"))
        temail.setText(intent.getStringExtra("temail"))
        tphone.setText(intent.getStringExtra("tphone"))
        citiy.setText(intent.getStringExtra("city"))
        country.setText(intent.getStringExtra("country"))
        state.setText(intent.getStringExtra("state"))
        postalcode.setText(intent.getStringExtra("postal"))
        rental_amount.setText(intent.getStringExtra("rentamount"))
        security_deposit.setText(intent.getStringExtra("securitiyDeposit"))
        late_charges.setText(intent.getStringExtra("latecharge"))
        collector.setText(intent.getStringExtra("collector"))



    }
}