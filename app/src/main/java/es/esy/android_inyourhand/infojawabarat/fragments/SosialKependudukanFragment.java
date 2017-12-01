package es.esy.android_inyourhand.infojawabarat.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.esy.android_inyourhand.infojawabarat.R;
import es.esy.android_inyourhand.infojawabarat.ui.InfoBPS;
import es.esy.android_inyourhand.infojawabarat.ui.KategoriActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SosialKependudukanFragment extends Fragment {

    private String[] sosialkependudukan = new String[]{
            "Gender", "Geografi", "Iklim", "Indeks Pembangunan Manusia", "Kemiskinan", "Kependudukan", "Kesehatan", "Pemerintahan", "Pendidikan", "Potensi Desa", "Tenaga Kerja"
    };

    private String[] sosialkependudukanID = new String[]{
            "40", "153", "151", "26", "23", "12", "30", "101", "28", "154", "6"
    };

    private InfoBPS infoBPS;
    private Toolbar toolbar;

    public SosialKependudukanFragment() {
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
        View view = inflater.inflate(R.layout.fragment_informasi_data, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        setupToolbar();

        ListView lvItem = view.findViewById(R.id.lv_kategori);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_item,
                android.R.id.text1, sosialkependudukan);

        lvItem.setAdapter(adapter);
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent;
                intent = new Intent(getActivity(), KategoriActivity.class);
                intent.putExtra("id", sosialkependudukanID[position]);
                intent.putExtra("kategori", sosialkependudukan[position]);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        infoBPS.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar(){
        toolbar.setTitle(getString(R.string.informasi_data));
        toolbar.setSubtitle(getString(R.string.sosial_dan_kependudukan));
        infoBPS.setSupportActionBar(toolbar);
    }

}
