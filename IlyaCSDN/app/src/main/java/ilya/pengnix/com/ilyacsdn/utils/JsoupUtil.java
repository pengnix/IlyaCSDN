package ilya.pengnix.com.ilyacsdn.utils;

import android.text.TextUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ilya.pengnix.com.ilyacsdn.bean.BlogItem;

/**
 * Created by Avazu on 2015/10/28.
 */
public class JsoupUtil {
    private static final String BLOG_URL = "http://blog.csdn.net";

    public static List<BlogItem> getBlogItemList(String str){
        List<BlogItem> list = new ArrayList<BlogItem>();
        Document doc  = Jsoup.parse(str);
        Elements blogList = doc.getElementsByClass("article_item");
        //Elements blogList = doc.select("article_item").select("list_item");
        //Log.i("pengnix3","size = " +blogList.size());
        for(Element blogItem: blogList){
            BlogItem item = new BlogItem();
            String title = blogItem.select("h1").text();
            String description =blogItem.select("div.article_description").text();
            String date = blogItem.getElementsByClass("article_manage").get(0).text();
            String link = BLOG_URL + blogItem.select("h1").select("a").attr("href");

            item.setTitle(title);
            item.setContent(description);
            item.setDate(date);
            item.setLink(link);
            list.add(item);
            //Log.i("pengnix3","title = " +title);
        }
        return list;
    }

    public static String getContent(String str){
        if(TextUtils.isEmpty(str)){
            return null;
        }
        Element detailElement = Jsoup.parse(str).getElementsByClass("details").get(0);
        detailElement.select("script").remove();
        if (detailElement.getElementById("digg") != null) {
            detailElement.getElementById("digg").remove();
        }
        if (detailElement.getElementsByClass("tag2box") != null) {
            detailElement.getElementsByClass("tag2box").remove();
        }

        detailElement.getElementsByClass("article_manage").remove();
        detailElement.getElementsByTag("h1").tagName("h2");
        Iterator<?> localIterator = detailElement.select("pre[name=code]").iterator();
        while (true) {
            if (!localIterator.hasNext())
                return detailElement.toString();
            Element localElement2 = (Element) localIterator.next();
            localElement2.attr("class", "brush: java; gutter: false;");
            //Log.i("CSNDBlog_JsoupUtil", "codeNode.text()" + localElement2.text());
        }
    }
}
