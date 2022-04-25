package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //expande, aumenta, cria o layout
        setContentView(binding.root/*passando a raiz do novo layout*/)

        //Events onCreate
        supportActionBar?.hide()
        handleUserName()

        //setting the loop img to white
        MotivationConstants.FILTER.LOOP//
        handleFilter(binding.imgLoop.id)

        //Events
        binding.buttonPhrase.setOnClickListener(this)
        binding.imgSun.setOnClickListener(this)
        binding.imgHappy.setOnClickListener(this)
        binding.imgLoop.setOnClickListener(this)

    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.button_phrase) {
            Toast.makeText(this, "ia ia ou", Toast.LENGTH_SHORT).show()
        } else if (view?.id in listOf(R.id.img_happy, R.id.img_loop, R.id.img_sun)) {
            handleFilter(view?.id)
        }
    }

    private fun handleFilter(id:Int?) {
        binding.imgHappy.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imgLoop.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imgSun.setColorFilter(ContextCompat.getColor(this, R.color.black))
        when (id) {
            R.id.img_loop -> {
                binding.imgLoop.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            R.id.img_happy -> {
                binding.imgHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            else -> {
                binding.imgSun.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
        }
    }
}