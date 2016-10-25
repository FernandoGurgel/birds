package birds.celioantony.br.birds;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    private String ID;
    private String title;
    private String url;
    private String cover;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            ID = null;
        } else {
            ID = extras.getString("mid");
            title = extras.getString("title");
            time = extras.getString("time");
            url = extras.getString("url");
            cover = extras.getString("cover");
        }

        youtubeView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youtubeView.initialize(API_KEY, this);

        descriptionMusic();
        downloadVideoYoutube();
    }

    public void descriptionMusic() {
        TextView textView = (TextView) findViewById(R.id.description_music);
        String descriptionn =
                "TITLE: " + title +
                "TIME: " + time +
                "URL: " + url +
                "ID: " + ID;

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
        final String urlDownload = "http://br.keepvid.com/?url="+url;
        btnDownload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlDownload));
                startActivity(browserIntent);
            }
        });
    }

}
