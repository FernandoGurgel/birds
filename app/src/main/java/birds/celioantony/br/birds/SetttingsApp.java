package birds.celioantony.br.birds;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeBaseActivity;

/**
 * Created by Celio Antony on 25/10/2016.
 */

public class SetttingsApp {

    Context context;
    AppCompatActivity activity;
    Activity activity_youtube;

    public SetttingsApp(AppCompatActivity activity){
        this.context = activity;
        this.activity = activity;
    }

    public SetttingsApp(YouTubeBaseActivity activity) {
        this.context = activity;
        this.activity_youtube = activity;
    }

    public void setTtitleAndIcon(String title) {
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("  " + title);
        actionBar.setLogo(R.drawable.icon_app);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    public void setYoutube(String title) {
        android.app.ActionBar actionBar = activity_youtube.getActionBar();
        actionBar.setTitle("  " + title);
        actionBar.setLogo(R.drawable.icon_app);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

}
