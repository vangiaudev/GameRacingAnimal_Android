package vangiaurecca.example.gameanimalcrossing;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtScore;
    ImageButton ibtnPlay;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar sbOne, sbTwo, sbThree;

    int iScore = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();

        actionBar.hide();
        addControls();
        disableSeekBar();
        txtScore.setText(iScore + "");
        addEvent();
    }

    private void disableSeekBar() {
        sbOne.setEnabled(false);
        sbTwo.setEnabled(false);
        sbThree.setEnabled(false);
    }

    private void enableCheckBox() {
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }

    private  void disableCheckBox(){
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

    private void CalcScore(CheckBox cb){
        if(cb.isChecked()){
            iScore += 100;
            Toast.makeText(MainActivity.this, "Congratulations !!! You WIN and + 100 score", Toast.LENGTH_LONG).show();
        }
        else{
            iScore -= 50;
            Toast.makeText(MainActivity.this, "You LOSE and - 50 score", Toast.LENGTH_LONG).show();
        }
        txtScore.setText(iScore + "");
    }
    private void addEvent() {
        final CountDownTimer countDownTimer = new CountDownTimer(60000, 500) {
            @Override
            public void onTick(long l) {
                int number = 4;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                //Check WIN
                if(sbOne.getProgress() >= sbOne.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Bird WIN", Toast.LENGTH_LONG).show();
                    //check place a bet
                    CalcScore(cbOne);
                    enableCheckBox();
                }
                if(sbTwo.getProgress() >= sbTwo.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Buffalo WIN", Toast.LENGTH_LONG).show();
                    //check place a bet
                    CalcScore(cbTwo);
                    enableCheckBox();
                }
                if(sbThree.getProgress() >= sbThree.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Turtle WIN", Toast.LENGTH_LONG).show();
                    //check place a bet
                    CalcScore(cbThree);
                    enableCheckBox();
                }
                sbOne.setProgress(sbOne.getProgress() + one);
                sbTwo.setProgress(sbTwo.getProgress() + two);
                sbThree.setProgress(sbThree.getProgress() + three);
            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()){
                    sbOne.setProgress(0);
                    sbTwo.setProgress(0);
                    sbThree.setProgress(0);

                    ibtnPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();

                    disableCheckBox();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please, choose the animal before playing !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //uncheck 2,3
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //uncheck 1,3
                cbOne.setChecked(false);
                cbThree.setChecked(false);
            }
        });

        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //uncheck 1,2
                cbOne.setChecked(false);
                cbTwo.setChecked(false);
            }
        });
    }

    private void addControls() {
        txtScore = findViewById(R.id.txtScore);
        cbOne    = findViewById(R.id.cbOne);
        cbTwo    = findViewById(R.id.cbTwo);
        cbThree  = findViewById(R.id.cbThree);
        ibtnPlay = findViewById(R.id.ibtnPlay);
        sbOne    = findViewById(R.id.sbOne);
        sbTwo    = findViewById(R.id.sbTwo);
        sbThree  = findViewById(R.id.sbThree);

    }
}