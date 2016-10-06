package xuanhien.ta.magicsquare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Ping on 10/5/2016.
 */
public class ResultActivity extends AppCompatActivity{
    private String TAG = "ResultActivity";
    private TextView lblMatrixSquare;

    private int numberMatrix;
    private int [][] matrixSquare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        lblMatrixSquare = (TextView) findViewById(R.id.lblMatrixSquare);

        Bundle args = this.getIntent().getExtras();
        if (args != null) {
            numberMatrix = args.getInt(MainActivity.NUMBER_INPUT);
            Log.d(TAG, "numberMatrix: " + String.valueOf(numberMatrix));

            setValueMagicSquare(numberMatrix);
            displayMagicSquare();
        }
    }

    private void displayMagicSquare(){
        String textOfMatrix = "";
        for(int y = 0; y < numberMatrix; y++){
            for(int x = 0; x < numberMatrix; x++){
                textOfMatrix += " | " + String.valueOf(matrixSquare[y][x]);
            }
            textOfMatrix += "\n";
        }
//        Log.d(TAG, "Text: " + textOfMatrix);
        lblMatrixSquare.setText(textOfMatrix);
    }

    private void setValueMagicSquare(int numberMatric){
        matrixSquare = new int[numberMatric][numberMatric];

        for(int y = 0; y < numberMatrix; y++) {
            for (int x = 0; x < numberMatrix; x++) {
                matrixSquare[y][x] = 0;
            }
        }

        int middlePosition = numberMatric/2;
        int[] nextPosition = new int[2];
        nextPosition[0] = 0;
        nextPosition[1] = middlePosition;
        //Set first value
        matrixSquare[nextPosition[0]][nextPosition[1]] = 1;

        int maxNumber = numberMatric * numberMatric;
        if(maxNumber > 1){
            for(int i = 2; i <= maxNumber; i++){
                nextPosition = getNextPosition(nextPosition[1], nextPosition[0]);
                matrixSquare[nextPosition[0]][nextPosition[1]] = i;
            }
        }
    }

    // xPositionInput - Horizontal position of matrix
    // yPositionInput - Verizontal position of matrix
    private int[] getNextPosition(int xPositionInput, int yPositionInput){
        int[] nextPosition = new int[2];

        int xPosition = xPositionInput + 1;
        int yPosition = yPositionInput - 1;

        if(xPosition >= numberMatrix)
            xPosition = 0;
        if(yPosition < 0)
            yPosition = numberMatrix - 1;

        Log.d(TAG, "position: " + String.valueOf(yPosition) + ", " + String.valueOf(xPosition) + " = " + matrixSquare[yPosition][xPosition]);
        if(matrixSquare[yPosition][xPosition] != 0){
            yPosition = yPositionInput + 1;
            if(yPosition >= numberMatrix)
                yPosition = 0;
            //Don't add for X value
            xPosition = xPositionInput;
        }

        nextPosition[0] = yPosition;
        nextPosition[1] = xPosition;
        Log.d(TAG, "nextPosition: " + String.valueOf(xPositionInput) + " - " + String.valueOf(yPositionInput) + " : " + String.valueOf(nextPosition[0]) + ", " + String.valueOf(nextPosition[1]));
        return nextPosition;
    }
}
