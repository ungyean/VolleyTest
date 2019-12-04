package plearn.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

public class RequestQueueSetup extends AppCompatActivity {

   String serverUrl = Url.serverUrl+"/hello.php";
   // String serverUrl = "http://localhost/hello.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView);

        Cache cache=new DiskBasedCache(getCacheDir(), 1024*1024); //1 mg
        Network network=new BasicNetwork(new HurlStack());

        final RequestQueue requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                        requestQueue.stop();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.toString());
                        requestQueue.stop();

                    }
                });

//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
//        stringRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 30000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 30000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });
    }
}
