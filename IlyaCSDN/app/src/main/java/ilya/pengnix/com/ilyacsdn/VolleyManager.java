package ilya.pengnix.com.ilyacsdn;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Avazu on 2015/10/23.
 */
public class VolleyManager {

    public static final String VOLLEY_DIR_JSON = "json";
    public static final String VOLLEY_DIR_IMAGE = "image";

    public static final int MAX_DISK_CACHE_IMAGE = 30 * 1024 * 1024;

    private static VolleyManager mInstance;

    private Context mContext;
    private ImageLoader mImageLoader;
    private RequestQueue mImageRequestQueue;

    private VolleyManager(Context context){
        this.mContext = context;
    }

    public static synchronized VolleyManager getmInstance(Context context){
        if(mInstance == null){
            mInstance = new VolleyManager(context);
        }
        return mInstance;
    }

    private RequestQueue getImageRequestQueue(){
        if(mImageRequestQueue == null){

        }
        return mImageRequestQueue;
    }

    public ImageLoader getImageLoader(){
        if(mImageLoader == null){

        }
        return mImageLoader;
    }

    private static class LruBitmapCache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache{
        @Override
        public Bitmap getBitmap(String url) {
            return get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            put(url,bitmap);
        }

        public LruBitmapCache(int maxSize) {
            super(maxSize);
        }

        public LruBitmapCache(){
            this(getCacheSize());
        }

        public static int getCacheSize(){
            int maxMemory = (int)Runtime.getRuntime().maxMemory();
            int maxSize = maxMemory/4;
            return maxSize;
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes()*value.getHeight();
        }
    }

}
