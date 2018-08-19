package in.tecknic.nehal.salary;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText basic_salary, grade_pay, da, hra, other;
    private EditText professional_tax, provident_fund, tds, deduction_other;

    private double calculated_salary = 0;
    private TextView total_salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basic_salary = findViewById(R.id.basic_salary);
        grade_pay = findViewById(R.id.grade_pay);
        da = findViewById(R.id.da);
        hra = findViewById(R.id.hra);
        other = findViewById(R.id.other);

        professional_tax = findViewById(R.id.professional_tax);
        provident_fund = findViewById(R.id.provident_fund);
        tds = findViewById(R.id.tds);
        deduction_other = findViewById(R.id.deduction_other);

        total_salary = findViewById(R.id.total_salary);


        final Button btn_salary_calculate = findViewById(R.id.calculate_salary);
        btn_salary_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    //calculate salary
                    calculated_salary = convert_data(basic_salary) + convert_data(grade_pay);// + convert_data(da) + convert_data(hra) + convert_data(other) ;

                    double dbl_da = calculated_salary * convert_data(da) / 100;
                    double dbl_hra = calculated_salary * convert_data(hra) / 100;

                    calculated_salary += dbl_da;
                    calculated_salary += dbl_hra;
                    calculated_salary += convert_data(other);

                    //deduction from salary
                    calculated_salary -= convert_data(professional_tax);
                    calculated_salary -= convert_data(provident_fund);
                    calculated_salary -= convert_data(tds);
                    calculated_salary -= convert_data(deduction_other);

                    //calculated_salary -= convert_data(professional_tax) + convert_data(provident_fund) + convert_data(tds) + convert_data(deduction_other) ;
                    //Toast.makeText( MainActivity.this , String.valueOf(calculated_salary) , Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText( MainActivity.this , e.getMessage() , Toast.LENGTH_LONG).show();
                }

                total_salary.setText(String.valueOf(calculated_salary));
                //Toast.makeText( MainActivity.this , btn_salary_calculate.toString() , Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_about){
            startActivity(new Intent(this,  SettingsActivity.class ));
        }

        return super.onOptionsItemSelected(item);
    }

    protected double convert_data(EditText input){

        String data = input.getText().toString();
        if(data == null || data.equals(""))
            return 0;

        return Double.parseDouble(data);
    }
}
