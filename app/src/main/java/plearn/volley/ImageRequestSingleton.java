package plearn.volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class ImageRequestSingleton extends AppCompatActivity {

   String serverUrl = Url.serverUrl+"/yinyang.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_request);
        final ImageView imageView = findViewById(R.id.imageView);

        ImageRequest imageRequest = new ImageRequest(serverUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 0,0, ImageView.ScaleType.CENTER_CROP, null,
                    //max_width, max_height (0,0)actual size, scale_type, decode_format
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(imageRequest);

        //ASingleton.getInstance(this).addToRequestQueue(imageRequest);

    }
}
