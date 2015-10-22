package ilya.pengnix.com.ilyacsdn.net;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avazu on 2015/10/22.
 */
public class SyncHttp {

    public String httpPost(String url,List<Parameter> params) throws Exception{
        String response = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        if(params.size() > 0){
            httpPost.setEntity(new UrlEncodedFormEntity(buildNameValuePair(params), HTTP.UTF_8));
        }
        HttpResponse httpResponse = httpClient.execute(httpPost);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if(statusCode == HttpStatus.SC_OK){
            response = EntityUtils.toString(httpResponse.getEntity());
        } else {
            response = "statusCode: " + statusCode;
        }
        return response;
    }

    private List<BasicNameValuePair> buildNameValuePair(List<Parameter> params){
        List<BasicNameValuePair> result = new ArrayList<BasicNameValuePair>();
        for(Parameter param:params){
            BasicNameValuePair pair = new BasicNameValuePair(param.getKey(),param.getValue());
            result.add(pair);
        }
        return result;
    }
}
