package com.ejer.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket; 
import android.app.Activity; 
import android.os.AsyncTask;
import android.os.Bundle; 
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText; 
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	EditText Mensaje;
	TextView dato_recibido;
	Button Boton_enviar;
	String Nombre;
	String ip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		  super.onCreate(savedInstanceState);
		    setContentView(R.layout.activity_main);
		    Mensaje = (EditText)findViewById(R.id.caja_mensaje);
		    dato_recibido=(TextView) findViewById(R.id.mensaje_enviado);
		    Boton_enviar = (Button)findViewById(R.id.enviar);
		    Boton_enviar.setOnClickListener(this); 
		   
		    Bundle recogerDatos = getIntent().getExtras(); 
		    ip = recogerDatos.getString("enviar_ip");
		    Nombre = recogerDatos.getString("enviar_nombre"); 
		    unir(); 
		
		    
	} 

	public void onClick(View arg0) {
		
	    Thread t = new Thread(){

	        @Override
	        public void run() {
	            try {
	            	
	            	Socket s = new Socket(ip, 9999);
	                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	                dos.writeUTF(Nombre+" Dice:"+Mensaje.getText().toString());

	               
	                DataInputStream dis2 = new DataInputStream(s.getInputStream());
	                InputStreamReader disR2 = new InputStreamReader(dis2);
	                BufferedReader br = new BufferedReader(disR2); 
	                 
	                dis2.close();
	                s.close();

	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    };
	   
	    if(Mensaje.getText().toString().equals("")){
  			 Toast.makeText(this, "Escribe tu mensaje",
  		             Toast.LENGTH_LONG).show();
  		}
	    else{
	    	t.start();
	    	Toast.makeText(this, "Mensaje Enviado", Toast.LENGTH_SHORT).show();
	        dato_recibido.append("Tu Dices:"+Mensaje.getText().toString() + "\n");
	        
	    }
	}
	
	 
	public void unir() {
		  Thread t_unir = new Thread(){

		        @Override
		        public void run() {
		            try {
		            	
		            	Socket s = new Socket(ip, 9999);
		                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		                dos.writeUTF(Nombre+" se a unido");
		                s.close();

		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    };
		    
		    t_unir.start();
	}
	
	
	

}
