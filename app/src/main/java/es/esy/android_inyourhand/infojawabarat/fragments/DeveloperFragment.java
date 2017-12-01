package es.esy.android_inyourhand.infojawabarat.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import es.esy.android_inyourhand.infojawabarat.R;
import es.esy.android_inyourhand.infojawabarat.ui.InfoBPS;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeveloperFragment extends Fragment {

    private InfoBPS infoBPS;
    private Toolbar toolbar;
    private ArrayList<DetailBaris> list;
    private DetailBaris detailBaris;
    private AdapterSaya adapterSaya;
    private ListView listView;
    private CoordinatorLayout coordinatorLayout;

    private static final String[] text = new String[]{
            "Info Jawa Barat V1.0 (Check Update)",
            "Created by Robby Akbar",
            " WhatsApp 0896-6654-9850",
            " www.facebook.com/robby.akbar.75",
            " www.android-inyourhand.esy.es",
            " www.catatankerenrobby.blogspot.com",
            " www.github.com/robbyakbar",
            "Copyright © 2017",
            "ICT Club SMKN 2 Cikarang Barat"
    };

    public DeveloperFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        infoBPS = (InfoBPS)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_developer, container, false);

        ((CollapsingToolbarLayout)view.findViewById(R.id.collapsing_toolbar)).setTitle(getString(R.string.tentang_fragment_title));

        toolbar = view.findViewById(R.id.toolbar);
        coordinatorLayout = view.findViewById(R.id.coordinator_layout);
        list = new ArrayList<>();
        adapterSaya = new AdapterSaya(getActivity().getApplicationContext(), list);
        listView = view.findViewById(R.id.list_view_about);
        listView.setAdapter(adapterSaya);
        ImageView imageView = view.findViewById(R.id.flexible_image);
        Picasso.with(getActivity()).load("http://catatan-robby.000webhostapp.com/header.png").into(imageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Drawable[]icon = new Drawable[9];
                icon[0] = getResources().getDrawable(R.mipmap.ic_launcher);
                icon[1] = null;
                icon[2] = getResources().getDrawable(R.drawable.wa);
                icon[3] = getResources().getDrawable(R.drawable.fb);
                icon[4] = getResources().getDrawable(R.drawable.www);
                icon[5] = getResources().getDrawable(R.drawable.blog);
                icon[6] = getResources().getDrawable(R.drawable.github);
                icon[7] = null;
                icon[8] = getResources().getDrawable(R.drawable.ict);
                list.clear();
                for (int i=0;i<text.length;i++){
                    detailBaris = new DetailBaris();
                    detailBaris.string = text[i];
                    detailBaris.drawable = icon[i];
                    list.add(detailBaris);
                }
                adapterSaya.notifyDataSetChanged();
                listView.setOnItemClickListener(detectClick());
            }
        }, 100);

        setHasOptionsMenu(true);

        setupToolbar();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        infoBPS.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar(){
        toolbar.setTitle("");
        infoBPS.setSupportActionBar(toolbar);
    }

    private AdapterView.OnItemClickListener detectClick(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (text[i].contains("Info")){
                    final String appPackageName = "es.esy.android_inyourhand.infojawabarat";
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)), "Buka dengan"));
                }
                if (text[i].contains("WhatsApp")){
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=+6289666549850&text=Hallo%20Robby%20Akbar")), "Buka dengan"));
                }
                if (text[i].contains(".com")){
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + text[i].trim())), "Buka dengan"));
                }
                if (text[i].contains(".esy.es")){
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + text[i].trim())), "Buka dengan"));
                }
            }
        };
    }

    private class DetailBaris{
        Drawable drawable;
        String string;
    }

    private class AdapterSaya extends BaseAdapter {
        List<DetailBaris> list = new ArrayList<>();
        LayoutInflater layoutInflater;
        AdapterSaya(Context context, ArrayList<DetailBaris>list){
            layoutInflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view==null){
                view = layoutInflater.inflate(R.layout.tentang_list, null);
                viewHolder = new ViewHolder();
                viewHolder.imageView = view.findViewById(R.id.about_row_ikon);
                viewHolder.textView = view.findViewById(R.id.about_row_text);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)view.getTag();
            }
            if (list.get(i)!=null){
                viewHolder.imageView.setImageDrawable(list.get(i).drawable);
                viewHolder.textView.setText(list.get(i).string);
            }
            return view;
        }

        class ViewHolder{
            ImageView imageView;
            TextView textView;
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_share, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Download aplikasi Info Jawa Barat BPS di :\nhttps://play.google.com/store/apps/details?id=es.esy.android_inyourhand.infojawabarat");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
