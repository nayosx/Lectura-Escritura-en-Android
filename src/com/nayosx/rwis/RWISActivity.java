package com.nayosx.rwis;

import java.io.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class RWISActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
	EditText ingresar;
	Button add, leer, borrar;
	TextView mostrar;
	final String ARCHIVO ="adds.dat", RUTA="/data/data/com.nayosx.rwis/files/"+ARCHIVO;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        inicializar();
    }
    private void inicializar()
    {
    	ingresar = (EditText) findViewById(R.id.ingresar);
    	
    	add = (Button) findViewById(R.id.bAdd);
    	leer = (Button) findViewById(R.id.bLeer);
    	borrar = (Button) findViewById(R.id.bBorrar);
    	
    	mostrar = (TextView) findViewById(R.id.mostrar);
    	
    	add.setOnClickListener(this);
    	leer.setOnClickListener(this);
    	borrar.setOnClickListener(this);
    	
    }
	/* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View p_v) {
	    // TODO Auto-generated method stub
	    switch(p_v.getId())
	    {
	    	case R.id.bAdd:
	    		addTexto();
	    		break;
	    	case R.id.bLeer:
	    		leerTexto();
	    		break;
	    	case R.id.bBorrar:
	    		borrarTexto();
	    		break;
	    }
    }
	/**
     * 
     */
    private void borrarTexto() {
	    // TODO Auto-generated method stub
	    File archivo = new File(RUTA);
	    archivo.delete();
	    if(archivo.delete())
	    {
	    	mensaje("No se pudo borar el archivo "+ARCHIVO);
	    }
	    else
	    {
	    	mostrar.setText("");
	    	mensaje("El archivo "+ARCHIVO+" fue borrado");
	    }
    }
	/**
     * 
     */
    private void leerTexto() {
	    // TODO Auto-generated method stub
    	try {
    		FileInputStream f = this.openFileInput(ARCHIVO);
    	    InputStreamReader isr = new InputStreamReader(f);
    	    BufferedReader br = new BufferedReader(isr);
    	    StringBuilder texto = new StringBuilder();
    	    String linea;
    	    while((linea=br.readLine()) != null)
    	    {
    	    	texto.append(linea);
    	    	texto.append("\n");
    	    }
    	    br.close();
    	    mostrar.setText(texto.toString());
    	} catch(IOException e)
    	{
    		e.printStackTrace();
    		mensaje("no se puede leer el archivo "+ARCHIVO);
    	}
	    
    }
	/**
     * 
     */
    private void addTexto() {
	    // TODO Auto-generated method stub
    	try
    	{
    		String aux = ingresar.getText().toString();
        	FileOutputStream f = this.openFileOutput(ARCHIVO, this.MODE_APPEND);
    	    OutputStreamWriter osw = new OutputStreamWriter(f);
    	    BufferedWriter bw = new BufferedWriter(osw);
    	    bw.write(aux);
    	    bw.newLine();
    	    bw.flush();
    	    bw.close();
    	    ingresar.setText("");
    	} catch(IOException e)
    	{ 
    		e.printStackTrace();
    		mensaje("No se pudo crear el archivo "+ARCHIVO);
    	}
	    
    }
	/**
     * @param p_string
     */
    private void mensaje(String p_string) {
	    // TODO Auto-generated method stub
	    Toast.makeText(getApplicationContext(), p_string, Toast.LENGTH_SHORT).show();
    }
}