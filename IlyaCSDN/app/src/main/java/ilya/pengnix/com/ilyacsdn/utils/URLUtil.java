package ilya.pengnix.com.ilyacsdn.utils;

import ilya.pengnix.com.ilyacsdn.constant.AppConstants;

/**
 * Created by Avazu on 2015/10/26.
 */
public class URLUtil {

    public static String getBlogListURL(String baseUrl, int page){
        return baseUrl + page;
    }

    public static String getBlogDefaultUrl(String userId){
        return AppConstants.CSDN_BASE_URL + userId + "/article/list/";
    }

}
