package br.com.george.menutest.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.george.menutest.Adapter.TagAdapter;
import br.com.george.menutest.Model.Tag;
import br.com.george.menutest.R;

public class ListTagsActivity extends AppCompatActivity {

    private ListView listTags;
    private TagAdapter adapter;
    private ArrayList tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tags);

        this.setTitle("Tags Encontradas");

        Tag tagTeste = new Tag("Encontrou tag");

        tags = new ArrayList<>();
        tags.add(tagTeste);

        listTags = (ListView) findViewById(R.id.list_tags);
        adapter = new TagAdapter(ListTagsActivity.this, tags);
        listTags.setAdapter(adapter);

        listTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tag tagAtual;

                switch (i) {
                    case 0:
                        tagAtual = (Tag) tags.get(i);
                        Toast.makeText(ListTagsActivity.this, tagAtual.getId(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
