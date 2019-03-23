package android.hackforgood.hackforgood.ui.navigation_drawer

import android.content.Intent
import android.hackforgood.hackforgood.R
import android.hackforgood.hackforgood.ui.public_ad.PublicAdActivity
import android.hackforgood.hackforgood.ui.search_travel.SearchTravelActivity
import android.hackforgood.hackforgood.ui.see_own_ads.SeeOwnAdsActivity
import android.hackforgood.hackforgood.ui.see_own_ads.SeeOwnAdsFragment
import android.hackforgood.hackforgood.ui.see_profile.SeeProfileActivity
import android.hackforgood.hackforgood.ui.see_requests.SeeRequestsActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_navigation_drawer.*
import kotlinx.android.synthetic.main.app_bar_navigation_drawer.*
import kotlinx.android.synthetic.main.nav_header_navigation_drawer.view.*

class NavigationDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)
        window.statusBarColor = resources.getColor(R.color.colorSecondary)

        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, SeeOwnAdsFragment())
                .commit()

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        toolbar.title = "HackForGood"

        nav_view.setNavigationItemSelectedListener(this)

        nav_view.getHeaderView(0).imageImageView.setOnClickListener {
            startActivity(Intent(this, SeeProfileActivity::class.java))
        }

        val presenter = NavigationDrawerPresenter(this)
        presenter.viewLoaded()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.navigation_drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search_travel -> {
                val intent = Intent(this, SearchTravelActivity::class.java)
                startActivity(intent)
            }
            R.id.public_ad -> {
                val intent = Intent(this, PublicAdActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_requests -> {
                val intent = Intent(this, SeeRequestsActivity::class.java)
                startActivity(intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun loadImageView(urlImage: String) {
        Picasso.get()
                .load(urlImage)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(nav_view.getHeaderView(0).imageImageView);
    }

    fun loadName(allname: String) {
        nav_view.getHeaderView(0).nameTextView.text = allname
    }
}
