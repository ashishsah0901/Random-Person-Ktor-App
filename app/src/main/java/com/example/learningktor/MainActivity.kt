package com.example.learningktor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.learningktor.data.remote.PersonService
import com.example.learningktor.data.remote.dto.PersonRequest
import com.example.learningktor.data.remote.dto.PersonResponse
import com.example.learningktor.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var service: PersonService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.isVisible = View.VISIBLE
        if(service == null) {
            service = PersonService.create()
        }
        getPerson()
        binding.next.setOnClickListener {
            binding.isVisible = View.VISIBLE
            getPerson()
        }
        binding.post.setOnClickListener {
            binding.isVisible = View.VISIBLE
            val url = binding.imageUrl.text.toString()
            val name = binding.name.text.toString()
            if(name.isEmpty() || url.isEmpty() || !URLUtil.isValidUrl(url)) {
                Toast.makeText(this,"Please enter valid inputs",Toast.LENGTH_SHORT).show()
            } else {
                postData(name, url)
                binding.name.text = null
                binding.imageUrl.text = null
            }
        }
    }

    private fun postData(name: String, url: String) {
        lifecycleScope.launch {
            val request = PersonRequest(name, url)
            if(service == null) {
                service = PersonService.create()
            }
            val person = service!!.postRandomPerson(request)
            updateUI(person)
        }
    }

    private fun getPerson() {
        lifecycleScope.launch {
            if(service == null) {
                service = PersonService.create()
            }
            val person = service!!.getRandomPerson()
            updateUI(person)
        }
    }

    private fun updateUI(person: PersonResponse) {
        binding.personName = person.name
        Glide.with(this@MainActivity)
            .load(person.imageUrl)
            .into(binding.personImage)
        binding.isVisible = View.GONE
    }

}