package com.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import com.shop.R;
import com.shop.details_item.Details_Product;
import com.shop.favorite_activity.FavoriteSharedPreference;
import com.shop.model.Model;


public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {


    private ArrayList<Model> list;
    private FavoriteSharedPreference sharedPreference;
    private Context context;

    public myAdapter(Context context, ArrayList<Model> list) {
        this.list = new ArrayList<Model>(list);
        this.context = context;
        this.sharedPreference = new FavoriteSharedPreference();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custome_adapter_medical, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {

        final Model position = list.get(i);


        Glide.with(context).load(position.getProduct_all_Image_url())
                .crossFade()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imageView);


        viewHolder.tv_name.setText(position.getProduct_all_name());
        viewHolder.tv_prices.setText("₹ "+position.getProduct_all_price());
        viewHolder.tv_discount.setText(position.getProduct_all_discount());
        viewHolder.tv_discount.setPaintFlags(viewHolder.tv_discount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);



        viewHolder.tv_name1=position.getProduct_all_name();
        viewHolder.tv_prices1=position.getProduct_all_price();
        viewHolder.tv_discount1=position.getProduct_all_discount();
        viewHolder.imageView1=position.getProduct_all_Image_url();


        if (checkFavoriteItem(position)) {
            viewHolder.btnFavourite.setImageResource(R.drawable.ic_favorite);
            viewHolder.btnFavourite.setTag("active");
        } else {
            viewHolder.btnFavourite.setImageResource(R.drawable.ic_favorite_outline);
            viewHolder.btnFavourite.setTag("deactive");
        }


        viewHolder.btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tag = viewHolder.btnFavourite.getTag().toString();
                if (tag.equalsIgnoreCase("deactive")) {
                    sharedPreference.addFavorite(context, position);
                    viewHolder.btnFavourite.setTag("active");
                    viewHolder.btnFavourite.setImageResource(R.drawable.ic_favorite);
                } else {
                    sharedPreference.removeFavorite(context, position);
                    viewHolder.btnFavourite.setTag("deactive");
                    viewHolder.btnFavourite.setImageResource(R.drawable.ic_favorite_outline);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView tv_name,tv_prices,tv_discount;
        String tv_name1,tv_prices1,tv_discount1,imageView1;
        ImageView btnFavourite;

        public ViewHolder(View rowView) {
            super(rowView);

            imageView = (ImageView) rowView.findViewById(R.id.img);
            tv_name=(TextView)rowView.findViewById(R.id.tv_name);
            tv_prices=(TextView)rowView.findViewById(R.id.tv_prices);
            tv_discount=(TextView)rowView.findViewById(R.id.tv_discount);
            btnFavourite = (ImageView)rowView.findViewById(R.id.favouritesToggle);



            itemView.setOnClickListener(this); // bind the listener

        }


        @Override
        public void onClick(View view) {
            AppCompatActivity activity=(AppCompatActivity)context;
            Context context = view.getContext();
            Intent intent = new Intent(context, Details_Product.class);
            intent.putExtra("tv_name",tv_name1);
            intent.putExtra("tv_prices",tv_prices1);
            intent.putExtra("tv_discount",tv_discount1);
            intent.putExtra("imageView",imageView1);
            context.startActivity(intent);
            //activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

    }


    public boolean checkFavoriteItem(Model lists) {
        boolean check = false;
        ArrayList<Model> favorites = sharedPreference.loadFavorites(context);
        if (favorites != null) {
            for (Model product : favorites) {
                if (product.equals(lists)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
}

