package www.kanavwadhawan.com.urbanclap_zomato;

import android.app.ProgressDialog;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity  {


    private RecyclerView userSearchRecyclerView;
    private List<Restaurants> mRestaurantsArrayList = new ArrayList<>();
    private userSearchAdapter userSearchAdapter;
    EditText searchText;
    Button searchButton,filterButton;
    TextView resultsFound;
    EditText filterText;

    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Loading Restaurants ...");


        userSearchRecyclerView = findViewById(R.id.searchUserRecyclerView);
        searchText=findViewById(R.id.searchText);
        searchButton=findViewById(R.id.Search);
        resultsFound=findViewById(R.id.totalResults);
        filterButton=findViewById(R.id.filterButton);


        userSearchAdapter = new userSearchAdapter(MainActivity.this, mRestaurantsArrayList);
        userSearchRecyclerView.setAdapter(userSearchAdapter);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(MainActivity.this);
        userSearchRecyclerView.setLayoutManager(gridLayoutManager);
        resultsFound.setVisibility(View.GONE);

        filterText=findViewById(R.id.filterText);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG",filterText.getText().toString());
                userSearchAdapter.filter(filterText.getText().toString());

            }
        });






        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd.show();

                userSearchAdapter.userSearchresult.clear();
                userSearchAdapter.notifyDataSetChanged();

                requestWithSomeHttpHeaders(searchText.getText().toString());

            }
        });




    }


    public void requestWithSomeHttpHeaders(String text) {

        try {


            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://developers.zomato.com/api/v2.1/search?entity_type=subzone&q=" + text + "&start=0";
            StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //resultsFound.setVisibility(View.VISIBLE);


                            Gson g = new Gson();
                            SearchModel p = g.fromJson(response.toString(), SearchModel.class);
                            resultsFound.setText("Search Results:- "+p.getResults_found());

                            mRestaurantsArrayList = Arrays.asList(p.getRestaurants());
                            userSearchAdapter.addAll(mRestaurantsArrayList);
                            userSearchAdapter.notifyDataSetChanged();
                            Log.d("Response", response);
                            pd.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub
                            Log.d("ERROR", "error => " + error.toString());
                            pd.dismiss();
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user-key", " c750173e8cf7e5fdc2c331cf897ee8c3");
                    params.put("Content-Type", "application/json");

                    return params;
                }
            };
            queue.add(getRequest);
        }
        catch (IllegalStateException e){




        }
        catch (Exception e){


        }

    }



}



















