package br.com.george.menutest.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.com.george.menutest.Model.Image;
import br.com.george.menutest.R;

public class SlideGaleriaAdapter extends PagerAdapter {

    private ArrayList<Image> images;
    private LayoutInflater inflater;
    private int id;
    private Context context;

    public SlideGaleriaAdapter(Context context, ArrayList<Image> images, int id) {
        this.context = context;
        this.images = images;
        this.id = id;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object o) {
        View view = (View) o;
        ((ViewPager) collection).removeView(view);
        view = null;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.slide_galeria, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.imageSlideGaleria);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeResource(view.getResources(), images.get(position).getEndImage(), options);

        Glide.with(context).load(bitmap).into(myImage);
        view.addView(myImageLayout, 0);

        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
