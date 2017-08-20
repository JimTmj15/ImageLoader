package com.example.imgloader;

/**
 * Created by Jimmy-PC on 20/8/2017.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 *
 * This class is to handle/process the bitmap obtained.
 */
public class BitmapHelper {

    /**
     *
     * This method is to calculate the
     * sample size from the bitmap obtained
     * in order to save memory by downsample
     * the input bitmap.
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return Return sample size
     */
    public static int computeInSampleSize(BitmapFactory.Options options,
                                          int reqWidth, int reqHeight) {
        /**
         *
         * Define the original inSampleSize &
         * get the height & width of the
         * input bitmap.
         * If set inSampleSize to a value > 1, requests the decoder
         * to subsample the original image, returning a smaller image to save memory.
         */
        final int height = options.outHeight; //get raw height
        final int width = options.outWidth; //get raw width
        int inSampleSize = 1; //set inSampleSize to 1

        /**
         *
         * If the raw height & width larger than the
         * required height & width, re-calculate the
         * inSampleSize.
         * Else remain inSampleSize equal to 1.
         */
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;  //calculate inSampleSize in multiplier of 2
            }
        }
        return inSampleSize;
    }

    /**
     *
     * This method is to decode the bitmap from byte array input &
     * prompt desired width & height from the caller.
     *
     * @param data
     * @param reqWidth
     * @param reqHeight
     * @return Return decoded bitmap
     */
    public static Bitmap decodeBitmapFromByteArray(byte[]data,
                                                   int reqWidth, int reqHeight){

        /**
         *
         * Set inJustDecodeBounds to true to decode the bitmap in order
         * to obtain the dimensions input bitmap
         */
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        options.inSampleSize = computeInSampleSize(options, reqWidth,
                reqHeight);

        /**
         *
         * decode the input byte array with inSampleSize calculated.
         */
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }
}
