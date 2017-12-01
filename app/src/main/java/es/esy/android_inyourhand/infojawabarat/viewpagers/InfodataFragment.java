package es.esy.android_inyourhand.infojawabarat.viewpagers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import es.esy.android_inyourhand.infojawabarat.R;
import es.esy.android_inyourhand.infojawabarat.config.Config;
import es.esy.android_inyourhand.infojawabarat.models.progressDialogModel;
import im.delight.android.webview.AdvancedWebView;

import static es.esy.android_inyourhand.infojawabarat.adapters.DetectConnection.isNetworkStatusAvialable;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfodataFragment extends Fragment implements AdvancedWebView.Listener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private String basedUrl;
    private AdvancedWebView webView;

    public InfodataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategori, container, false);

        String menu = getArguments().getString("menu");
        basedUrl = Config.SERVER_API+"curl.php?menu="+menu;

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

    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
        // ...
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
