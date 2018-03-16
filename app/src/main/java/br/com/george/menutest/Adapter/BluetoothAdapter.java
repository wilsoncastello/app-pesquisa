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

import br.com.george.menutest.Model.DispositivoBluetooth;
import br.com.george.menutest.R;

public class BluetoothAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<DispositivoBluetooth> dispositivos;

    public BluetoothAdapter(@NonNull Context c, @NonNull ArrayList<DispositivoBluetooth> objects) {
        super(c, 0, objects);
        context = c;
        dispositivos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if (dispositivos != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_bluetooth, parent, false);

            TextView nomeDispositivo = (TextView) view.findViewById(R.id.nome_dispositivo);
            TextView descricaoDispositivo = (TextView) view.findViewById(R.id.endereco_dispositivo);

            DispositivoBluetooth dispositivo = dispositivos.get(position);

            nomeDispositivo.setText(dispositivo.getNome());
            descricaoDispositivo.setText(dispositivo.getEndereco());
        }

        return view;
    }

}
