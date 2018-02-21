package br.com.george.menutest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.george.menutest.Model.Card;
import br.com.george.menutest.R;

public class CardAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Card> cards;

    public CardAdapter(@NonNull Context c, @NonNull ArrayList<Card> objects) {
        super(c, 0, objects);
        context = c;
        cards = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if (cards != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.card_inicio, parent, false);

            ImageView imgCard = (ImageView) view.findViewById(R.id.img_card);
            TextView tituloCard = (TextView) view.findViewById(R.id.titulo_card);
            TextView descricaoCard = (TextView) view.findViewById(R.id.descricao_card);

            Card card = cards.get(position);

            imgCard.setImageResource(card.getImgCardResource());
            tituloCard.setText(card.getTituloCard());
            descricaoCard.setText(card.getDescricaoCard());
        }

        return view;
    }
}
