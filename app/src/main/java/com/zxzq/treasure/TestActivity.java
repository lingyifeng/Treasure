package com.zxzq.treasure;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.sephiroth.android.library.picasso.Picasso;

public class TestActivity extends AppCompatActivity {

    private ImageView mIv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mIv_show = (ImageView) findViewById(R.id.iv_show);

        try {
            URL url = new URL("http://7xsct4.com1.z0.glb.clouddn.com/16-9-21/44621585.jpg");
            getBitmapBounds(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void getBitmapBounds(final URL url)  {
        final int width = getWindowManager().getDefaultDisplay().getWidth();
        final int height = getWindowManager().getDefaultDisplay().getHeight();


        new Thread(new Runnable() {
            @Override
            public void run() {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;// 只返回bitmap的大小，可以减少内存使用，防止OOM.
                InputStream is = null;
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    is=httpURLConnection.getInputStream();
                    BitmapFactory.decodeStream(is, null, options);
                    ViewGroup.LayoutParams layoutParams = mIv_show.getLayoutParams();
                    layoutParams.height=options.outHeight*width/height;
                    layoutParams.width=width/2;
                    mIv_show.setLayoutParams(layoutParams);
                    System.out.println("height"+options.outHeight*width/height);
                    System.out.println("width"+width/2);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Picasso.with(TestActivity.this).load(String.valueOf(url)).into(mIv_show);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();



    }
}
