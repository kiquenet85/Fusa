/**
 * 
 */
package com.example.zonamigosfusa;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Nestor Diazgranados
 * La clase para ser Actividad necesita heredar ese comportamiento.
 */
public class MainActivity extends ActionBarActivity {

	//--Broadcast 
	private static final String CUSTOM_INTENT = "course.examples.BroadcastReceiver.show_toast";
	
	//-- Este botón existe en el layout activity_main.
	private Button btnSegundaActividad, btnMostrarUbicacion, btnCrearBroacast;
	private String LOG_TAG=this.getClass().getSimpleName(); 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getBatteryStatus();
		
		//-- Se pueden utilizar las vistas del layout activity_main de la siguiente manera:
		btnSegundaActividad=(Button)findViewById(R.id.button1);
		btnMostrarUbicacion=(Button)findViewById(R.id.button2);
		btnCrearBroacast=(Button)findViewById(R.id.button3);
		//-- Ejemplo de un evento para un botón.
		btnSegundaActividad.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Es posible también llamar la siguiente actividad de dos formas:
				
				//Intent i= new Intent(v.getContext(),SecondActivity.class);
				Intent i= new Intent("com.example.zonamigosfusa.otro.SecondActivity");
				startActivity(i);
			}
		});
		
		btnMostrarUbicacion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Es posible también llamar la siguiente actividad de dos formas:
				showLocationInMap();
			}
		});
		
		OnClickListener myListener=new myListener();
		btnCrearBroacast.setOnClickListener(myListener);
	}
	
	//-- Instanciar un Listener a través de una clase anónima.
	public class myListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			sendBroadcast(new Intent(CUSTOM_INTENT),
					android.Manifest.permission.VIBRATE);
		}
		
	}
		
	public void showLocationInMap() {
		Uri uri = Uri
				.parse("geo:4.334858,-74.350432?q=Fusagasuga+Cundinamarca,+Colombia");
        //Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
		Intent intent = new Intent();
		intent.setData(uri);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
		if (intent.resolveActivity(this.getPackageManager()) != null) {
			startActivity(intent);
		} else {
			String msg = "Is not possible to see location in MAP, there are not available apps";
			Log.d(LOG_TAG, msg);
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		}
	}
	
	 //http://developer.android.com/training/monitoring-device-state/battery-monitoring.html
    private int getBatteryStatus() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);

        // Are we charging / charged?
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        int level=batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level / (float)scale;

        // How are we charging?
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        Toast.makeText(this,"Percentage: "+batteryPct+" Battery Level is: "+level+", CHARGING: "+isCharging+" USB Plug:"+usbCharge+" CHARGE Plug:"+acCharge,Toast.LENGTH_LONG).show();
        return status;
    }
	
}
