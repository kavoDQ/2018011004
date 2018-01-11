package forfun.good.a2018011004;

import android.util.Log;
import android.widget.ArrayAdapter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Student on 2018/1/10.
 */

public class MyHandler extends DefaultHandler {
    boolean isTitle = false;
    boolean isItem = false;
    boolean isLink = false;
    boolean isDescription = false;
    StringBuilder linkSB = new StringBuilder();
    StringBuilder descSB = new StringBuilder();
    public ArrayList<Mobile01NewsItem> newsItems = new ArrayList<>();
    Mobile01NewsItem item;

    @Override//標題
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch(qName)
        {
            case "title":
                isTitle = true;
                break;
            case  "item":
                isItem = true;
                item = new Mobile01NewsItem();
                break;
            case  "link":
                isLink = true;
                break;
            case "description":
                isDescription = true;
                descSB = new StringBuilder();
                break;
            }
        }

//        if (qName.equals("title"))
//        {
//            isTitle = true;
//        }
//        if (qName.equals("item"))
//        {
//            isItem = true;
//            item = new Mobile01NewsItem();
//        }
//        if (qName.equals("link"))
//        {
//            isLink = true;
//        } 用SWITCH替換


    @Override //結尾
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName) {
            case "title":
                isTitle = false;
                break;
            case "item":
                isTitle = false;
                Log.d("NET", "When add item, imgurl:" + item.imgurl);
                newsItems.add(item); //已經裝滿內容
                break;
            case "link":
                isLink = false;
                if (isItem) {
                    item.link = linkSB.toString();
                    linkSB = new StringBuilder();
                }
                break;
            case "description":
                isDescription = false;
                if (isItem) //比對條件是否為範圍
                {
                    String str = descSB.toString();

                    Pattern pattern = Pattern.compile("https.*jpg");
                    Matcher m = pattern.matcher(str); //做一個配對器
                    String imgurl ="";
                    if (m.find())
                    {
                        imgurl = m.group(0);
                    }

                    str = str.replaceAll("<img.*/>","");//用正規表示法替代掉此行程式碼
                    item.description =  str;
                    item.imgurl = imgurl;
                    Log.d("NET",imgurl);
                }
                break;
            }
        }

//        if(qName.equals("title"))
//        {
//            isTitle = false;
//        }
//        if(qName.equals("item"))
//        {
//            isItem = false;
//            newsItems.add(;
//        }
//        if (qName.equals("link"))
//        {
//            isLink = false;
//            if (isItem) //判斷是在Item裡面的link
//            {
//                item.link = linkSB.toString();
//                linkSB = new StringBuilder();
//            }





    @Override //中間字串
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (isTitle && isItem)
        {
            Log.d("NET",new String(ch, start,length));
            item.title = new String(ch, start ,length);
        }
        if (isLink && isItem)
        {
            Log.d("NET",new String(ch,start,length));
            linkSB.append(new String(ch,start,length));
        }
        if (isDescription && isItem)
        {
           descSB.append(new String(ch, start ,length));
        }
    }
}


