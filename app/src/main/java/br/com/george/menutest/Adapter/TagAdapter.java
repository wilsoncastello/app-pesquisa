package br.com.george.menutest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.george.menutest.Model.Tag;
import br.com.george.menutest.R;

public class TagAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Tag> tags;

    public TagAdapter(@NonNull Context c, @NonNull ArrayList<Tag> objects) {
        super(c, 0, objects);
        context = c;
        tags = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if (tags != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_tag, parent, false);

            TextView idTag = (TextView) view.findViewById(R.id.id_tag);

            Tag tag = tags.get(position);

            idTag.setText(tag.getId());
        }

        return view;
    }


}
