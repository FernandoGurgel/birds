package birds.celioantony.br.birds;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by Celio Antony on 23/10/2016.
 */

public class Musics {
    private String title;
    private String cover;
    private String m_id;
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
