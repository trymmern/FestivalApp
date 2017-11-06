package com.example.trymtodalshaug.festivalapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.trymtodalshaug.festivalapp.adapter.TabsPagerAdapter;
import com.example.trymtodalshaug.festivalapp.fragments.ArtistsFragment;
import com.example.trymtodalshaug.festivalapp.fragments.DashboardFragment;
import com.example.trymtodalshaug.festivalapp.fragments.FavouritesFragment;
import com.example.trymtodalshaug.festivalapp.fragments.MapFragment;
import com.example.trymtodalshaug.festivalapp.fragments.PackingListFragment;
import com.example.trymtodalshaug.festivalapp.fragments.ScheduleFragment;
import com.example.trymtodalshaug.festivalapp.fragments.WalletFragment;

/***
 * Main activity of the application
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Defines the navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Sets the navigaion view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Loads the dashboard when the app opens
        loadFragment(DashboardFragment.newInstance());
    }

    // Replaces the content of the FrameLayout in content_main.xml
    public void loadFragment(Fragment selectedFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_content, selectedFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Defines which fragment is loaded into content_main.xml
    // depending on which menu button is pressed.
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            // Dashboard
            case R.id.nav_dashboard:
                selectedFragment = DashboardFragment.newInstance();
                break;

            case R.id.nav_schedule:
                selectedFragment = ScheduleFragment.newInstance();
                break;
            case R.id.nav_artists:
                selectedFragment = ArtistsFragment.newInstance();
                break;
            case R.id.nav_favourites:
                selectedFragment = FavouritesFragment.newInstance();
                break;

            // Packing list
            case R.id.nav_packing_list:
                selectedFragment = PackingListFragment.newInstance();
                break;
            // Wallet
            case R.id.nav_wallet:
                selectedFragment = WalletFragment.newInstance();
                break;
            // Map
            case R.id.nav_map:
                selectedFragment = MapFragment.newInstance();
                break;
        }

        if (selectedFragment != null) {
            loadFragment(selectedFragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
