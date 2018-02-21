package com.example.gaetan.firstapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.io.IOException;

import static com.example.gaetan.firstapplication.NetworkUtils.getResponseFromHttpUrl;


public class FirstApplication extends AppCompatActivity {

    private TextView mDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_application);
        mDisplay = (TextView) findViewById(R.id.action);
        makeQuery();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action) {
            Context context = FirstApplication.this;
            String textToShow = "Action initiated";
            Toast.makeText(context, textToShow,
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class QueryTask extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... params) {
            String searchUrl = params[0];
            String queryResults=null;
            try{
                queryResults = getResponseFromHttpUrl(searchUrl);
            }
            catch(IOException e){
                e.printStackTrace();
            }
            assert queryResults != null;
            System.out.println(queryResults);
            return queryResults;
        }
        @Override
        protected void onPostExecute(String queryResults){
            if(queryResults !=null && !queryResults.equals("")){
                mDisplay.setText(queryResults);
            }
        }
    }
    private void makeQuery(){
        new QueryTask().execute("https://andfun-weather.udacity.com/weather");
    }
    public void printPressure(){

    }
}
