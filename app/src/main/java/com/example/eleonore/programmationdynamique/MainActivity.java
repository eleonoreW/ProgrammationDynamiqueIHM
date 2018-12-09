package com.example.eleonore.programmationdynamique;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Turbine turbine1,turbine2,turbine3,turbine4,turbine5;
    CheckBox checkbox1,checkbox2,checkbox3,checkbox4,checkbox5;
    EditText debitTurbine1,debitTurbine2,debitTurbine3,debitTurbine4,debitTurbine5;
    EditText elevationAmontInput,debitTotalInput;
    TextView resTurbine1,resTurbine2,resTurbine3,resTurbine4,resTurbine5;
    double[][] coeffTurbine1,coeffTurbine2,coeffTurbine3,coeffTurbine4,coeffTurbine5;
    double elevationAmont;
    int debitTotal;
    private static final int DEFAULT_DEBIT = 160;
    private TextView puissanceTurbine1,puissanceTurbine2,puissanceTurbine3,puissanceTurbine4,puissanceTurbine5;
    private TextView hauteurTurbine1,hauteurTurbine2,hauteurTurbine3,hauteurTurbine4,hauteurTurbine5;
    private TextView puissanceTotale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSimulation();
        initViews();
    }

    private void initViews() {
        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);
        checkbox3 = findViewById(R.id.checkbox3);
        checkbox4 = findViewById(R.id.checkbox4);
        checkbox5 = findViewById(R.id.checkbox5);

        debitTurbine1 = findViewById(R.id.debitTurbine1);
        debitTurbine1.setText(Integer.toString(DEFAULT_DEBIT));
        debitTurbine2 = findViewById(R.id.debitTurbine2);
        debitTurbine2.setText(Integer.toString(DEFAULT_DEBIT));
        debitTurbine3 = findViewById(R.id.debitTurbine3);
        debitTurbine3.setText(Integer.toString(DEFAULT_DEBIT));
        debitTurbine4 = findViewById(R.id.debitTurbine4);
        debitTurbine4.setText(Integer.toString(DEFAULT_DEBIT));
        debitTurbine5 = findViewById(R.id.debitTurbine5);
        debitTurbine5.setText(Integer.toString(DEFAULT_DEBIT));

        elevationAmontInput = findViewById(R.id.elevationAmont);
        debitTotalInput = findViewById(R.id.debitTotal);
        resTurbine1 = findViewById(R.id.resTurbine1);
        resTurbine2 = findViewById(R.id.resTurbine2);
        resTurbine3 = findViewById(R.id.resTurbine3);
        resTurbine4 = findViewById(R.id.resTurbine4);
        resTurbine5 = findViewById(R.id.resTurbine5);

        puissanceTurbine1 = findViewById(R.id.puissanceTurbine1);
        puissanceTurbine2 = findViewById(R.id.puissanceTurbine2);
        puissanceTurbine3 = findViewById(R.id.puissanceTurbine3);
        puissanceTurbine4 = findViewById(R.id.puissanceTurbine4);
        puissanceTurbine5 = findViewById(R.id.puissanceTurbine5);

        hauteurTurbine1 = findViewById(R.id.hauteurTurbine1);
        hauteurTurbine2 = findViewById(R.id.hauteurTurbine2);
        hauteurTurbine3 = findViewById(R.id.hauteurTurbine3);
        hauteurTurbine4 = findViewById(R.id.hauteurTurbine4);
        hauteurTurbine5 = findViewById(R.id.hauteurTurbine5);

        puissanceTotale = findViewById(R.id.puissanceTotale);


    }

    public void initSimulation(){
        double[][] coeffTurbine1 = {{0.01795,-0.1966,0.002889,-1.194*Math.pow(10,-5)}, {-0.0004493,0.008152,7.483*Math.pow(10,-6)}};
         turbine1 = new Turbine(1, DEFAULT_DEBIT,coeffTurbine1);
        //      - turbine 2
        double[][] coeffTurbine2 = {{0.3245,-0.237,0.003811,-0.00001667},{-0.009282,0.006285,0.00002281}};
         turbine2 = new Turbine(2, DEFAULT_DEBIT,coeffTurbine2);
        //      - turbine 3
        double[][] coeffTurbine3 = {{0.001055,-0.216,0.003543,-0.00001572},{-0.00004689,0.006365,0.0000221}};
        turbine3 = new Turbine(3, DEFAULT_DEBIT, coeffTurbine3);
        //      - turbine 4
        double[][] coeffTurbine4 = {{0.03327,-0.1916,0.003544,-0.00001693},{-0.001008,0.004989,0.00003474}};
         turbine4 = new Turbine(4, DEFAULT_DEBIT, coeffTurbine4);
        //      - turbine 5
        double[][] coeffTurbine5 = {{-0.004301,-0.08809,0.002881,-0.00003086,0.00000008458},{0.0001528,0.00009614,0.0001387,-0.0000004462}};
         turbine5 = new Turbine(5, DEFAULT_DEBIT, coeffTurbine5);
    }

    public void onClickSimulation(View view){
        simulation();
    }

    public void simulation(){
        updateData();
        new SystemeResolution().resoudre(turbine1, turbine2, turbine3, turbine4, turbine5);
        resTurbine1.setText(Integer.toString(turbine1.getDebitReel()));
        resTurbine2.setText(Integer.toString(turbine2.getDebitReel()));
        resTurbine3.setText(Integer.toString(turbine3.getDebitReel()));
        resTurbine4.setText(Integer.toString(turbine4.getDebitReel()));
        resTurbine5.setText(Integer.toString(turbine5.getDebitReel()));
        updateData();
    }

    private void updateData() {
        Constante.debitTotal = SystemeResolution.arrondi(Integer.parseInt(String.valueOf(debitTotalInput.getText())));
        Constante.elevAmont = Double.parseDouble(String.valueOf(elevationAmontInput.getText()));
        updateTurbine(turbine1,checkbox1.isChecked(),debitTurbine1,puissanceTurbine1,hauteurTurbine1);
        updateTurbine(turbine2,checkbox2.isChecked(),debitTurbine2,puissanceTurbine2,hauteurTurbine2);
        updateTurbine(turbine3,checkbox3.isChecked(),debitTurbine3,puissanceTurbine3,hauteurTurbine3);
        updateTurbine(turbine4,checkbox4.isChecked(),debitTurbine4,puissanceTurbine4,hauteurTurbine4);
        updateTurbine(turbine5,checkbox5.isChecked(),debitTurbine5,puissanceTurbine5,hauteurTurbine5);
        puissanceTotale.setText(Double.toString(turbine1.puissance()+turbine2.puissance()+turbine3.puissance()+turbine4.puissance()+turbine5.puissance()));
    }


    public void graphique(View view){
        GridLayout fragment = (GridLayout) findViewById(R.id.fragment);
        if(fragment.getVisibility() == View.INVISIBLE){
            fragment.setVisibility(View.VISIBLE);
        }else{
            fragment.setVisibility(View.INVISIBLE);
        }
    }

    private void updateTurbine(Turbine turbine, Boolean active,EditText debitTurbine, TextView puissanceTurbine,TextView hauteurDeChute) {
        if(active) {
            turbine.setDebitMaxReel(Integer.parseInt(String.valueOf(debitTurbine.getText())));
            if(turbine.getDebitReel() !=0) {
                puissanceTurbine.setText(Double.toString(turbine.puissance(turbine.getDebitReel())));
            } else {
                puissanceTurbine.setText("0");
            }
            hauteurDeChute.setText(Double.toString(Constante.hauteurChuteNette(turbine.getDebitReel())));
        } else {
            turbine.setDebitMaxReel(0);
            debitTurbine.setText("-");
            puissanceTurbine.setText("-");
            hauteurDeChute.setText("-");
        }
    }
}
