package com.aidiapp.salonbike;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.view.Menu;
/**
 * Esta clase representa el contenedor general de la aplicación
 * @author aidiapp
 *
 */
public class MainActivity extends Activity {
public MapManager mapmngr;

	@Override
	/**
	 * Cuando se inicia la activity inicializamos el mapmanager
	 */
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.mapmngr=new MapManager();
		FragmentManager gestorFragments=this.getFragmentManager();
		FragmentTransaction transicion=gestorFragments.beginTransaction();
		transicion.add(R.id.contenedorFragmnt, this.mapmngr);
		transicion.commit();
		//this.mapmngr.goToYourLocation();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
