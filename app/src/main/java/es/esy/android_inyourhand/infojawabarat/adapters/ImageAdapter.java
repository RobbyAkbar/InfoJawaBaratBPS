package es.esy.android_inyourhand.infojawabarat.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import es.esy.android_inyourhand.infojawabarat.R;
import es.esy.android_inyourhand.infojawabarat.models.ImageModel;

/**
 * Created by robby on 09/11/17.
 */

public class ImageAdapter extends BaseAdapter {

    // params
    ArrayList listItem;
    Activity activity;

    public ImageAdapter(Activity activity, ArrayList listItem){
        this.activity = activity;
        this.listItem = listItem;
    }

    //method ini akan menentukan seberapa banyak data yang akan ditampilkan didalam ListView
    //listItem.size() == jumlah data dalam object List yang ada
    @Override
    public int getCount() {
        return listItem.size();
    }

    //method ini untuk mengakses per-item objek dalam list
    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //method ini yang akan menampilkan baris per baris dari item yang ada di ListView
    //dengan menggunakan pattern ViewHolder untuk optimasi performa dari ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.image_list_item, null);
            holder.imgItem = view.findViewById(R.id.img_ls);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        ImageModel imageModel = (ImageModel)getItem(position);
        Picasso.with(activity).load(imageModel.getImage()).into(holder.imgItem);

        return view;
    }

    static class ViewHolder{
        ImageView imgItem;
    }
}
