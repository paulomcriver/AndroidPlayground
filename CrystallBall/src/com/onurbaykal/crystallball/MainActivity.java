package com.onurbaykal.crystallball;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final TextView answerLabel = (TextView) findViewById(R.id.textView1);
        Button getAnswerButton = (Button) findViewById(R.id.button1); 
        
        
        
        getAnswerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String answer = "";
				
				Random r = new Random();
				
				switch(r.nextInt(4))
				{
				case 0:
					answer = "YES";
					break;
				case 1:
					answer = "NO";
					break;
				case 2: 
					answer = "MAYBE";
					break;
				case 3:
					answer = "NOT A CHANCE";
					break;
				}
				
				answerLabel.setText(answer);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
