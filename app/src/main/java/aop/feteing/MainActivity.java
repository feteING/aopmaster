package aop.feteing;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

import aop.feteing.aop.CheckLogin;
import aop.feteing.aop.Permission;
import aop.feteing.aop.Safe;
import aop.feteing.aop.SingleClick;
import aop.feteing.aop.Trace;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CALENDAR;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("tag", "s");
        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag", "read");
                readFile(1000);
            }
        });
        findViewById(R.id.write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag", "write");
                writeFile(v);
            }
        });
        findViewById(R.id.safe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
        findViewById(R.id.no_double_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testClick(v);
            }
        });
        findViewById(R.id.no_double_click2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testClick2(v);
            }
        });
        findViewById(R.id.no_double_click3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testClick3(v);
            }
        });

        findViewById(R.id.no_double_click4).setOnClickListener(this);
        findViewById(R.id.no_double_click5).setOnClickListener(this);
        findViewById(R.id.no_double_click6).setOnClickListener(this);

        findViewById(R.id.check_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
        findViewById(R.id.check_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAppPermission();
            }
        });

    }

    @Permission(values = {READ_CONTACTS, WRITE_EXTERNAL_STORAGE, READ_CALENDAR, SEND_SMS, CALL_PHONE})
    private void checkAppPermission() {
        Toast.makeText(this, "checkpermission begin", Toast.LENGTH_SHORT).show();
    }

    @CheckLogin
    private void checkLogin() {
        Toast.makeText(this, "checklogin begin", Toast.LENGTH_SHORT).show();
    }

    int count = 0;
    int count2 = 0;
    int count3 = 0;
    int count4 = 0;
    int count5 = 0;
    int count6 = 0;

    @SingleClick
    private void testClick(View v) {
        String s = "hello";
        count++;
        Toast.makeText(this, s + "_" + count, Toast.LENGTH_SHORT).show();
    }

    @SingleClick
    private void testClick2(View v) {
        String s = "hello2";
        count2++;
        Toast.makeText(this, s + "_" + count2, Toast.LENGTH_SHORT).show();
    }

    @SingleClick
    private void testClick3(View v) {
        String s = "hello3";
        count3++;
        Toast.makeText(this, s + "_" + count3, Toast.LENGTH_SHORT).show();
    }

    @Safe
    private void initData() {

        String s = null;
        int length = s.length();
    }

    //trace名称，通过Trace的 value()方法获取
    @Trace("读文件")
    public int readFile(int a) {

//        long start = System.currentTimeMillis();

        //使用Random模仿方法运行时间
        SystemClock.sleep(Math.abs(new Random().nextInt(1000)));

//        long duration = System.currentTimeMillis() - start;
//        Log.i("trace", String.format("功能：%s,耗时：%d ms", "读文件", duration));
        return a++;
    }

    @Trace("写文件")
    public void writeFile(View view) {

//       long start = System.currentTimeMillis();

        //使用Random模仿方法运行时间
        SystemClock.sleep(Math.abs(new Random().nextInt(1000)));

//        long duration = System.currentTimeMillis() - start;
//        Log.i("trace", String.format("功能：%s,耗时：%d ms", "读文件", duration));
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.no_double_click4:
                String s4 = "hello4";
                count4++;
                Toast.makeText(this, s4 + "_" + count4, Toast.LENGTH_SHORT).show();
                break;
            case R.id.no_double_click5:
                String s5 = "hello5";
                count5++;
                Toast.makeText(this, s5 + "_" + count5, Toast.LENGTH_SHORT).show();
                break;
            case R.id.no_double_click6:
                String s6 = "hello6";
                count6++;
                Toast.makeText(this, s6 + "_" + count6, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }


    }
}
