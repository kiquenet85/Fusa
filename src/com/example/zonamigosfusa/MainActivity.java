/**
 * 
 */
package com.example.zonamigosfusa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.zonamigosfusa.otro.SecondActivity;

/**
 * @author Nestor Diazgranados
 * La clase para ser Actividad necesita heredar ese comportamiento.
 */
public class MainActivity extends ActionBarActivity {

	//-- Este botón existe en el layout activity_main.
	private Button btnSegundaActividad, btnMostrarUbicacion;
	private String LOG_TAG=this.getClass().getSimpleName(); 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//-- Se pueden utilizar las vistas del layout activity_main de la siguiente manera:
		btnSegundaActividad=(Button)findViewById(R.id.button1);
		btnMostrarUbicacion=(Button)findViewById(R.id.button2);
		//-- Ejemplo de un evento para un botón.
		btnSegundaActividad.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Es posible también llamar la siguiente actividad de dos formas:
				
				Intent i= new Intent(v.getContext(),SecondActivity.class);
				//Intent i= new Intent("com.example.zonamigosfusa.otro.SecondActivity");
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
	
}
