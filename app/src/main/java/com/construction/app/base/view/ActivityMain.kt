package com.construction.app.base.view

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import com.construction.app.R
import com.construction.app.base.utility.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class ActivityMain : BaseActivity() {
    override fun layoutResourceId() = R.layout.activity_main
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Main Screen"

    private var optionMenu: Menu? = null

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        this.optionMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navigation = navigationFragment as NavHostFragment
        return when (item.itemId) {
            R.id.optionAddItem -> {
                navigation.navController.navigate(R.id.fragmentItemAdd)
                true
            }
            R.id.optionAddUnit -> {
                navigation.navController.navigate(R.id.fragmentUnitAdd)
                true
            }
            R.id.optionLogout -> {
                signOut()
                this@ActivityMain.finish()
                true
            }
            android.R.id.home -> {
                hideKeyboard()
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun homeBackButton(visible: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(visible)
    }

    fun homeOptionMenu(visible: Boolean) {
        this.optionMenu?.let { menu ->
            if (visible) {
                menu.findItem(R.id.optionAddItem).isVisible = true
                menu.findItem(R.id.optionAddUnit).isVisible = true
                menu.findItem(R.id.optionLogout).isVisible = true
            } else {
                menu.findItem(R.id.optionAddItem).isVisible = false
                menu.findItem(R.id.optionAddUnit).isVisible = false
                menu.findItem(R.id.optionLogout).isVisible = false
            }
        }
    }

    /*override fun onBackPressed() {
        super.onBackPressed()
        // handel back press
    }*/
}
