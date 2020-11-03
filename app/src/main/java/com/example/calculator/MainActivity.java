package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvkq, tvpt;
    Button btn1, btn0, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btncong, btntru, btnnhan, btnchia, btnphantram, btncham, btnbang, bntAC, btnxoa, btnnghichdao;
    double num1;
    double num2;
    String dl = "";
    char pt;
    final char cong = '+';
    final char tru = '-';
    final char nhan = '*';
    final char chia = '/';
    final char bang = '0';
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setControl();
        getSupportActionBar().hide();
        //setEvent();

    }

    //su kien chon button
    public void buttonClick(View view) {
        Button button = (Button) view;
        String s = button.getText().toString();
        switch (s) {
            case "AC":
                dl = "";
                tvpt.setText("");
                break;
            case "C":
                xoa();
                break;
            case "=":
                tinh();
                break;
            case "%":
                if (dl != "")
                    dl += "%";
                break;
            case "+/-":
                ngichdao();
                break;
            case "-":
                ktdauctru();
                break;
            case "+":
                ktdaucong();
                break;
            case "x":
                ktdaunhan();
                break;
            case "÷":
                ktdauchia();
                break;
            default:
                if (dl == null) {
                    dl = "";
                }
//                if (s.equals("÷") || s.equals("x")) {
//                    tinh();
//                }
                dl += s;
        }
        tvkq.setText(dl);
    }

    //KT phep tinh nhap vao...
    private void ktpheptinh() {
        if (dl.endsWith("+") == true) {
            dl = dl.subSequence(0, dl.length() - 2) + "-";
        }
        if (dl == null) {
            if (dl.equals("+") || dl.equals("*") || dl.equals("/")) {
                tvkq.setText("");
            }
        }
    }

    //Ham tinh toan cong, tru, nhan, chia, pham tram//
    private void tinh() {
        if (dl.split("x").length == 2) {                                //--------Nhan------//
            tvpt.setText(dl);
            String numbers[] = dl.split("x");
            double nhan = 0;
            /*tinh % nhan*/
            //nhan % 2 so a% * b%
            if (numbers[0].contains("%") == true && numbers[1].contains("%") == true) {
                String a[] = numbers[0].split("%");
                String b[] = numbers[1].split("%");
                try {
                    nhan = (Double.parseDouble(a[0]) / 100) * (Double.parseDouble(b[0]) / 100);
                } catch (Exception e) {
                }
            }
            //% so thu 1 a% * b
            else if (numbers[0].contains("%") == true) {
                String a[] = numbers[0].split("%");
                try {
                    nhan = Double.parseDouble(a[0]) / 100 * Double.parseDouble(numbers[1]);
                } catch (Exception e) {
                }
            }
            //% so thu 2 a * b% *
            else if (numbers[1].contains("%") == true) {
                String a[] = numbers[1].split("%");
                try {
                    nhan = Double.parseDouble(a[0]) / 100 * Double.parseDouble(numbers[0]);
                } catch (Exception e) {
                }
            }
            /**/
            // a * b
            else
                try {
                    nhan = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                } catch (Exception e) {
                }
            dl = nhan + "";
        } else if (dl.split("÷").length == 2) {                        //--------Chia-------//
            tvpt.setText(dl);
            String numbers[] = dl.split("÷");
            double chia = 0;
            /*tinh % chia*/
            //chia % 2 so a% * b%
            if (numbers[0].contains("%") == true && numbers[1].contains("%") == true) {
                String a[] = numbers[0].split("%");
                String b[] = numbers[1].split("%");
                try {
                    chia = (Double.parseDouble(a[0]) / 100) / (Double.parseDouble(b[0]) / 100);
                } catch (Exception e) {
                }
            }
            //so thu 1 a% / b
            else if (numbers[0].contains("%") == true) {
                String a[] = numbers[0].split("%");
                try {
                    chia = Double.parseDouble(a[0]) / 100 / Double.parseDouble(numbers[1]);
                } catch (Exception e) {
                }
            }
            //% so thu 2 a / b%
            else if (numbers[1].contains("%") == true) {
                String a[] = numbers[1].split("%");
                try {
                    chia = Double.parseDouble(numbers[0]) / (Double.parseDouble(a[0]) / 100);
                } catch (Exception e) {
                }
            }
            /**/
            // a / b
            else
                try {
                    chia = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);

                } catch (Exception e) {
                }
            dl = chia + "";
        } else if (dl.split("\\+").length == 2) {                      //--------Cong-------//
            tvpt.setText(dl);
            String numbers[] = dl.split("\\+");
            double cong = 0;
            /*tinh % cong*/
            //cong % 2 so a% + b%
            if (numbers[0].contains("%") == true && numbers[1].contains("%") == true) {
                String a[] = numbers[0].split("%");
                String b[] = numbers[1].split("%");
                try {
                    cong = (Double.parseDouble(a[0]) / 100) + (Double.parseDouble(b[0]) / 100);
                } catch (Exception e) {
                }
            }
            //% so thu 1 a% + b
            else if (numbers[0].contains("%") == true) {
                String a[] = numbers[0].split("%");
                try {
                    cong = Double.parseDouble(a[0]) / 100 + Double.parseDouble(numbers[1]);
                } catch (Exception e) {
                }
            }
            // so thu 2 a + b%
            else if (numbers[1].contains("%") == true) {
                String a[] = numbers[1].split("%");
                try {
                    cong = Double.parseDouble(numbers[0]) + Double.parseDouble(a[0]) / 100;
                } catch (Exception e) {
                }
            }
            /**/
            //a + b
            else
                try {
                    cong = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                } catch (Exception e) {
                }
            dl = cong + "";
        } else if (dl.split("\\-").length > 1) {                        //--------Tru-------//
            tvpt.setText(dl);
            String numbers[] = dl.split("\\-");
            double tru = 0;
//            if (numbers[0] == "") {
//                numbers[0] = 0 + "";
//            }
            try {
                if (numbers.length == 2) {
                    //tru % 2 so a% - b%
                    if (numbers[0].contains("%") == true && numbers[1].contains("%") == true) {
                        String a[] = numbers[0].split("%");
                        String b[] = numbers[1].split("%");
                        try {
                            tru = (Double.parseDouble(a[0]) / 100) - (Double.parseDouble(b[0]) / 100);
                            dl = tru + "";
                        } catch (Exception e) {
                        }
                    }
                    /*tinh % tru a% - b*/
                    else if (numbers[0].contains("%") == true) {
                        String a[] = numbers[0].split("%");
                        try {
                            tru = (Double.parseDouble(a[0]) / 100) - (Double.parseDouble(numbers[1]));
                            dl = tru + "";
                        } catch (Exception e) {
                        }
                    }
                    /*tinh % tru a - b% */
                    else if (numbers[1].contains("%") == true) {
                        String a[] = numbers[1].split("%");
                        try {
                            tru = Double.parseDouble(numbers[0]) - (Double.parseDouble(a[0]) / 100);
                            dl = tru + "";
                        } catch (Exception e) {
                        }
                    }
                    /**/
                    // a - b
                    tru = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                } else if (numbers.length == 3) {
                    //tru % 2 so -a% - b%
                    if (numbers[1].contains("%") == true && numbers[2].contains("%") == true) {
                        String a[] = numbers[1].split("%");
                        String b[] = numbers[2].split("%");
                        try {
                            tru = -(Double.parseDouble(a[0]) / 100) - (Double.parseDouble(b[0]) / 100);
                            dl = tru + "";
                        } catch (Exception e) {
                        }
                    }
                    /*tinh % tru -a% - b */
                    if (numbers[1].contains("%") == true) {
                        String a[] = numbers[1].split("%");
                        try {
                            tru = -Double.parseDouble(a[0]) / 100 - Double.parseDouble(numbers[2]);
                            dl = tru + "";
                        } catch (Exception e) {
                        }
                    }
                    /*tinh % tru -a - b% */
                    else if (numbers[2].contains("%") == true) {
                        String a[] = numbers[2].split("%");
                        try {
                            tru = -Double.parseDouble(numbers[1]) - Double.parseDouble(a[0]) / 100;
                            dl = tru + "";
                        } catch (Exception e) {
                        }
                    }
                    /**/
                    tru = -Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[2]);
                }
                dl = tru + "";
            } catch (Exception e) {
            }
        }
        tinhPT();
        loaibo();
        tvkq.setText(dl);
    }

    //Tinh phan tram a%b
    private void tinhPT() {
        if (dl.split("%").length == 2) {
            String numbers[] = dl.split("%");
            double pt = 0;
            try {
                pt = Double.parseDouble(numbers[0]) / 100 * Double.parseDouble(numbers[1]);
                dl = pt + "";
            } catch (Exception e) {
            }
        }
        tvkq.setText(dl);
    }

    //Loai bo .0 trong kieu double
    private void loaibo() {
        String n[] = dl.split("\\.");
        if (n.length > 1) {
            if (n[1].equals("0")) {
                dl = n[0];
            }
        }
        tvkq.setText(dl);
    }

    //Tinh nghich dao
    private void ngichdao() {
        double nd = 0;
        if (dl.length() > 0) {
            nd = Double.parseDouble(dl) * -1;
            dl = nd + "";
            loaibo();
            tvkq.setText(dl);
        }
    }

    //kiem tra doi phep tinh
    private void ktdauchia(){
        if (dl.startsWith("-")&& dl.length() < 2)
            dl = dl.replace("-", "");
        else if (dl.endsWith("-"))
            dl = dl.substring(0, dl.length()-1) + "÷";
        else if (dl.endsWith("+"))
            dl = dl.replace("+", "÷");
        else if (dl.endsWith("x"))
            dl = dl.replace("x", "÷");
        else if(dl != ""){
            tinh();
            dl += "÷";
        }
    }

    private void ktdaunhan(){
        if (dl.startsWith("-")&& dl.length() < 2)
            dl = dl.replace("-", "");
        else if (dl.endsWith("-"))
            dl = dl.substring(0, dl.length()-1) + "x";
        else if (dl.endsWith("+"))
            dl = dl.replace("+", "x");
        else if (dl.endsWith("÷"))
            dl = dl.replace("÷", "x");
        else if(dl != ""){
            tinh();
            dl += "x";
        }
    }

    private void ktdaucong() {
        if (dl.startsWith("-") && dl.length() < 2)
            dl = dl.replace("-", "");
        else if (dl.endsWith("-"))
            dl = dl.substring(0, dl.length()-1) + "+";
        else if (dl.endsWith("x"))
            dl = dl.replace("x", "+");
        else if (dl.endsWith("÷"))
            dl = dl.replace("÷", "+");
        else if(dl != ""){
            tinh();
            dl += "+";
        }
    }

    private void ktdauctru(){
        if (dl.endsWith("+"))
            dl = dl.replace("+", "-");
        else if (dl.endsWith("x"))
            dl = dl.replace("x", "-");
        else if (dl.endsWith("÷"))
            dl = dl.replace("÷", "-");
        else {
            tinh();
            dl += "-";
        }
    }

    private void xoa(){
        if (dl.length() > 0) {
            // tvkq.setText(tvkq.getText().subSequence(0, tvkq.length() - 1));// lay chuoi
            String del = dl.substring(0, dl.length() - 1); //lay chuoi con
            dl = del;
        }
    }

    private void setControl() {
        tvkq = findViewById(R.id.tvketqua);
        tvpt = findViewById(R.id.tvpheptoan);
        btn0 = findViewById(R.id.btnkhong);
        btn1 = findViewById(R.id.btnmot);
        btn2 = findViewById(R.id.btnhai);
        btn3 = findViewById(R.id.btnba);
        btn4 = findViewById(R.id.btnbon);
        btn5 = findViewById(R.id.btnnam);
        btn6 = findViewById(R.id.btnsau);
        btn7 = findViewById(R.id.btnbay);
        btn8 = findViewById(R.id.btntam);
        btn9 = findViewById(R.id.btnchin);
        btnbang = findViewById(R.id.btnbang);
        btncong = findViewById(R.id.btncong);
        btntru = findViewById(R.id.btntru);
        btnnhan = findViewById(R.id.btnnhan);
        btnchia = findViewById(R.id.btnchia);
        btnphantram = findViewById(R.id.btnphantram);
        btncham = findViewById(R.id.btncham);
        bntAC = findViewById(R.id.btnac);
        btnxoa = findViewById(R.id.btndelete);
        btnnghichdao = findViewById(R.id.btnnghicdao);
    }

    private void setEvent() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.mot));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.hai));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.ba));
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.bon));
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.nam));
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.sau));
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.bay));
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.tam));
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.chin));
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.khong));
            }
        });
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pt = cong;
//                tinhtoan();
                tvkq.setText(String.valueOf(num1) + getString(R.string.cong));

//                if(s.split("\\+").length == 2){
//                    String num[] = s.split("\\+");
//                    double c = Double.parseDouble(num[0]) + Double.parseDouble(num[1]);
//                    s = c + "";
//                }
//                tvkq.setText(s);
            }
        });
        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.tru));
            }
        });
        btnnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                phepToan();
                tvkq.setText(tvkq.getText() + getString(R.string.nhan));


            }
        });
        btnchia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.chia));
            }
        });
        btncham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvkq.length() == 0) {
                    tvkq.setText(tvkq.getText() + "0.");
                } else
                    tvkq.setText(tvkq.getText() + getString(R.string.cham));
            }
        });
        bntAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText("");
                tvpt.setText("");
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvkq.getText() != null && tvkq.length() > 0) {
                    tvkq.setText(tvkq.getText().subSequence(0, tvkq.length() - 1));
                }
            }
        });
        btnphantram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkq.setText(tvkq.getText() + getString(R.string.phantram));
            }
        });

        btnbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pt = bang;
//                tinhtoan();
                tvkq.setText(tvkq.getText().toString() + String.valueOf(num2) + "=" + String.valueOf(num1));

                //tvpt.setText(tvkq.getText());
            }
        });

    }

//    private void phepToan() {
//        s = tvkq.getText().toString();
//        if (s.split("\\*").length == 2) {
//            String num[]=s.split("\\*");
//            double nhan = Double.parseDouble(num[0])*Double.parseDouble(num[1]);
//            s = nhan + "";
//        }
//        tvpt.setText(s);
//
//    }

//    public void tinhtoan()
//    {
//        if(!Double.isNaN(num1)){
//            num2 =  Double.parseDouble(tvkq.getText().toString());
//
//            switch (pt){
//                case cong:
//                    num1 = num1 + num2;
//                    break;
//                case tru:
//                    num1 = num1 - num2;
//                    break;
//                case nhan:
//                    num1 = num1 * num2;
//                    break;
//                case chia:
//                    num1 = num1 / num2;
//                    break;
//                case bang:
//                    break;
//
//            }
//
//        }else{
//            num1 = Double.parseDouble(tvkq.getText().toString());
//        }
//    }


}
