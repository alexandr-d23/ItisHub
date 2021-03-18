package com.example.itishub.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.itishub.R
import com.example.itishub.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            bnvMenu.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.i_main -> {
                        true
                    }
                    R.id.i_subjects -> {
                        startFragment(SubjectsFragment.newInstance())
                        true
                    }
                    R.id.i_about -> {
                        startFragment(CreatorsFragment.newInstance())
                        true
                    }
                    else -> false
                }
            }
            bnvMenu.setOnNavigationItemReselectedListener {}
        }
    }

    private fun startFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fl_container, fragment).commit()
    }
}