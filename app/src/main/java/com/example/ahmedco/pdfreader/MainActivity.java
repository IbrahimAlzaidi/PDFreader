package com.example.ahmedco.pdfreader;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

  private Context context = this;
  private TextView textFromDialog;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

  }

  public void onEnterButtonClicked(View view) {

    Intent starter = new Intent(this,com.example.ahmedco.pdfreader.ListActivity.class);
    startActivity(starter);

  }

  public void onAboutButtonClicked(View view) {

    LayoutInflater inflater = LayoutInflater.from(context);
    View dialogLayout = inflater.inflate(R.layout.dialog,null);
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setView(dialogLayout);
    TextView indtruction = (TextView) dialogLayout.findViewById(R.id.dialog_text);
    ImageView dialogImage = (ImageView) dialogLayout.findViewById(R.id.dialog_image);
    AlertDialog customAlertDialog = builder.create();
    customAlertDialog.show();
  }

}
