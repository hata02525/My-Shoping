package com.shop.details_item;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.readystatesoftware.viewbadger.BadgeView;

import com.shop.R;
import com.shop.favorite_activity.FavoriteActivity;

public class Details_Product extends AppCompatActivity {

    private ImageView imageView,Image_addcart;
    private TextView tv_name,tv_prices,tv_discount,tv_addcart;
    private String tv_name1,tv_prices1,tv_discount1,imageView1;
    private Bundle bundle;
    private int clickcount=0;
    private StringBuilder builder;
    private LayerDrawable icon;
    private MenuItem itemCart;
    private Context context=this;
    private ImageButton btn_favorite_wish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__product);

        setToolbar();
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        imageView = (ImageView) findViewById(R.id.img);
        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_prices=(TextView)findViewById(R.id.tv_prices);
        tv_discount=(TextView)findViewById(R.id.tv_discount);
        tv_addcart=(TextView)findViewById(R.id.tv_addcart);
        btn_favorite_wish = (ImageButton) findViewById(R.id.btn_favorite_wish);



        bundle = getIntent().getExtras();
        if (bundle != null) {
            tv_name1 = bundle.getString("tv_name");
            tv_prices1 = bundle.getString("tv_prices");
            tv_discount1 = bundle.getString("tv_discount");
            imageView1 = bundle.getString("imageView");

        }



        tv_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickcount++;
                icon = (LayerDrawable) itemCart.getIcon();
                setBadgeCount(Details_Product.this, icon, String.valueOf(clickcount));

            }
        });



        btn_favorite_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,FavoriteActivity.class));
            }
        });



        tv_name.setText(tv_name1);
        tv_prices.setText("₹ "+tv_prices1);
        tv_discount.setText((tv_discount1));
        tv_discount.setPaintFlags(tv_discount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        Glide.with(this).load(imageView1)
                .crossFade()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        itemCart = menu.findItem(R.id.action_cart);

        icon = (LayerDrawable) itemCart.getIcon();
        setBadgeCount(Details_Product.this, icon, String.valueOf(clickcount));

         //   setBadgeCount(this, icon, "");

        return true;
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
