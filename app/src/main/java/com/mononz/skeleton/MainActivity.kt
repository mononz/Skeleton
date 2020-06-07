package com.mononz.skeleton

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.mononz.skeleton.extensions.setupWithNavController
import com.mononz.skeleton.injection.FragmentInjectionFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject lateinit var fragmentFactory: FragmentInjectionFactory

    private var navController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onDestroy() {
        super.onDestroy()
        navController?.value?.removeOnDestinationChangedListener(listener)
    }

    private val listener = NavController.OnDestinationChangedListener { _, _, _ ->
        // listen to nav graph changes
    }

    private fun setupBottomNavigationBar() {

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)
        val navGraphIds = listOf(
            R.navigation.nav_home,
            R.navigation.nav_dashboard,
            R.navigation.nav_notifications
        )

        val fragmentManager = supportFragmentManager
        fragmentManager.fragmentFactory = fragmentFactory

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = fragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        controller.observe(this, Observer { nc ->
            navController?.value?.removeOnDestinationChangedListener(listener)
            nc.addOnDestinationChangedListener(listener)
        })
        navController = controller
    }
}