package es.esy.android_inyourhand.infojawabarat.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import es.esy.android_inyourhand.infojawabarat.R;
import es.esy.android_inyourhand.infojawabarat.adapters.ImageAdapter;
import es.esy.android_inyourhand.infojawabarat.config.Config;
import es.esy.android_inyourhand.infojawabarat.models.ImageModel;
import es.esy.android_inyourhand.infojawabarat.ui.InfoBPS;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    String[] data = new String[]{
            Config.SERVER_API+"chart/chart.png",
            Config.SERVER_API+"chart/chart1.png",
            Config.SERVER_API+"chart/chart2.png",
            Config.SERVER_API+"chart/chart3.png",
            Config.SERVER_API+"chart/chart4.png",
            Config.SERVER_API+"chart/chart5.png",
            Config.SERVER_API+"chart/chart6.png",
            Config.SERVER_API+"chart/chart7.png",
            Config.SERVER_API+"chart/chart8.png",
            Config.SERVER_API+"chart/chart9.png"
    };

    private ListView lvItem;
    private ArrayList listItem;

    private InfoBPS infoBPS;
    private Toolbar toolbar;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        setupToolbar();

        lvItem = view.findViewById(R.id.lv_img);
        listItem = new ArrayList<>();

        ImageModel imageModel;

        for (int i = 0; i < data.length; i++){
            imageModel = new ImageModel();
            imageModel.setImage(data[i]);
            listItem.add(imageModel);
        }
        ImageAdapter adapter = new ImageAdapter(getActivity(), listItem);
        lvItem.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        infoBPS.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar(){
        toolbar.setTitle(getString(R.string.home_fragment_title));
        toolbar.setSubtitle(getString(R.string.home_fragment_subtitle));
        infoBPS.setSupportActionBar(toolbar);
    }

}
