package forfun.good.a2018011004;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Student on 2018/1/10.
 */

public class MyHandler extends DefaultHandler {
    boolean isTitle = false;
    @Override//標題
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("title"))
        {
            isTitle = true;
        }
    }

    @Override //結尾
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equals("title"))
        {
            isTitle = false;
        }
    }

    @Override //中間字串
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (isTitle)
        {
            Log.d("NET",new String(ch, start,length));
        }
    }
}
