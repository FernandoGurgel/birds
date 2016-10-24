package birds.celioantony.br.birds;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.glide.GlideRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Celio Antony on 23/10/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    // Context Activity
    private final Context context;
    // lis of musics
    private ArrayList<Musics> musics;
    // interface for simulate the click in the music
    private AdapterListener listener;

    public RecyclerAdapter(Context context, ArrayList<Musics> m) {
        this.context = context;
        this.musics = m;
    }

    //GET to our AdapterListener
    public AdapterListener getListener(){ return listener;}

    // SET para o nosso AdapterLister
    public void setListener(AdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create the layout in viewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_music, null);
        // Pass layout to inside ViewHolder
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get reference class
        Musics music = musics.get(position);

        // set the values the musics to layout inside holder
        holder
                .setTitle(music.getTitle())
                .setTime(music.getTime())
                .setCover(music.getCover());

    }

    @Override
    public int getItemCount() {
        // Size array books
        return musics.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView cover;
        private TextView title;
        private TextView time;


        public ViewHolder(View itemView) {
            super(itemView);

            // get reference layout
            cover = (ImageView) itemView.findViewById(R.id.cover_music);
            title = (TextView) itemView.findViewById(R.id.title_music);
            time = (TextView) itemView.findViewById(R.id.time_music);

            itemView.setOnClickListener(this);
        }

        public ViewHolder setTitle(String t) {
            if(title == null) return this;
            title.setText(t);
            return this;
        }

        public ViewHolder setTime(String t) {
            if(title == null) return this;
            time.setText(t);
            return this;
        }

        public ViewHolder setCover(String url) {
            if(cover == null) return this;
//            Ocean.glide(context)
//                 .load(url)
//                 .build(GlideRequest.BITMAP)
//                 .into(cover);
            Picasso.with(context).load(url).into(cover);

            return this;
        }

        public String getID(String url) {
            Pattern pattern = Pattern.compile("=([a-zA-Z]?[0-9]?)+");
            Matcher matcher = pattern.matcher(url);
            if(matcher.find()) {
                return matcher.group(0).replace("=","");
            }else {
                return url;
            }
        }

        @Override
        public void onClick(View v) {
            int position =  getAdapterPosition();
            Toast.makeText(context, "POSITION " + position, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, YoutubeActivity.class);
            intent.putExtra("title", musics.get(position).getTitle());
            intent.putExtra("cover", musics.get(position).getCover());
            intent.putExtra("mid", getID(musics.get(position).getM_id()));
            intent.putExtra("url", musics.get(position).getM_id());
            intent.putExtra("time", musics.get(position).getTime());
            context.startActivity(intent);
        }
    }

    /**
     * Referencias sobre interface
     * https://www.caelum.com.br/apostila-java-orientacao-objetos/interfaces/#10-5-exercicios-interfaces
     * http://www.devmedia.com.br/entendendo-interfaces-em-java/25502
     * Interface para externalizar o click em um item do layout
     */
    interface AdapterListener{
        void onItemClick(View view, int posicao);
    }
}
