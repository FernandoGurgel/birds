package birds.celioantony.br.birds;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.glide.GlideRequest;

import java.util.ArrayList;

/**
 * Created by Celio Antony on 23/10/2016.
 */
public class CardAdapter extends BaseAdapter {
    private Context mContext;
    public ArrayList<Musics> musics;


    public CardAdapter(MainActivity c, ArrayList<Musics> m) {
        mContext = c;
        musics = m;
    }

    public int getCount() {
        return musics.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // references to our images
//    private Integer[] mThumbIds = {
//            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
//            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
//            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
//            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
//            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
//            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
//            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
//            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
//            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
//            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
//    };

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get componentes in layout per inflater
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_music, null);
        ImageView cover = (ImageView) view.findViewById(R.id.cover_music);
        TextView title = (TextView) view.findViewById(R.id.title_music);
        TextView time = (TextView) view.findViewById(R.id.time_music);


        if (convertView == null) {
//            setCover(cover, position);
            setTitle(title, position);
//            setTime(time, position);
        } else {
            Log.d("BIRDS", "NADA ACONTECE CACHORREIRA.");
        }

        return view;

    }

    public void setTitle(TextView title, int position) {
        title.setText(musics.get(position).getTitle());
        Log.i("SET TITLE", "" + musics.get(position).getTitle());
    }

    public void setTime(TextView time, int position) {
        time.setText(musics.get(position).getTime());
        Log.i("SET TIME", "" + musics.get(position).getTime());
    }

    public void setCover(ImageView cover, int position) {
        Ocean.glide(mContext)
             .load(musics.get(position).getCover())
             .build(GlideRequest.BITMAP)
             .into(cover);
    }

}
