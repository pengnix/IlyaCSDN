package ilya.pengnix.com.ilyacsdn.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.os.Build;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.io.File;

/**
 * Created by Avazu on 2015/10/23.
 */
public class ICVolley extends Volley{

    public static RequestQueue newRequestQueue(Context context, File cacheFile, int maxDiskCacheBytes) {
        HttpStack stack = null;
        String userAgent = "volley/0";
        try {
            String packageName = context.getPackageName();
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            userAgent = packageName + "/" + info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }

        if (stack == null) {
            if (Build.VERSION.SDK_INT >= 9) {
                stack = new HurlStack();
            } else {
                // Prior to Gingerbread, HttpUrlConnection was unreliable.
                // See: http://android-developers.blogspot.com/2011/09/androids-http-clients.html
                stack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent));
            }
        }

        Network network = new BasicNetwork(stack);

        RequestQueue queue = null;
        if (maxDiskCacheBytes <= -1)
        {
            // No maximum size specified
            queue = new RequestQueue(new DiskBasedCache(cacheFile), network);
        }
        else
        {
            // Disk cache size specified
            queue = new RequestQueue(new DiskBasedCache(cacheFile, maxDiskCacheBytes), network);
        }

        queue.start();

        return queue;
    }
}
