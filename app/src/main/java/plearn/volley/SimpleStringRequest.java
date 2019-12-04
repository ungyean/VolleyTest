package plearn.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SimpleStringRequest extends AppCompatActivity {

   String serverUrl = Url.serverUrl+"/hello.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
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
//                        if (error instanceof NetworkError) {
//                        } else if (error instanceof ServerError) {
//                        } else if (error instanceof AuthFailureError) {
//                        } else if (error instanceof ParseError) {
//                        } else if (error instanceof NoConnectionError) {
//                        } else if (error instanceof TimeoutError) {
//                            Toast.makeText(getApplicationContext(),
//                                    "Oops. Timeout error!",
//                                    Toast.LENGTH_LONG).show();
//                        }

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
