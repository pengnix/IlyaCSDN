package ilya.pengnix.com.ilyacsdn.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Avazu on 2015/10/23.
 */
public class FileUtil {

    public static final String BASE_DIR = "ilya.pengnix.com.ilyacsdn";
    public static final String VOLLEY_DIR = "volley";

    public static boolean isExtenalStorageMounted(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static File getDiskRootDir(Context context){
        String rootPath = isExtenalStorageMounted() ?
                Environment.getExternalStorageDirectory().getPath():
                context.getFilesDir().getAbsolutePath();
        return new File(rootPath);
    }

    public static File createDir(File basePath){
        if(!basePath.exists()){
            if(!basePath.mkdirs()){
                basePath = null;
            }
        }
        return basePath;
    }

    public static File getBaseDir(Context context){
        File basePath = new File(getDiskRootDir(context), BASE_DIR);
        return createDir(basePath);
    }

    public static File getVolleyDir(Context context){
        File basePath = new File(getBaseDir(context),VOLLEY_DIR);
        return createDir(basePath);
    }
}
