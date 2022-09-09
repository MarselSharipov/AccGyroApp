package ru.unlucky.accgyroapp.ui

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get
import ru.unlucky.accgyroapp.R


class MainActivity: MvpAppCompatActivity(), IMainActivity {

    private val presenter by moxyPresenter { get<MainPresenter>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}