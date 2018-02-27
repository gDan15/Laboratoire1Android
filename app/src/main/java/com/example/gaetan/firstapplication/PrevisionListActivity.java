package com.example.gaetan.firstapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class PrevisionListActivity extends AppCompatActivity implements ItemAdapter.ItemAdapterOnClickHandler, LoaderManager.LoaderCallbacks<String> {

    private RecyclerView resultView;

    private ItemAdapter itemAdapter;

    private static final int QUERY_LOADER = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prevision_list);

        resultView = (RecyclerView) findViewById(R.id.resultView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        resultView.setLayoutManager(layoutManager);
        resultView.setHasFixedSize(true);

        itemAdapter = new ItemAdapter(this);
        resultView.setAdapter(itemAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_prevision_list, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action) {

            Log.i("PrevisionListActivity", "Menu clicked");
            Toast.makeText(PrevisionListActivity.this, "Loading...", Toast.LENGTH_SHORT).show();


            Bundle queryURL = new Bundle();
            queryURL.putString("URL","https://andfun-weather.udacity.com/weather");

            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.restartLoader(QUERY_LOADER, queryURL, this);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(int index) {
        Context context = this;

        Class destinationClass = FirstApplication.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra(Intent.EXTRA_INDEX, index);
        startActivity(intent);
    }
    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {

            String json = null;

            @Override
            protected void onStartLoading() {
                if(json != null) {
                    deliverResult(json);
                } else {
                    forceLoad();
                }
            }

            @Override
            public String loadInBackground() {
                String searchUrl = args.getString("URL");
                try {
                    Log.i("ASyncTaskLoader", "start query");
                    return NetworkUtils.getResponseFromHttpUrl(searchUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(String data) {
                json = data;
                super.deliverResult(data);
            }
        };
    }
    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        try {
            Log.i("ASyncTaskLoader", "query loaded");
            Prevision.parse(data);
            itemAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}