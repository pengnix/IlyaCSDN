package ilya.pengnix.com.ilyacsdn.bean;

import com.lidroid.xutils.db.annotation.Column;

import java.io.Serializable;

/**
 * Created by Avazu on 2015/10/22.
 */
public abstract class BaseEntity implements Serializable{
    private static final long serialVersionUID = 4995176180527325406L;

    @Column(column = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
