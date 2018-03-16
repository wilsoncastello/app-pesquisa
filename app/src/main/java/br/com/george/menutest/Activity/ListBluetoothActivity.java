package br.com.george.menutest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.george.menutest.Adapter.BluetoothAdapter;
import br.com.george.menutest.Model.DispositivoBluetooth;
import br.com.george.menutest.R;

public class ListBluetoothActivity extends AppCompatActivity {

    private ListView listDispBluetooth;
    private Button btProcurarTags;
    private BluetoothAdapter adapter;
    private ArrayList dispositivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bluetooth);

        this.setTitle("Dispositvos Pareados");

        DispositivoBluetooth dispositivoTeste = new DispositivoBluetooth("George S7", "EE:EE:EE:EE:EE");

        dispositivos = new ArrayList<>();
        dispositivos.add(dispositivoTeste);

        listDispBluetooth = (ListView) findViewById(R.id.list_dispositivos_bluetooth);
        adapter = new BluetoothAdapter(ListBluetoothActivity.this, dispositivos);
        listDispBluetooth.setAdapter(adapter);

        listDispBluetooth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DispositivoBluetooth dispositivoAtual;

                switch (i) {
                    case 0:
                        dispositivoAtual = (DispositivoBluetooth) dispositivos.get(i);
                        Toast.makeText(ListBluetoothActivity.this, dispositivoAtual.getNome() + " - " + dispositivoAtual.getEndereco(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        btProcurarTags = (Button) findViewById(R.id.bt_procurar_tags);
        btProcurarTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListBluetoothActivity.this, ListTagsActivity.class);
                startActivity(intent);
            }
        });
    }
}
