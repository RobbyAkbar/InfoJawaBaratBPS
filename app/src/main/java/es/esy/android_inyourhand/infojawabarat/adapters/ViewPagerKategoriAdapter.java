package es.esy.android_inyourhand.infojawabarat.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import es.esy.android_inyourhand.infojawabarat.viewpagers.KategoriFragment;

/**
 * Created by robby on 07/11/17.
 */

public class ViewPagerKategoriAdapter extends FragmentPagerAdapter {

    private String id;

    public ViewPagerKategoriAdapter(FragmentManager fm, String id) {
        super(fm);
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        KategoriFragment fragment = new KategoriFragment();
        Bundle args = new Bundle();
        switch(position){
            case 0:
                args.putString("menu", "3");
                args.putString("kategori", id);
                fragment.setArguments(args);
                return fragment;
            case 1:
                args.putString("menu", "1");
                args.putString("kategori", id);
                fragment.setArguments(args);
                return fragment;
            case 2:
                args.putString("menu", "2");
                args.putString("kategori", id);
                fragment.setArguments(args);
                return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Daftar Tabel";
            case 1:
                return "Konsep";
            case 2:
                return "Metodologi";
        }
        return null;
    }

}
