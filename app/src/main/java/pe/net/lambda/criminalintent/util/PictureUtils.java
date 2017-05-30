package pe.net.lambda.criminalintent.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by Gary on 26/05/2017.
 */

public class PictureUtils {

    public static Bitmap getScaleBitmap(String path, Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return getScaleBitmap(path, size.x , size.y);
    }

    public static Bitmap getScaleBitmap(String path, int destWidth, int destHeight){
        //Lee las dimensiones de la imagen del disco
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        //Averigua cuanto se debe reducir el tamaño
        int inSampleSize = 1;
        if ( srcHeight > destHeight || srcWidth > destWidth){
            if(srcWidth > srcHeight){
                inSampleSize = Math.round(srcHeight / destHeight);
            }else{
                inSampleSize = Math.round( srcWidth / destWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        //vuelva a leerlo y crea el mapa de bits definido
        return BitmapFactory.decodeFile(path, options);
    }
}
