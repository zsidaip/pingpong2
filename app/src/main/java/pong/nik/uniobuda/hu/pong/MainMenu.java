package pong.nik.uniobuda.hu.pong;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainMenu extends AppCompatActivity {

    Game game;
    SensorManager manager;
    BluetoothAdapter adapter;//=new BluetoothAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      /*  setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        game=new Game(this);
        setContentView(game);
        manager= (SensorManager) getSystemService(SENSOR_SERVICE);
        adapter=BluetoothAdapter.getDefaultAdapter();
        if(adapter == null){
            // Bluetooth nem támogatott
        }else if(!adapter.isEnabled()){
            // BT nincs engedélyezve, küldünk egy kérést
            Intent i = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);
        }else{

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private SensorEventListener listener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values=event.values;
            int direction=1;
            int move= (int)(values[0]*4);
            if(move<0){
                move*=-1;
                direction*=-1;
            }
            game.prec1.setMove(direction, move);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(listener, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(listener);
    }

}
