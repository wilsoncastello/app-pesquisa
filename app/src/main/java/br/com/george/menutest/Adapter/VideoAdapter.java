package br.com.george.menutest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.george.menutest.Model.Video;
import br.com.george.menutest.R;

public class VideoAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Video> videos;

    public VideoAdapter(@NonNull Context c, @NonNull ArrayList<Video> objects) {
        super(c, 0, objects);
        context = c;
        videos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if (videos != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_videos, parent, false);

            ImageView imgVideo = (ImageView) view.findViewById(R.id.img_video);
            TextView descricaoVideo = (TextView) view.findViewById(R.id.descricao_video);

            Video video = videos.get(position);

            imgVideo.setImageResource(video.getResourceImg());
            descricaoVideo.setText(video.getDescricao());
        }

        return view;
    }
}
