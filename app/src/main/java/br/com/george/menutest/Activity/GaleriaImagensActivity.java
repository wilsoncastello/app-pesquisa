package br.com.george.menutest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.george.menutest.Model.Image;
import br.com.george.menutest.R;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class GaleriaImagensActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private List<String> grupos;

    private SectionedRecyclerViewAdapter sectionAdapter;

    private ArrayList<Image> imagensCategoria1;
    private ArrayList<Image> imagensCategoria2;
    private ArrayList<Image> imagensCategoria3;
    private ArrayList<Image> imagensCategoria4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_imagens);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Galeria de Imagens");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        grupos = populateGrupos();
        imagensCategoria1 = populateCategoria1();
        imagensCategoria2 = populateCategoria2();
        imagensCategoria3 = populateCategoria3();
        imagensCategoria4 = populateCategoria4();

        sectionAdapter = new SectionedRecyclerViewAdapter();

        sectionAdapter.addSection(new ExpandableSection(grupos.get(0), imagensCategoria1));
        sectionAdapter.addSection(new ExpandableSection(grupos.get(1), imagensCategoria2));
        sectionAdapter.addSection(new ExpandableSection(grupos.get(2), imagensCategoria3));
        sectionAdapter.addSection(new ExpandableSection(grupos.get(3), imagensCategoria4));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        GridLayoutManager glm = new GridLayoutManager(GaleriaImagensActivity.this, 3);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(sectionAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 3;
                    default:
                        return 1;
                }
            }
        });
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(sectionAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            Intent intent = new Intent(GaleriaImagensActivity.this, InformativoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_galeria_imagens) {
        } else if (id == R.id.nav_galeria_videos) {
            Intent intent = new Intent(GaleriaImagensActivity.this, GaleriaVideosActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_RFID) {
            Intent intent = new Intent(GaleriaImagensActivity.this, MonitoramentoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_sobre) {
            Intent intent = new Intent(GaleriaImagensActivity.this, SobreActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_diagnostico) {
            Intent intent = new Intent(GaleriaImagensActivity.this, DiagnosticoActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private List<String> populateGrupos(){
        ArrayList<String> array = new ArrayList<>();

        array.add("Lesões no Tronco");
        array.add("Lesões nos Ramos");
        array.add("Lesões nos Frutos");
        array.add("Lesões Falsas");

        return array;
    }
    private ArrayList<Image> populateCategoria1(){
        ArrayList<Image> array = new ArrayList<>();

        array.add(new Image(11, R.drawable.img_card_1, "Image 1-1"));
        array.add(new Image(12, R.drawable.img_card_2, "Image 1-2"));
        array.add(new Image(13, R.drawable.img_video2, "Image 1-3"));
        array.add(new Image(14, R.drawable.img_video3, "Image 1-4"));
        array.add(new Image(15, R.drawable.img_card_1, "Image 1-5"));
        array.add(new Image(16, R.drawable.img_card_2, "Image 1-6"));

        return array;
    }
    private ArrayList<Image> populateCategoria2(){
        ArrayList<Image> array = new ArrayList<>();

        array.add(new Image(21, R.drawable.img_card_1, "Image 2-1"));
        array.add(new Image(22, R.drawable.img_card_2, "Image 2-2"));
        array.add(new Image(23, R.drawable.img_video2, "Image 2-3"));
        array.add(new Image(24, R.drawable.img_video3, "Image 2-4"));
        array.add(new Image(25, R.drawable.img_card_1, "Image 2-5"));
        array.add(new Image(26, R.drawable.img_card_2, "Image 2-6"));

        return array;
    }
    private ArrayList<Image> populateCategoria3(){
        ArrayList<Image> array = new ArrayList<>();

        array.add(new Image(31, R.drawable.img_card_1, "Image 3-1"));
        array.add(new Image(32, R.drawable.img_card_2, "Image 3-2"));
        array.add(new Image(33, R.drawable.img_video2, "Image 3-3"));
        array.add(new Image(34, R.drawable.img_video3, "Image 3-4"));
        array.add(new Image(35, R.drawable.img_card_1, "Image 3-5"));
        array.add(new Image(36, R.drawable.img_card_2, "Image 3-6"));

        return array;
    }
    private ArrayList<Image> populateCategoria4(){
        ArrayList<Image> array = new ArrayList<>();

        array.add(new Image(41, R.drawable.img_card_1, "Image 4-1"));
        array.add(new Image(42, R.drawable.img_card_2, "Image 4-2"));
        array.add(new Image(43, R.drawable.img_video2, "Image 4-3"));
        array.add(new Image(44, R.drawable.img_video3, "Image 4-4"));
        array.add(new Image(45, R.drawable.img_card_1, "Image 4-5"));
        array.add(new Image(46, R.drawable.img_card_2, "Image 4-6"));
        array.add(new Image(47, R.drawable.img_card_1, "Image 4-7"));

        return array;
    }

    private class ExpandableSection extends StatelessSection {

        String title;
        ArrayList<Image> list;
        boolean expanded = false;

        ExpandableSection(String title, ArrayList<Image> list) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.item_galeria_imagens)
                    .headerResourceId(R.layout.grupo_categoria)
                    .build());

            this.title = title;
            this.list = list;
         }

        @Override
        public int getContentItemsTotal() {
            return expanded? list.size() : 0;
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            final int idImage = list.get(position).getId();
            final int resourceImage = list.get(position).getEndImage();
            final String tituloImage = list.get(position).getTitulo();

            final ArrayList<Integer> endImages = new ArrayList<>();
            final ArrayList<Integer> idsImages = new ArrayList<>();
            for (Image img: list){
                endImages.add(img.getEndImage());
                idsImages.add(img.getId());
                Log.i("OBJETO", img.getTitulo());
            }

            itemHolder.imageItem.setImageResource(resourceImage);

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GaleriaImagensActivity.this, ImagemGaleriaActivity.class);
                    intent.putExtra("title", tituloImage);
                    intent.putExtra("image", resourceImage);
                    intent.putExtra("id", idImage);
                    intent.putExtra("ids", idsImages);
                    intent.putExtra("endImages", endImages);
                    startActivity(intent);
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);

            headerHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    headerHolder.imgArrow.setImageResource(
                            expanded ? R.drawable.ic_keyboard_arrow_up : R.drawable.ic_keyboard_arrow_down
                    );
                    sectionAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView tvTitle;
        private final ImageView imgArrow;

        HeaderViewHolder(View view) {
            super(view);

            rootView = view;
            tvTitle = (TextView) view.findViewById(R.id.titulo_categoria_imagem);
            imgArrow = (ImageView) view.findViewById(R.id.imgArrow);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final ImageView imageItem;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            imageItem = (ImageView) view.findViewById(R.id.image_grid_galeria);
        }
    }


}
