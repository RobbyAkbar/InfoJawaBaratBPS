package es.esy.android_inyourhand.infojawabarat.fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import es.esy.android_inyourhand.infojawabarat.R;
import es.esy.android_inyourhand.infojawabarat.config.Config;
import es.esy.android_inyourhand.infojawabarat.models.progressDialogModel;
import es.esy.android_inyourhand.infojawabarat.ui.InfoBPS;
import im.delight.android.webview.AdvancedWebView;

import static es.esy.android_inyourhand.infojawabarat.adapters.DetectConnection.isNetworkStatusAvialable;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublikasiFragment extends Fragment implements AdvancedWebView.Listener{

    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchableSpinner spTahun;
    private String basedUrl;
    private AdvancedWebView webView;
    private InfoBPS infoBPS;
    private Toolbar toolbar;
    private String pTahun = "2017";

    public PublikasiFragment() {
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
        View view = inflater.inflate(R.layout.fragment_publikasi, container, false);

        spTahun = view.findViewById(R.id.spTahun);
        spTahun.setTitle("Tahun");
        spTahun.setPositiveButton("Oke");
        toolbar = view.findViewById(R.id.toolbar);
        setupToolbar();

        tahun();
        basedUrl = Config.SERVER_API+"search.php?tahun="+pTahun;

        webView = view.findViewById(R.id.webview);
        webView.setListener(getActivity(), this);
        webView.getSettings().setBuiltInZoomControls(true);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isNetworkStatusAvialable (getActivity().getApplicationContext())) {
                    webView.reload();
                    webView.getSettings().setDomStorageEnabled(true);
                    webView.loadUrl(basedUrl);
                    progressDialogModel.pdMenyiapkanDataLogin(getActivity());
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection!!", Toast.LENGTH_LONG).show();
                    webView.loadDataWithBaseURL(null, "<html><body><img width=\"100%\" height=\"100%\" src=\"file:///android_res/drawable/offline.png\"></body></html>", "text/html", "UTF-8", null);
                    progressDialogModel.hideProgressDialog();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        if(isNetworkStatusAvialable (getActivity().getApplicationContext())) {
            webView.getSettings().setDomStorageEnabled(true);
            webView.loadUrl(basedUrl);
            progressDialogModel.pdMenyiapkanDataLogin(getActivity());
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection!!", Toast.LENGTH_LONG).show();
            webView.loadDataWithBaseURL(null, "<html><body><img width=\"100%\" height=\"100%\" src=\"file:///android_res/drawable/offline.png\"></body></html>", "text/html", "UTF-8", null);
            progressDialogModel.hideProgressDialog();
            swipeRefreshLayout.setRefreshing(false);
        }

        return view;
    }

    private void tahun(){
        List<String> list = new ArrayList<>();
        final String[]tahun = getResources().getStringArray(R.array.tahun);
        final int length = tahun.length;
        for (int i=0;i<length;i++){
            list.add(tahun[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTahun.setAdapter(adapter);
        spTahun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pTahun = tahun[i];
                basedUrl = Config.SERVER_API+"search.php?tahun="+pTahun;
                webView.reload();
                webView.getSettings().setDomStorageEnabled(true);
                webView.loadUrl(basedUrl);
                progressDialogModel.pdMenyiapkanDataLogin(getActivity());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        infoBPS.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar(){
        toolbar.setTitle(getString(R.string.data_publikasi));
        toolbar.setSubtitle(getString(R.string.home_fragment_subtitle));
        infoBPS.setSupportActionBar(toolbar);
    }

    @SuppressLint("NewApi")
    @Override
    public void onPause() {
        webView.onPause();
        // ...
        super.onPause();
    }

    @Override
    public void onDestroy() {
        webView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        webView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {
        progressDialogModel.hideProgressDialog();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {
    }

}
