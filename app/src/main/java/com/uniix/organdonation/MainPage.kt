package com.uniix.organdonation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.uniix.organdonation.databinding.ActivityMainPageBinding
import com.uniix.organdonation.ui.help.HelpInstructionFragment

class MainPage : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMainPage.toolbar)

        binding.appBarMainPage.fab.setOnClickListener { view ->
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content,HelpInstructionFragment()).addToBackStack(null).commit()
            Snackbar.make(view, "Help and Documentation", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }



        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main_page)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,R.id.nav_profile,R.id.nav_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //supportFragmentManager.beginTransaction().replace(R.id.main_page_host,HomeFragment()).addToBackStack(null).commit()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_page, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.rate_us -> {
                rateUs()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun rateUs() {
        Toast.makeText(this, "Rate The Application", Toast.LENGTH_LONG).show()
        /*val uri = Uri.parse("market://details?id=$packageName")
        val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
        try {
            startActivity(myAppLinkToMarket)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Impossible to find an application for the market", Toast.LENGTH_LONG).show()
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main_page)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun change(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.drawer_layout,fragment).addToBackStack(null).commit()
    }
}