package br.com.george.menutest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.george.menutest.Model.Tag;
import br.com.george.menutest.R;

public class DatabaseAdapter extends ArrayAdapter {

    private Context context;
    private List<Tag> tags;

    public DatabaseAdapter(@NonNull Context c, @NonNull List<Tag> objects) {
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
            view = inflater.inflate(R.layout.item_database, parent, false);

            TextView codTag = (TextView) view.findViewById(R.id.codTagDatabase);
            TextView idTag = (TextView) view.findViewById(R.id.idTagDatabase);
            TextView descricaoTag = (TextView) view.findViewById(R.id.descricaoTagDatabase);

            codTag.setText(String.valueOf(tags.get(position).getCod()));
            idTag.setText(tags.get(position).getIdentificacao());
            descricaoTag.setText(tags.get(position).getDescricao());
        }

        return view;
    }
}