package oosysteme.android;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomappbar.BottomAppBar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_BLUETOOTH = 0;

    private static final int REQUEST_STORAGE = 1;

    private final static String TAG = "MainActivity";

    public static enum CONN_TYPE {
        LEJOS_PACKET, LEGO_LCP
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomAppBar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("storage", ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) + "");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.BLUETOOTH)) {
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.BLUETOOTH},
                        REQUEST_BLUETOOTH);

            }
        } else {
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_STORAGE);

            }
        } else {
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_STORAGE);

            }
        } else {
        }

        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        /*getSupportFragmentManager().beginTransaction()
                .addToBackStack("strategies")
                .replace(R.id.frame_layout, new StrategiesFragment(), "strategies")
                .commitAllowingStateLoss();*/

        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frame_layout, new ManualControl2Fragment())
                .commitAllowingStateLoss();

        seupNXJCache();

        BTSend btSend = new BTSend();
        btSend.start();

        viewModel.getStrategyRepository().setBtSend(btSend);
    }

    public static NXTConnector connect(final CONN_TYPE connection_type) {
        Log.d(TAG, " about to add LEJOS listener ");

        NXTConnector conn = new NXTConnector();
        conn.setDebug(true);
        conn.addLogListener(new NXTCommLogListener() {

            public void logEvent(String arg0) {
                Log.e(TAG + " NXJ log:", arg0);
            }

            public void logEvent(Throwable arg0) {
                Log.e(TAG + " NXJ log:", arg0.getMessage(), arg0);
            }
        });

        switch (connection_type) {
            case LEGO_LCP:
                conn.connectTo("btspp://NXT", NXTComm.LCP);
                break;
            case LEJOS_PACKET:
                conn.connectTo("btspp://");
                break;
        }

        return conn;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bottomappbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                NavigationFragment navigationFragment = new NavigationFragment();
                navigationFragment.show(getSupportFragmentManager(), "navigation");
                break;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            moveTaskToBack(true);
        } else {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_BLUETOOTH:
                Log.d("request", "bluetooth");
                break;
        }
    }

    private void seupNXJCache() {
        File root = Environment.getExternalStorageDirectory();

        try {
            String androidCacheFile = "nxj.cache";
            File mLeJOS_dir = new File(root, "/leJOS NXJ");
            if (!mLeJOS_dir.exists()) {
                Log.d("mkdir", mLeJOS_dir.mkdir() + "");
            }
            File mCacheFile = new File(root + "/leJOS/", androidCacheFile);
            Log.d("mkdir2", mCacheFile.mkdirs() + "");

            if (root.canWrite() && !mCacheFile.exists()) {
                FileWriter gpxwriter = new FileWriter(mCacheFile);
                BufferedWriter out = new BufferedWriter(gpxwriter);
                out.write("");
                out.flush();
                out.close();
            } else {

            }
        } catch (IOException e) {
            Log.e(TAG, "Could not write nxj.cache " + e.getMessage(), e);
        }
    }
}
