package xyz.yukisako.calcapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    double num1;
    double num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.plus);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.minus);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.time);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.devide);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(getNum()) {
            Intent intent = new Intent(this, SecondActivity.class);
            if (v.getId() == R.id.plus) {
                intent.putExtra("RESULT", num1 + num2);
            } else if (v.getId() == R.id.minus) {
                intent.putExtra("RESULT", num1 - num2);
            } else if (v.getId() == R.id.time) {
                intent.putExtra("RESULT", num1 * num2);
            } else if (v.getId() == R.id.devide) {
                intent.putExtra("RESULT", num1 / num2);
            }
            startActivity(intent);
        }
    }

    boolean getNum(){
        EditText firstText = (EditText) findViewById(R.id.firstnum);
        EditText secondText = (EditText) findViewById(R.id.secondnum);

        try{
            num1 = Double.parseDouble(firstText.getText().toString());
            num2 = Double.parseDouble(secondText.getText().toString());
            return true;
        } catch (NumberFormatException e){
            showAlertDialog();
            return false;
        }
    }

    private void showAlertDialog() {
        // AlertDialog.Builderクラスを使ってAlertDialogの準備をする
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("エラー");
        alertDialogBuilder.setMessage("数値以外のものが入力されています．");

        // 肯定ボタンに表示される文字列、押したときのリスナーを設定する
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        // AlertDialogを作成して表示する
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
