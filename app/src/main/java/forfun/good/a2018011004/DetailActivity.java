package forfun.good.a2018011004;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Student on 2018/1/11.
 */

public class DetailActivity extends AppCompatActivity {
        WebView wv;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);
            Intent it = getIntent();
            String link = it.getStringExtra("link");
            Log.d("NET", "Link:" + link);

            wv = (WebView) findViewById(R.id.webView);
            wv.setWebChromeClient(new WebChromeClient());
            wv.getSettings().setJavaScriptEnabled(true);
            wv.loadUrl(link);
        }
    }

