package jp.suntech.s21002.bmicalculators002;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // リスナ
        CalculateListener listener = new CalculateListener();

        // ボタン押下
        Button btCalculate = findViewById(R.id.btCalculate);
        Button btClear = findViewById(R.id.btClear);

        btCalculate.setOnClickListener(listener);
        btClear.setOnClickListener(listener);

        // 非表示
        findViewById(R.id.tvYourDegreeOfOdesite).setVisibility(View.INVISIBLE);
        findViewById(R.id.tvYourAppropriateWeight).setVisibility(View.INVISIBLE);
        findViewById(R.id.tvResultUnit).setVisibility(View.INVISIBLE);
    }

    // ボタン押下処理
    private class CalculateListener implements View.OnClickListener{
        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View view){
            TextView YourDOD = findViewById(R.id.tvYourDegreeOfOdesite);
            TextView YourAW = findViewById(R.id.tvYourAppropriateWeight);
            TextView RUnit = findViewById(R.id.tvResultUnit);

            TextView DODResult = findViewById(R.id.tvDegreeOfOdesiteResult);
            TextView AWResult = findViewById(R.id.tvAppropriateWeightResult);

            String[] BMIArray = getResources().getStringArray(R.array.BMITable);
            String[] RohrerArray = getResources().getStringArray(R.array.RohrerTable);
            String[] KaupArray = getResources().getStringArray(R.array.KaupTable);

            EditText Age = findViewById(R.id.etAge);
            EditText Height = findViewById(R.id.etHeight);
            EditText Weight = findViewById(R.id.etWeight);

            TextView DODMethod = findViewById(R.id.DODMethod);

            // 非表示
            YourDOD.setVisibility(View.INVISIBLE);
            YourAW.setVisibility(View.INVISIBLE);
            RUnit.setVisibility(View.INVISIBLE);
            DODMethod.setVisibility(View.INVISIBLE);
            DODResult.setText("");
            AWResult.setText("");

            int id = view.getId();
            switch (id){
                case R.id.btCalculate:
                    // 未記入や数値以外があった場合にcatch
                    try {
                        // 身長・体重・年齢を取得
                        float fHeight = Float.parseFloat(Height.getText().toString()) / 100;
                        float fWeight = Float.parseFloat(Weight.getText().toString());
                        float fAge = Float.parseFloat(Age.getText().toString());

                        String ansColor = "black";

                        // 取得成功時に非表示解除
                        YourDOD.setVisibility(View.VISIBLE);
                        YourAW.setVisibility(View.VISIBLE);
                        RUnit.setVisibility(View.VISIBLE);
                        DODMethod.setVisibility(View.VISIBLE);

                        //年齢確認
                        if(fAge < 5){
                            // 警告
                            WarningDialog dialogFlagment = new WarningDialog();
                            dialogFlagment.show(getSupportFragmentManager(), "ConfirmDialog");

                            // 入力された値からKaupを計算
                            KaupCalculator kaupCal = new KaupCalculator();
                            kaupCal.setBodyScore(fHeight, fWeight, fAge);
                            DODMethod.setText("カウプ指数により計算");
                            // 肥満度の表示
                            DODResult.setText(KaupArray[kaupCal.getDOD()]);
                            ansColor = kaupCal.getColor();
                            // 適正体重を表示
                            AWResult.setText(String.format("%.1f", kaupCal.getStandardWeight()));
                        }else if(fAge < 16){
                            // 警告
                            WarningDialog dialogFlagment = new WarningDialog();
                            dialogFlagment.show(getSupportFragmentManager(), "ConfirmDialog");

                            // 入力された値からRohrerを計算
                            RohrerCalculator rohrerCal = new RohrerCalculator();
                            rohrerCal.setBodyScore(fHeight, fWeight);
                            DODMethod.setText("ローレル指数により計算");
                            // 肥満度の表示
                            DODResult.setText(RohrerArray[rohrerCal.getDOD()]);
                            ansColor = rohrerCal.getColor();
                            // 適正体重を表示
                            AWResult.setText(String.format("%.1f", rohrerCal.getStandardWeight()));
                        }else {
                            // 入力された値からBMIを計算
                            BMICalculator bmiCal = new BMICalculator();
                            bmiCal.setBodyScore(fHeight, fWeight);
                            DODMethod.setText("BMIにより計算");
                            // 肥満度の表示
                            DODResult.setText(BMIArray[bmiCal.getDOD()]);
                            ansColor = bmiCal.getColor();
                            // 適正体重を表示
                            AWResult.setText(String.format("%.1f", bmiCal.getStandardWeight()));
                        }

                        switch (ansColor){
                            case "red":
                                DODResult.setTextColor(getColor(R.color.red));
                                break;
                            case "black":
                                DODResult.setTextColor(getColor(R.color.black));
                                break;
                            case "blue":
                                DODResult.setTextColor(getColor(R.color.blue));
                                break;
                        }


                    }catch (Exception e){
                        // 計算に失敗した場合Toastで通知
                        Toast.makeText(MainActivity.this, "入力に不備があります\n全てのパラメータを入力してください", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.btClear:
                    Age.setText("");
                    Height.setText("");
                    Weight.setText("");
                    break;
            }
        }
    }
}