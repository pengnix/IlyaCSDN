package ilya.pengnix.com.ilyacsdn.net;

import java.io.Serializable;

/**
 * Created by Avazu on 2015/10/22.
 */
public class Parameter implements Serializable,Comparable<Parameter>{

    private String key;
    private String value;

    public Parameter(){
        super();
    }

    public Parameter(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(Parameter another) {
        return 0;
    }
}
