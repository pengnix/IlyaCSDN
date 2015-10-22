package ilya.pengnix.com.ilyacsdn.net;

import java.util.ArrayList;
import java.util.List;

import ilya.pengnix.com.ilyacsdn.utils.APIUtil;

/**
 * Created by Avazu on 2015/10/22.
 */
public class Potocal {

    public static final String USER_NAME = "superpengnix";

    public static final String PWD = "112358";


    public static List<Parameter> getOauthParams(String username,String password){
        List<Parameter> params = new ArrayList<Parameter>();
        params.add(new Parameter("client_id", APIUtil.APP_KEY));
        params.add(new Parameter("client_secret",APIUtil.APP_SECRET));
        params.add(new Parameter("grant_type",password));
        params.add(new Parameter("username",username));
        params.add(new Parameter("password",password));
        return params;
    }
}
