package com.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.shop.details_item.BadgeDrawable;
import com.shop.favorite_activity.FavoriteActivity;
import com.shop.fragments.ALL;
import com.shop.fragments.Baby_Kids;
import com.shop.fragments.Home_Kitchen;
import com.shop.fragments.Men;
import com.shop.fragments.Sports_More;
import com.shop.fragments.Electronics;
import com.shop.fragments.Women;
import com.shop.profile.ProfileActivity;


public class NavigationDrawerMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private boolean doubleBackToExitPressedOnce = false;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private String[] colors;
    private Context context = this;
    private TextView txtName, txtWebsite, tv_notification,tv_favorite,tv_add_cart;
    private View navHeader;
    private Bundle bundle;
    private ImageButton btn_favorite_wish;

    private LayerDrawable icon;
    private MenuItem itemCart;


    private static final String SITE_URL = "http://karunkumar.in";
    private CustomTabsIntent customTabsIntent;
    private CustomTabsIntent.Builder intentBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manu);

        bundle = new Bundle();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        btn_favorite_wish = (ImageButton) findViewById(R.id.btn_favorite_wish);
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);

        tv_favorite=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().findItem(R.id.nav_favorite));
        tv_add_cart=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().findItem(R.id.nav_add_shopping_cart));
        tv_notification=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().findItem(R.id.nav_Notifications));

        initializeCountDrawer();

        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        txtWebsite.setText("karunkumar.in");
        txtName.setText("Karun Kumar");


        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //  bundle.putSerializable("ALL","airport");
        Fragment a1 = ALL.newInstance();
        // airport.setArguments(bundle);
        adapter.addFrag(a1, "ALL");

        Fragment a2 = Electronics.newInstance();
        adapter.addFrag(a2, "Electronics");

        Fragment a3 = Sports_More.newInstance();
        adapter.addFrag(a3, "Sports More");

        Fragment a4 = Men.newInstance();
        adapter.addFrag(a4, "Men");

        Fragment a5 = Women.newInstance();
        adapter.addFrag(a5, "Women");

        Fragment a6 = Baby_Kids.newInstance();
        adapter.addFrag(a6, "Baby Kids");

        Fragment a7 = Home_Kitchen.newInstance();
        adapter.addFrag(a7, "Home Kitchen");

        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        btn_favorite_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FavoriteActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }



    private void initializeCountDrawer(){


        tv_favorite.setGravity(Gravity.CENTER_VERTICAL);
        tv_favorite.setTypeface(null, Typeface.BOLD);
        tv_favorite.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        tv_favorite.setText("15");


        tv_add_cart.setGravity(Gravity.CENTER_VERTICAL);
        tv_add_cart.setTypeface(null, Typeface.BOLD);
        tv_add_cart.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        tv_add_cart.setText("4");


        tv_notification.setGravity(Gravity.CENTER_VERTICAL);
        tv_notification.setTypeface(null, Typeface.BOLD);
        tv_notification.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        tv_notification.setText("9");


    }



    /*********** tab menu start************************/

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }

/*********** tab menu end************************/


    /*********** menu option   right toolbar************************/

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/



    /*add_cart menu bar*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        itemCart = menu.findItem(R.id.action_cart);

        icon = (LayerDrawable) itemCart.getIcon();
        setBadgeCount(context, icon, "4");

        //   setBadgeCount(this, icon, "");

        return true;
    }

    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }


    /*********** side menu bar  ************************/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

           /* tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            colors = new String[]{"ALL", "TOP RATED", "TOP RATED", "TOP RATED"};

            adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFrag(new ALL(), "ALL");
            adapter.addFrag(new ATM(), "Tab2");
            adapter.addFrag(new Bank(), "Tab3");
            adapter.addFrag(new Bus_station(), "Tab4");
            viewPager.setAdapter(adapter);*/


        } else if (id == R.id.nav_mysite) {
            intentBuilder = new CustomTabsIntent.Builder();
            intentBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            intentBuilder.setExitAnimations(this, R.anim.right_to_left_end_mysite, R.anim.left_to_right_end_mysite);
            intentBuilder.setStartAnimations(this, R.anim.left_to_right_start_mysite, R.anim.right_to_left_start_mysite);
            intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            customTabsIntent = intentBuilder.build();
            customTabsIntent.launchUrl(this, Uri.parse(SITE_URL));

        } else if (id == R.id.nav_profile) {
            {
                startActivity(new Intent(context, ProfileActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        } else if (id == R.id.nav_favorite) {

            startActivity(new Intent(context, FavoriteActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        } else if (id == R.id.nav_add_shopping_cart) {
            Toast.makeText(this, "Coming soon!!!", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_Invite_friends) {
            Toast.makeText(this, "Coming soon!!!", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_Review_Rating) {
            Toast.makeText(this, "Coming soon!!!", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_Advance_Search) {
            Toast.makeText(this, "Coming soon!!!", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_Notifications) {
            Toast.makeText(this, "Coming soon!!!", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_Settings) {
            Toast.makeText(this, "Coming soon!!!", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_logout) {
            Toast.makeText(this, "Coming soon!!!", Toast.LENGTH_SHORT).show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*********** side bar close draws ************************/

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}