package ilya.pengnix.com.ilyacsdn.bean;

import com.lidroid.xutils.db.annotation.Column;

/**
 * Created by Avazu on 2015/10/22.
 */
public class Blogger extends BaseEntity{
    private static final long serialVersionUID = 6569781303855823679L;

    @Column(column = "userId")
    private String userId;

    @Column(column ="title")
    private String title;

    @Column(column ="description")
    private String description;

    @Column(column = "imgUrl")
    private String imgUrl;

    @Column(column = "link")
    private String link;

    @Column(column = "type")
    private String type;

    @Column(column = "category")
    private String category;

    @Column(column = "isNew")
    private int isNew;

    @Column(column = "isTop")
    private int isTop;

    @Column(column = "updateTime")
    private long updateTime;

    @Column(column = "reserve")
    private String reserve;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
}
