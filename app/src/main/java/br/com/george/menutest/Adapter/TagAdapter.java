package br.com.george.menutest.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import br.com.george.menutest.R;

public class TagAdapter extends BaseAdapter {
        Context ctx;
        List<String> listaTags;

    public TagAdapter(Context ctx, List<String> listaTags) {
        this.listaTags = listaTags;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return listaTags.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTags.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            final int auxPosition = position;

            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.item_etiqueta, null);

            String tag = listaTags.get(position);

            TextView idTag = (TextView) layout.findViewById(R.id.idTag);
            TextView longIdTag = (TextView) layout.findViewById(R.id.longIdTag);

            idTag.setText(tag);
            longIdTag.setText(tag);
            idTag.setVisibility(View.VISIBLE);
            longIdTag.setVisibility(View.VISIBLE);

            return layout;
        } catch (Exception ex) {
            Log.d("ERRO", ex.getMessage());
            return null;
        }
    }
}