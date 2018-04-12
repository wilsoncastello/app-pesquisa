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

import br.com.george.menutest.R;

public class EndImagensAdapter extends ArrayAdapter {

    private Context context;
    private List<String> enderecos;

    public EndImagensAdapter(@NonNull Context c, @NonNull List<String> objects) {
        super(c, 0, objects);
        context = c;
        enderecos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if (enderecos != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_end_imagens, parent, false);

            TextView enderecoText = (TextView) view.findViewById(R.id.endImagem);

            String endereco = enderecos.get(position).substring(100);

            enderecoText.setText(endereco);
        }

        return view;
    }
}