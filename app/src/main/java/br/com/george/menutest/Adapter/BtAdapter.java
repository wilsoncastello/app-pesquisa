package br.com.george.menutest.Adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.george.menutest.R;

public class BtAdapter extends BaseAdapter {
    private List<BluetoothDevice> dispostivos;
    private LayoutInflater inflater;
    private Context ctx;
    private ImageView imgBluetooth;

    public BtAdapter(Context ctx, List<BluetoothDevice> dispositivos) {
        this.dispostivos = dispositivos;
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return dispostivos.size();
    }

    @Override
    public Object getItem(int position) {
        return dispostivos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final BluetoothDevice dispositivo = (BluetoothDevice) getItem(position);

        View view = inflater.inflate(R.layout.item_bluetooth, null);

        TextView txtNomeDispositivo = (TextView) view.findViewById(R.id.nome_dispositivo);
        txtNomeDispositivo.setText(dispositivo.getName());

        TextView txtCodigoDispositivo = (TextView) view.findViewById(R.id.endereco_dispositivo);
        txtCodigoDispositivo.setText(dispositivo.getAddress());

        return view;
    }
}