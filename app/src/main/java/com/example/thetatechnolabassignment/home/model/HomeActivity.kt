package com.example.thetatechnolabassignment.home.model

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.thetatechnolabassignment.R
import com.example.thetatechnolabassignment.home.db.UserDataBase
import com.example.thetatechnolabassignment.home.fragment.HomeFragment
import com.example.thetatechnolabassignment.home.fragment.MapFragment
import com.example.thetatechnolabassignment.home.fragment.ProfileFragment
import com.example.thetatechnolabassignment.home.viewModel.HomeRepository
import com.example.thetatechnolabassignment.home.viewModel.HomeViewModel
import com.example.thetatechnolabassignment.home.viewModel.HomeViewModelProvider

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val repository = HomeRepository(UserDataBase(this))
        val viewModelProviderFactory = HomeViewModelProvider(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(HomeViewModel::class.java)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<HomeFragment>(R.id.fragmentContainer)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.tabbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.idHome -> {
                addHomeFragment()

            }
            R.id.idProfile -> {
                addProfileFragment()

            }
            R.id.idMap -> {
                addMapFragment()
            }
        }
        return true
    }

    private fun addHomeFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<HomeFragment>(R.id.fragmentContainer)
        }
    }

    private fun addMapFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<MapFragment>(R.id.fragmentContainer)

        }
    }

    private fun addProfileFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ProfileFragment>(R.id.fragmentContainer)

        }
    }
}