package com.example.calculators

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.calculators.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val leafFragment = LeafFragment()
    private val circleFragment = CircleFragment()
    private val ovalFragment = OvalFragment()
    private val rectangleFragment = RectangleFragment()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(leafFragment)
        bottomNavigation()

    }

    private fun bottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.oval -> replaceFragment(ovalFragment)
                R.id.circle -> replaceFragment(circleFragment)
                R.id.rectangle -> replaceFragment(rectangleFragment)
                R.id.leaf -> replaceFragment(leafFragment)
            }
            false
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.content, fragment).commit()
        }
    }


}