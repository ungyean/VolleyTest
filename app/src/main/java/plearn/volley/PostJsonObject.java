package plearn.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PostJsonObject extends AppCompatActivity {

   String serverUrl = Url.serverUrl+"/json_post";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        final TextView name=findViewById(R.id.name);
        final TextView pw=findViewById(R.id.pw);

//        Map<String, String> parametersMap = new HashMap<>();
//        parametersMap.put("user", "john");
//        parametersMap.put("pw", "pw123");

        JSONObject json=new JSONObject();
        try {
            json.put("user", "ungy@gmail");
            json.put("pw", 1234567);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                Request.Method.POST,
                serverUrl,
                json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            name.setText(response.getString("user")+
                                    response.getInt("pw"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            name.setText(error.toString());                        }

                    }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

//
//        //jsonobjectrequest to send request and get response in json
//        jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.POST //request method
//                , Constants.BASE_URL + Constants.GET_PERSON_DETAILS_URL   //URL of php file
//                , new JSONObject(parametersMap) //parameters to send to server
//                , new Response.Listener<JSONObject>() { //response will come here in case of success
//            @Override
//            public void onResponse(JSONObject response) {...

//<?php
//        $json = json_decode(file_get_contents("php://input"),true); //sending json object from android..can not receive parameters using $_POST or $_GET
//          $user=$json->user;
//           $pw= $json->pw;
//?>

    }
}
