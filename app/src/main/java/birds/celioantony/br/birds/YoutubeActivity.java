package birds.celioantony.br.birds;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String API_KEY = "AIzaSyBzb3hAiKmH0wjid_L2a6M-wgNqR3Dh6b8";
    private YouTubePlayerView youtubeView;
    ProgressDialog progressDialog;

    private String ID;
    private String title;
    private String urly;
    private String cover;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processando");
//        settingsActivity();

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            ID = null;
        } else {
            ID = extras.getString("mid");
            title = extras.getString("title");
            time = extras.getString("time");
            urly = extras.getString("url");
            cover = extras.getString("cover");
        }

        youtubeView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youtubeView.initialize(API_KEY, this);

        descriptionMusic();
        downloadVideoYoutube();

    }

    public void descriptionMusic() {
        TextView titleView = (TextView) findViewById(R.id.title_music);
        TextView textView = (TextView) findViewById(R.id.description_music);
        String t = title+"\n";
        String descriptionn =
                "\nTIME: " + time +
                "\nURL: " + urly +
                "\nID: " + ID;

        titleView.setText(t);
        textView.setText(descriptionn);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Log.i("YOUTUBE", "RAIZ 1");
        if(!b) {
            Log.i("YOUTUBE", "RAIZ 2");
            youTubePlayer.cueVideo(ID);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "onInitializationFailure()", Toast.LENGTH_SHORT).show();
    }

    public void downloadVideoYoutube() {
        final Button btnDownload = (Button) findViewById(R.id.download_video);
//        final String urlDownload = "http://br.keepvid.com/?url="+url;
        final String urlDownload = urly;
        btnDownload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlDownload));
//                startActivity(browserIntent);
                loadLinksDownload(urlDownload);
                progressDialog.show();
            }
        });
    }

    public void loadLinksDownload(final String urlDownload){
        // teste webview
        final WebView webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
//        webview.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        webview.loadUrl("http://pt.savefrom.net");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("URL CLICK", "URL:"+url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url,
                                      Bitmap favicon) {
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                webview.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementById('dl').innerHTML+'</html>');");
                webview.loadUrl("javascript:var el = document.getElementById('sf_url').value='"+urlDownload+"'; document.getElementById('sf_submit').click();");
                progressDialog.dismiss();
                webview.setVisibility(View.VISIBLE);
                webview.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        webview.loadUrl("javascript: document.getElementsById('sf_result').innerHTML;");
                    }
                });

            }
        });

    }

    public void settingsActivity() {
        SetttingsApp settings = new SetttingsApp(YoutubeActivity.this);
        settings.setYoutube("Birds Musics");
    }
}
