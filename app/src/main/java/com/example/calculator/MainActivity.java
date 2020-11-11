package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvkq, tvpt;
    Button btn1, btn0, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btncong, btntru, btnnhan, btnchia, btnphantram, btncham, btnbang, bntAC, btnxoa, btnnghichdao;
    String data = "";
    //public static final String TAG = MainActivity.class.getSimpleName();

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
                data = "";
                tvpt.setText("");
                break;
            case "C":
                delete();
                break;
            case "=":
                operation();
                percent();
                break;
            case "%":
                checkpercent();
                operation();
//                if (data != "")
//                    data += "%";
                break;
            case "+/-":
                inverse();
                break;
            case "-":
                checkMSub();
                break;
            case "+":
                checkSum();
                break;
            case "x":
                checkMul();
                break;
            case "÷":
                checkDiv();
                break;
            case ".":
//                if(data.contains("."))
//                    return;
                checkDot();

                break;
            default:// Cac button con lai
                if (data == null) {
                    data = "";
                }
                if (data.endsWith("%")) // kiem tra nhap a%b%
                    data += "x" + s;
                else
                    data += s;
        }
        tvkq.setText(data);
    }

    //Ham tinh toan cong, tru, nhan, chia, pham tram//
    private void operation() {
        if (data.split("x").length == 2) {                                //--------Nhan------//
            tvpt.setText(data);
            String numbers[] = data.split("x");
            double multiplication = 0;
            /*tinh % nhan*/
            //nhan % 2 so a% * b%
            if (numbers[0].contains("%") && numbers[1].contains("%")) {
                String a[] = numbers[0].split("%");
                String b[] = numbers[1].split("%");
                try {
                    multiplication = (Double.parseDouble(a[0]) / 100) * (Double.parseDouble(b[0]) / 100);
                } catch (Exception e) {
                }
            }
            //% so thu 1 a% * b
            else if (numbers[0].contains("%")) {
                String a[] = numbers[0].split("%");
                try {
                    multiplication = Double.parseDouble(a[0]) / 100 * Double.parseDouble(numbers[1]);
                } catch (Exception e) {
                }
            }
            //% so thu 2 a * b% *
            else if (numbers[1].contains("%")) {
                String a[] = numbers[1].split("%");
                try {
                    multiplication = Double.parseDouble(a[0]) / 100 * Double.parseDouble(numbers[0]);
                } catch (Exception e) {
                }
            }
            /**/
            // a * b
            else
                try {
                    multiplication = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                } catch (Exception e) {
                }
            data = multiplication + "";
        } else if (data.split("÷").length == 2) {                        //--------Chia-------//
            tvpt.setText(data);
            String numbers[] = data.split("÷");
            double division = 0;
            /*tinh % chia*/
            //chia % 2 so a% * b%
            if (numbers[0].contains("%") && numbers[1].contains("%")) {
                String a[] = numbers[0].split("%");
                String b[] = numbers[1].split("%");
                try {
                    division = (Double.parseDouble(a[0]) / 100) / (Double.parseDouble(b[0]) / 100);
                } catch (Exception e) {
                }
            }
            //so thu 1 a% / b
            else if (numbers[0].contains("%")) {
                String a[] = numbers[0].split("%");
                try {
                    division = Double.parseDouble(a[0]) / 100 / Double.parseDouble(numbers[1]);
                } catch (Exception e) {
                }
            }
            //% so thu 2 a / b%
            else if (numbers[1].contains("%")) {
                String a[] = numbers[1].split("%");
                try {
                    division = Double.parseDouble(numbers[0]) / (Double.parseDouble(a[0]) / 100);
                } catch (Exception e) {
                }
            }
            /**/
            // a / b
            else
                try {
                    division = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);

                } catch (Exception e) {
                }
            data = division + "";
        } else if (data.split("\\+").length == 2) {                      //--------Cong-------//
            tvpt.setText(data);
            String numbers[] = data.split("\\+");
            double summation = 0;
            /*tinh % cong*/
            //cong % 2 so a% + b%
            if (numbers[0].contains("%") && numbers[1].contains("%")) {
                String a[] = numbers[0].split("%");
                String b[] = numbers[1].split("%");
                try {
                    summation = (Double.parseDouble(a[0]) / 100) + (Double.parseDouble(b[0]) / 100);
                } catch (Exception e) {
                }
            }
            //% so thu 1 a% + b
            else if (numbers[0].contains("%")) {
                String a[] = numbers[0].split("%");
                try {
                    summation = Double.parseDouble(a[0]) / 100 + Double.parseDouble(numbers[1]);
                } catch (Exception e) {
                }
            }
            // so thu 2 a + b%
            else if (numbers[1].contains("%")) {
                String a[] = numbers[1].split("%");
                try {
                    summation = Double.parseDouble(numbers[0]) + Double.parseDouble(a[0]) / 100;
                } catch (Exception e) {
                }
            }
            /**/
            //a + b
            else
                try {
                    summation = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                } catch (Exception e) {
                }
            data = summation
                    + "";
        } else if (data.split("-").length > 1) {                        //--------Tru-------//
            tvpt.setText(data);
            String numbers[] = data.split("-");
            double subtraction = 0;
//            if (numbers[0] == "") {
//                numbers[0] = 0 + "";
//            }
            try {
                if (numbers.length == 2) {
                    //tru % 2 so a% - b%
                    if (numbers[0].contains("%") && numbers[1].contains("%")) {
                        String a[] = numbers[0].split("%");
                        String b[] = numbers[1].split("%");
                        try {
                            subtraction = (Double.parseDouble(a[0]) / 100) - (Double.parseDouble(b[0]) / 100);
                            data = subtraction + "";
                        } catch (Exception e) {
                        }
                    }
                    /*tinh % tru a% - b*/
                    else if (numbers[0].contains("%")) {
                        String a[] = numbers[0].split("%");
                        try {
                            subtraction = (Double.parseDouble(a[0]) / 100) - (Double.parseDouble(numbers[1]));
                            data = subtraction + "";
                        } catch (Exception e) {
                        }
                    }
                    /*tinh % tru a - b% */
                    else if (numbers[1].contains("%")) {
                        String a[] = numbers[1].split("%");
                        try {
                            subtraction = Double.parseDouble(numbers[0]) - (Double.parseDouble(a[0]) / 100);
                            data = subtraction + "";
                        } catch (Exception e) {
                        }
                    }
                    /**/
                    // a - b
                    subtraction = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                } else if (numbers.length == 3) {
                    //tru % 2 so -a% - b%
                    if (numbers[1].contains("%") && numbers[2].contains("%")) {
                        String a[] = numbers[1].split("%");
                        String b[] = numbers[2].split("%");
                        try {
                            subtraction = -(Double.parseDouble(a[0]) / 100) - (Double.parseDouble(b[0]) / 100);
                            data = subtraction + "";
                        } catch (Exception e) {
                        }
                    }
                    /*tinh % tru -a% - b */
                    if (numbers[1].contains("%")) {
                        String a[] = numbers[1].split("%");
                        try {
                            subtraction = -Double.parseDouble(a[0]) / 100 - Double.parseDouble(numbers[2]);
                            data = subtraction + "";
                        } catch (Exception e) {
                        }
                    }
                    /*tinh % tru -a - b% */
                    else if (numbers[2].contains("%")) {
                        String a[] = numbers[2].split("%");
                        try {
                            subtraction = -Double.parseDouble(numbers[1]) - Double.parseDouble(a[0]) / 100;
                            data = subtraction + "";
                        } catch (Exception e) {
                        }
                    }
                    /**/
                    subtraction = -Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[2]);
                }
                data = subtraction + "";
            } catch (Exception e) {
            }
        }
        removeZero();
        tvkq.setText(data);
    }

    //Tinh phan tram
    private void percent() {
        double per = 0;
        //a%b
        if (data.split("%").length == 2) {
            String numbers[] = data.split("%");
            try {
                per = Double.parseDouble(numbers[0]) / 100 * Double.parseDouble(numbers[1]);
                data = per + "";
            } catch (Exception e) {
            }
        }
        //a%
        else if (data.endsWith("%") && data.split("%").length == 1) {
            String[] numbers = data.split("%");
            try {
                per = Double.parseDouble(numbers[0]) / 100;
                data = per + "";
            } catch (Exception e) {
            }
        }
        tvkq.setText(data);
    }

    //Tinh nghich dao
    private void inverse() {
        double nd = 0;
        String numbers[];
        if (data.length() > 0) {
            if (data.endsWith("%") || data.endsWith("+") || data.endsWith("-") || data.endsWith("x")
                    || data.endsWith("÷") || (data.startsWith(".") && data.length() == 1) || (data.startsWith("-") && data.length() == 1))
                return;
            else if (data.split("x").length == 2) {  // nghich dao b
                numbers = data.split("x");
                nd = Double.parseDouble(numbers[1]) * -1;
                String a = nd + "";
                String newnd[] = a.split("\\.");
                if (newnd[1].equals("0"))
                    data = numbers[0] + "x" + newnd[0];
                else if (!newnd[1].equals("0"))
                    data = numbers[0] + "x" + a;
            } else if (data.split("\\+").length == 2) {
                numbers = data.split("\\+");
                nd = Double.parseDouble(numbers[1]) * -1;
                String a = nd + "";
                String newnd[] = a.split("\\.");
                if (newnd[1].equals("0"))
                    data = numbers[0] + "+" + newnd[0];
                else if (!newnd[1].equals("0"))
                    data = numbers[0] + "+" + a;
            } else if (data.split("÷").length == 2) {
                numbers = data.split("÷");
                nd = Double.parseDouble(numbers[1]) * -1;
                String a = nd + "";
                String newnd[] = a.split("\\.");
                if (newnd[1].equals("0"))
                    data = numbers[0] + "÷" + newnd[0];
                else if (!newnd[1].equals("0"))
                    data = numbers[0] + "÷" + a;
            } else if (data.split("-").length == 3) { //-a--b = -a+b
                numbers = data.split("-");
                data = "-" + numbers[1] + "+" + numbers[2];
            } else if (data.split("-").length == 2 && !data.startsWith("-")) { // a--b = a+b
                numbers = data.split("-");
                data = numbers[0] + "+" + numbers[1];
            } else {
                nd = Double.parseDouble(data) * -1;
                data = nd + "";
            }
            removeZero();
        }
    }

    /*
     * kiem tra doi phep tinh (4function)
     * Khong nhap dau +*÷% và dau phay truoc
     * replace thay the tat ca(so am 2 dau tru)
     * khong nhap dau lien tiep
     */
    private void checkDiv() {
        if (data.startsWith("-") && data.length() < 2)
            data = data.replace("-", "");
        else if (data.endsWith("-"))
            data = data.substring(0, data.length() - 1) + "÷";
        else if (data.endsWith("+"))
            data = data.replace("+", "÷");
        else if (data.endsWith("x"))
            data = data.replace("x", "÷");
        else if (data.endsWith("÷"))
            return;
        else if (data.endsWith(".") && data.length() > 1)
            data = data.substring(0, data.length() - 1) + "÷";
        else if (data != "") {
            operation();
            data += "÷";
        }
    }

    private void checkMul() {
        if (data.startsWith("-") && data.length() < 2)
            data = data.replace("-", "");
        else if (data.endsWith("-"))
            data = data.substring(0, data.length() - 1) + "x";
        else if (data.endsWith("+"))
            data = data.replace("+", "x");
        else if (data.endsWith("÷"))
            data = data.replace("÷", "x");
        else if (data.endsWith("x"))
            return;
        else if (data.endsWith(".") && data.length() > 1)
            data = data.substring(0, data.length() - 1) + "x";
        else if (data != "") {
            operation();
            data += "x";
        }
    }

    private void checkSum() {
        if (data.startsWith("-") && data.length() < 2)      // Khong nhap dau +*/ truoc
            data = data.replace("-", "");
        else if (data.endsWith("-"))                        // replace thay the tat ca(so am 2 dau tru)
            data = data.substring(0, data.length() - 1) + "+";
        else if (data.endsWith("x"))
            data = data.replace("x", "+");
        else if (data.endsWith("÷"))
            data = data.replace("÷", "+");
        else if (data.endsWith("+"))
            return;
        else if (data.endsWith(".") && data.length() > 1)
            data = data.substring(0, data.length() - 1) + "+";
        else if (data != "") {
            operation();
            data += "+";
        }
    }

    private void checkMSub() {
        if (data.endsWith("+"))
            data = data.replace("+", "-");
        else if (data.endsWith("x"))
            data = data.replace("x", "-");
        else if (data.endsWith("÷"))
            data = data.replace("÷", "-");
        else if (data.endsWith("-"))
            return;
        else if (data.endsWith(".") && data.length() > 1)
            data = data.substring(0, data.length() - 1) + "-";
        else {
            operation();
            data += "-";
        }
    }

    //Kiem tra dau phay
    private void checkDot() {
        String numbers[];
        if (data.contains(".") && !data.contains("x") && !data.contains("÷") && !data.contains("+") && !data.contains("-")) //Kiem tra a
            return;
        else if (data.split("x").length == 2) {
            numbers = data.split("x");
            if (numbers[1].contains(".")) {
                return;
            }
        } else if (data.split("÷").length == 2) {
            numbers = data.split("÷");
            if (numbers[1].contains(".")) {
                return;
            }
        } else if (data.split("\\+").length == 2) {
            numbers = data.split("\\+");
            if (numbers[1].contains(".")) {
                return;
            }
        } else if (data.split("-").length == 2) {
            numbers = data.split("-");
            if (numbers[1].contains(".")) {
                return;
            }
        } else if (data.split("-").length == 3) {
            numbers = data.split("-");
            if (numbers[2].contains(".")) {
                return;
            }
        }

        if (data.length() < 1 || data.endsWith(".") || data.endsWith("x") || data.endsWith("÷") || data.endsWith("-") || data.endsWith("+"))
            return;
        else {
//            operation();
            data += ".";
        }
    }

    //Kiem tra phan tram
    private void checkpercent() {
        if (data.length() < 1 || data.endsWith("x") || data.endsWith("÷") || data.endsWith("-") || data.endsWith("+") || data.endsWith("%"))
            return;
        else if (data.endsWith(".") && data.length() > 1)
            data = data.substring(0, data.length() - 1) + "%";
        else
            data += "%";
    }

    //Xoa 1 ky tu
    private void delete() {
        if (data.length() > 0) {
            // tvkq.setText(tvkq.getText().subSequence(0, tvkq.length() - 1));// lay chuoi
            String del = data.substring(0, data.length() - 1); //lay chuoi con
            data = del;
        }
    }

    //Loai bo .0 trong kieu double
    private void removeZero() {
        String n[] = data.split("\\.");
        if (n.length > 1) {
            if (n[1].equals("0")) {
                data = n[0];
            }
            else
                data = n[0] + "." + n[1].substring(0,8);
        }
        tvkq.setText(data);
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

}
