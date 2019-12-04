package plearn.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestJsonArray extends AppCompatActivity {

   String serverUrl = Url.serverUrl+"/user_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_all);

        final TextView user=findViewById(R.id.user);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST,
                serverUrl, null, //request body
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String result="";
                        try {
//                            for(int i=0; i< response.length(); i++){
//                                JSONObject jsonObject=response.getJSONObject(i);
//                                result+= jsonObject.getString("user")+" "+
//                                         jsonObject.getString("password")+"\n";
//                            }
                            result=response.toString();
                            user.setText(result);
//                            Toast.makeText(getApplicationContext(), result,
//                                    Toast.LENGTH_LONG).show();


                        } catch (Exception e) {
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
        requestQueue.add(jsonArrayRequest);

        //ASingleton.getInstance(this).addToRequestQueue(imageRequest);

    }
}
