package xuanhien.ta.magicsquare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText txtInputNumber;
    private Button btnViewResult;
    private TextView lblAlertMessage;

    public static String NUMBER_INPUT = "NUMBER_INPUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInputNumber = (EditText)findViewById(R.id.txtInputNumber);
        btnViewResult = (Button)findViewById(R.id.btnViewResult);
        btnViewResult.setOnClickListener(this);
        lblAlertMessage = (TextView)findViewById(R.id.lblAlertMessage);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnViewResult:
                processClickResultButton();
                break;
        }
    }

    private void processClickResultButton(){
        String inputText = txtInputNumber.getText().toString();
        if(inputText.length() == 0){
            lblAlertMessage.setText(this.getApplicationContext().getString(R.string.alert_message));
//            Toast.makeText(this.getApplicationContext(), this.getApplicationContext().getString(R.string.alert_message), Toast.LENGTH_SHORT);
        }else{
            int numberInput = Integer.parseInt(inputText);
            if(numberInput % 2 == 0){
                lblAlertMessage.setText(this.getApplicationContext().getString(R.string.alert_message));
//                Toast.makeText(this.getApplicationContext(), this.getApplicationContext().getString(R.string.alert_message), Toast.LENGTH_SHORT);
            }else{
                lblAlertMessage.setText("");
                Bundle bundle = new Bundle();
                bundle.putInt(MainActivity.NUMBER_INPUT, numberInput);
                gotoActivity(this.getApplicationContext(), ResultActivity.class, bundle);
            }
        }
    }

    public void gotoActivity(Context context, Class<?> cla, Bundle bundle)
    {
        Intent intent = new Intent(context, cla);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
