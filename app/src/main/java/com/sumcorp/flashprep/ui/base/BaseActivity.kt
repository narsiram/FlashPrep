package com.sumcorp.flashprep.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.sumcorp.flashprep.R
import kotlinx.android.synthetic.main.toolbar.*

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle(getString(R.string.superheroes_villians))
    }

    fun initToolbar(title: String) {
        setSupportActionBar(toolbar)

        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24)

        tvTitle.text = title

        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun showError(lytContainer: View, message: String) {
        val snackbar = Snackbar.make(lytContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Dismiss", {
            snackbar.dismiss()
        }).setActionTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
            .show()
    }
}