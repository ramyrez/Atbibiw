package com.dto.quizcov;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dto.quizcov.activities.QuizDashboardActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_five);
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.CALL_PHONE,
                Manifest.permission.INTERNET
        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        int res=getIntent().getIntExtra("res",0);
    //    Toast.makeText(this,res+"",Toast.LENGTH_LONG).show();
        findViewById(R.id.cov).setVisibility(View.GONE);

        if(res<2)findViewById(R.id.cov).setVisibility(View.GONE);
        else  if(res==2){
            // new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                    .setTitleText("النتيجة")
                    .setContentText("على حسب الأعراض التي ابديتها، ننصح بمهاتفة 3030 من أجل معلومات أكثر.")
                    .setCustomImage(R.drawable.ic_action_call)
                    .setConfirmText("الاتصال")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            call("3030");
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .setCancelButton("تجاهل", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();

            findViewById(R.id.lang).setVisibility(View.GONE);
            findViewById(R.id.call).setVisibility(View.GONE);
        }
        else  if(res==3 || res==4){
            // new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                    .setTitleText("النتيجة")
                    .setContentText("إن الأعراض المسجلة، قد تكون أعراض الكوفيد.. يرجى التوجه إلى أقرب مركز صحي لفحص نوعية الزكام.")
                    .setCustomImage(R.drawable.ic_action_go)
                  .setConfirmText("تجاهل")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                          //  call("3030");
                            sDialog.dismissWithAnimation();
                        }
                    })
                 /*   .setCancelButton("تجاهل", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })*/
                    .show();
            findViewById(R.id.call).setVisibility(View.GONE);}
        else {
            // new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                    .setTitleText("النتيجة")
                    .setContentText("حالتك متقدمة، يرجى مكالمة الحماية المدنية فورا، من أجل نقلك إلى المستشفى")
                    .setCustomImage(R.drawable.ic_action_po)
                    .setConfirmText("الاتصال")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            call("1548");
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .setCancelButton("تجاهل", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();
        }

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("3030");
            }
        });
        findViewById(R.id.lang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call("3030");
            }
        });
        findViewById(R.id.call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("1548");
            }
        });

        ///////////////////////////////////////////////////////////////
        findViewById(R.id.follow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("3030");
            }
        });
        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("1714");
            }
        });
        findViewById(R.id.rating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.developer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openurl("http://covid19.sante.gov.dz/carte/");
            }
        });
        findViewById(R.id.policis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, QuizDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    private void openurl(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @SuppressLint("MissingPermission")
    private void call(String s) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + s));
        startActivity(callIntent);
    }

    private void sendemaill(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}
