package plearn.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ASingleton {
    private static ASingleton instance;
    private static RequestQueue requestQueue;
    private Context context;

    private ASingleton(Context context){
        this.context=context;
        requestQueue=getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized ASingleton getInstance(Context context){
        if(instance==null){
            instance=new ASingleton(context);
        }
        return instance;
    }
    public  void addToRequestQueue(Request request){
        requestQueue.add(request);
    }
}
