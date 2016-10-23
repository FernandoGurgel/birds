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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Celio Antony on 23/10/2016.
 */
public class CardAdapter extends BaseAdapter {
    private Context mContext;

    public CardAdapter(Context c, ArrayAdapter<Musics> musics) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_music, null);
        if (convertView == null) {
            // Get elements in card music
            ImageView imageView = (ImageView) view.findViewById(R.id.cover_music);
            TextView textView = (TextView) view.findViewById(R.id.title_music);


        } else {
            Log.d("BIRDS", "NADA ACONTECE CACHORREIRA.");
        }

        return view;

    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
            R.drawable.cover_brunomars_ex, R.drawable.cover_brunomars_ex,
    };


}
