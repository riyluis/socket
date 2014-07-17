package com.ejer.socket; 

import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle;  
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Socket_android extends Activity implements OnClickListener{
	EditText ip;
	EditText nombre;
	Button boton;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.socket_android);
		  ip = (EditText)findViewById(R.id.ip_conectar);
		  nombre = (EditText)findViewById(R.id.nombre_usuario);
		  boton = (Button)findViewById(R.id.iniciar);
		  boton.setOnClickListener(this); 
		    
	} 
	
	public void onClick(View arg0) {
		
		if(ip.getText().toString().equals("") || nombre.getText().toString().equals("") ){
			 Toast.makeText(this, "Ha dejado campos vacios",
		             Toast.LENGTH_LONG).show();
		}
		else{
		final Intent intent = new Intent(Socket_android.this, MainActivity.class);
		intent.putExtra("enviar_ip",ip.getText().toString());
		intent.putExtra("enviar_nombre",nombre.getText().toString());
		 
		startActivity(intent);
		}
		
	}

}
