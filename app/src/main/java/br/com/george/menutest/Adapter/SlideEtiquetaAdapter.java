package br.com.george.menutest.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import br.com.george.menutest.Model.ImagemBD;
import br.com.george.menutest.R;

public class SlideEtiquetaAdapter extends PagerAdapter {

    private ArrayList<ImagemBD> images;
    private LayoutInflater inflater;
    private Context context;

    public SlideEtiquetaAdapter(Context context, ArrayList<ImagemBD> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object o) {
        View view = (View)o;
        ((ViewPager) collection).removeView(view);
        view = null;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View myImageLayout = inflater.inflate(R.layout.slide_etiqueta, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.imageSlideEtiqueta);
        ImageView btnDescricao = (ImageView) myImageLayout.findViewById(R.id.btnDescricaoEtiqueta);
        TextView dateImage = (TextView) myImageLayout.findViewById(R.id.dataImagemEtiqueta);
        final TextView descricao = (TextView) myImageLayout.findViewById(R.id.descricaoImagemEtiqueta);
        final LinearLayout layoutBtnDesc = (LinearLayout) myImageLayout.findViewById(R.id.layoutBtnDesc);
        Uri imageUri = Uri.parse(images.get(position).getImagem());
        try {
            btnDescricao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(descricao.getText().equals("")){
                        descricao.setText(images.get(position).getDescricao());
                        layoutBtnDesc.setBackgroundColor(layoutBtnDesc.getResources().getColor(R.color.btnDescDesativado));
                    } else {
                        descricao.setText("");
                        layoutBtnDesc.setBackgroundColor(layoutBtnDesc.getResources().getColor(R.color.btnDescAtivado));
                    }
                }
            });

            String srcFile = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOCUMENTS + "/ImagensBancoIFSC/" + images.get(position).getImagem().substring(100);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(srcFile), null, options);

            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotated = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            Glide.with(context).load(rotated).into(myImage);
            dateImage.setText(images.get(position).getData());
            view.addView(myImageLayout, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
