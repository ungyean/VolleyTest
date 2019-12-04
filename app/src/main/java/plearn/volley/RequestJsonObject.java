package plearn.volley;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestJsonObject extends AppCompatActivity {

   String serverUrl = Url.serverUrl+"/json_object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        final TextView name=findViewById(R.id.name);
        final TextView pw=findViewById(R.id.pw);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                serverUrl, null, //request body
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            name.setText(response.getString("user"));
                            pw.setText(response.getString("pw"));
                            Toast.makeText(getApplicationContext(),
                                    response.getString("pw"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

        //ASingleton.getInstance(this).addToRequestQueue(imageRequest);

    }
}
