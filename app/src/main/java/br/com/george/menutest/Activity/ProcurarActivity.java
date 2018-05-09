package br.com.george.menutest.Activity;

import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.george.menutest.Adapter.TagAdapter;
import br.com.george.menutest.Database.Database;
import br.com.george.menutest.Model.ImagemBD;
import br.com.george.menutest.Model.Tag;
import br.com.george.menutest.R;
import br.com.george.menutest.RFID.DotR900.OnBtEventListener;
import br.com.george.menutest.RFID.DotR900.R900;

public class ProcurarActivity extends AppCompatActivity implements OnBtEventListener {
    private R900 leitor;
    public static final int MSG_ENABLE_LINK_CTRL = 10;
    public static final int MSG_DISABLE_LINK_CTRL = 11;
    public static final int MSG_SHOW_TOAST = 20;
    public static final int MSG_REFRESH_LIST_TAG = 22;
    public static final int MSG_BT_DATA_RECV = 10;
    private static final int[] TX_DUTY_OFF =
            {10, 40, 80, 100, 160, 180};

    private static final int[] TX_DUTY_ON =
            {190, 160, 70, 40, 20};

    private static final String[] TXT_DUTY =
            {"90%", "80%", "60%", "41%", "20%"};

    public static int Type;

    public static class SelectMask {
        public int Bank;
        public int Offset;
        public int Bits;
        public String Pattern;
        public String TagId;
    }

    public static EtiquetaActivity.SelectMask SelMask = new EtiquetaActivity.SelectMask();
    public static boolean UseMask = false;
    private ListView lstTagEncontrada;
    private ArrayAdapter mAdapterTag;
    private String tag;
    private List<Tag> tagsBanco;
    private List<ImagemBD> imagens;
    private List<String> tagLeitor;
    private List<String> tagsEncontradas;
    private Database database;
    //endregion

    //region HANDLER
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            switch (msg.what) {
                case MSG_BT_DATA_RECV:
                    onNotifyBtDataRecv();
                    break;
                case MSG_SHOW_TOAST:
                    Toast.makeText(ProcurarActivity.this, (String) msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case MSG_REFRESH_LIST_TAG:
                    try {
                        mAdapterTag.notifyDataSetChanged();

                        for (Tag tagBanco : tagsBanco) {
                            for (String tagAtual : tagLeitor) {
                                if (tagBanco.getIdentificacao().equals(tagAtual)) {
                                    if (!tagsEncontradas.contains(tagAtual)) {
                                        tagsEncontradas.add(tagAtual);
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                        Log.d("ERRO", ex.getMessage());
                    }

            }
        }
    };
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar);

        setTitle("Etiquetas Econtradas");

        database = new Database(ProcurarActivity.this);

        if (getIntent().hasExtra("addressDispositivo")) {
            leitor = new R900(ProcurarActivity.this, mHandler, this);

            String addressDispositivo = getIntent().getExtras().getString("addressDispositivo");
            leitor.conectar(addressDispositivo);

            tagLeitor = new ArrayList<>();
            tagLeitor = leitor.getListaTags();

            tagsBanco = new ArrayList<>();
            tagsBanco = database.buscarTodasTags();

            imagens = new ArrayList<>();
            imagens = database.buscarTodasImagens();

            tagsEncontradas = new ArrayList<>();

            lstTagEncontrada = (ListView) findViewById(R.id.lstApresentaTagEncontrada);

            mAdapterTag = new TagAdapter(ProcurarActivity.this, tagsEncontradas);
            lstTagEncontrada.setAdapter(mAdapterTag);

            lstTagEncontrada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    final Dialog dialog = new Dialog(ProcurarActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.menu_database);

                    Button btnExcluir = (Button) dialog.findViewById(R.id.btnExcluirDatabase);
                    Button btnImagem = (Button) dialog.findViewById(R.id.btnImagemDatabase);
                    Button btnAdcImagem = (Button) dialog.findViewById(R.id.btnAdicionarImagemDatabase);
                    Button btnCancelar = (Button) dialog.findViewById(R.id.btnCancelarDatabase);

                    btnExcluir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (ImagemBD img : imagens) {
                                if (tagsBanco.get(position).getCod() == img.getCodTag()) {
                                    String srcFileDelete = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOCUMENTS + "/ImagensBancoIFSC/" + img.getImagem().substring(100);
                                    File fdelete = new File(srcFileDelete);
                                    if (fdelete.exists()) {
                                        if (fdelete.delete()) {
                                            Log.i("DELETE", "deletou!!!");
                                        } else {
                                            Log.i("DELETE", "não deletou!!!");
                                        }
                                    }

                                    database.excluirImagem(img.getCod());
                                }
                            }

                            database.excluirTag(tagsBanco.get(position).getCod());
                            finish();
                        }
                    });

                    btnImagem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            List<Tag> listTags = database.buscarTodasTags();

                            for (Tag tagAtual : listTags) {
                                if (listTags.indexOf(tagAtual) == position) {

                                    List<ImagemBD> imagensBanco = new ArrayList<>();
                                    ArrayList<String> imagensBancoEnd = new ArrayList<>();

                                    ArrayList<String> datasBanco = new ArrayList<>();
                                    ArrayList<String> descricaoBanco = new ArrayList<>();

                                    imagensBanco = database.buscarTodasImagens();

                                    for (ImagemBD imagemBD : imagensBanco) {
                                        if (imagemBD.getCodTag() == tagAtual.getCod()) {
                                            imagensBancoEnd.add(imagemBD.getImagem());
                                            datasBanco.add(imagemBD.getData());
                                            descricaoBanco.add(imagemBD.getDescricao());
                                        }
                                    }

                                    Intent intent = new Intent(ProcurarActivity.this, ImagemEtiquetaActivity.class);
                                    intent.putExtra("image_etiqueta", imagensBancoEnd);
                                    intent.putExtra("data_etiqueta", datasBanco);
                                    intent.putExtra("descricao_etiqueta", descricaoBanco);

                                    startActivity(intent);
                                }
                            }
                            dialog.dismiss();
                        }
                    });

                    btnAdcImagem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            List<Tag> listTags = database.buscarTodasTags();

                            for (Tag tagAtual : listTags) {
                                if (listTags.indexOf(tagAtual) == position) {
                                    Intent intent = new Intent(ProcurarActivity.this, AdicionarFotoActivity.class);
                                    intent.putExtra("tagAtual", tagAtual.getIdentificacao());
                                    startActivity(intent);
                                }
                            }
                        }
                    });

                    btnCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            });
        }

        onNotifyBtDataRecv();
    }

    //region EVENTOS DA TELA
    @Override
    public void onNotifyBtDataRecv() {
        if (leitor == null)
            return;

        try {
            leitor.leitura();
        } catch (Exception ex) {
            Log.d("ERRO", ex.getMessage());
        }

    }

    @Override
    public void onBtConnected(BluetoothDevice device) {
        setEnabledLinkCtrl(true);

        showToastByOtherThread("Conectou: " + leitor.getDispositivo().getName(), Toast.LENGTH_SHORT);
        leitor.sendCmdOpenInterface1();

        leitor.sendSettingTxCycle(TX_DUTY_ON[0], TX_DUTY_OFF[0]);
    }
    //endregion

    //region MENSAGENS DO HANDLER

    private void setEnabledLinkCtrl(boolean bEnable) {
        if (bEnable)
            mHandler.sendEmptyMessageDelayed(MSG_ENABLE_LINK_CTRL, 50);
        else
            mHandler.sendEmptyMessageDelayed(MSG_DISABLE_LINK_CTRL, 50);
    }

    private void showToastByOtherThread(String msg, int time) {
        mHandler.removeMessages(MSG_SHOW_TOAST);
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SHOW_TOAST, time, 0, msg));
    }
    //endregion

    //region MÉTODOS DA TELA
    public void onDestroy() {
        super.onDestroy();

        leitor.finalize();
    }

    public static EtiquetaActivity.SelectMask getSelectMask() {
        EtiquetaActivity.SelectMask selMask = new EtiquetaActivity.SelectMask();

        if (Type == 0) {
            selMask.Bank = 1;//0;
            selMask.Offset = 16;//SelMask.Offset;

            final String pattern = selMask.Pattern = SelMask.TagId;
            if (pattern != null) {
                final int LEN = pattern.length();
                selMask.Bits = LEN * 4;
            } else {
                selMask.Bits = 0;
                selMask.Pattern = null;
            }
        } else {
            if (SelMask.Bank == 4/*0*/) {
                selMask.Bits = 0;
            } else {
                selMask.Bank = SelMask.Bank;
                selMask.Offset = SelMask.Offset;

                final String pattern = selMask.Pattern = SelMask.Pattern;
                if (pattern != null) {
                    final int LEN = pattern.length();
                    selMask.Bits = LEN * 4;
                } else {
                    selMask.Bits = 0;
                    selMask.Pattern = null;
                }
            }
        }
        return selMask;
    }
    //endregione

    //region Métodos não utilizados
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_listar_etiqueta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    //endregion
}
