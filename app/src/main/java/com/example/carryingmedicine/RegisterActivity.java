package com.example.carryingmedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_id,et_pass,et_name,et_age,et_home,et_phone,et_disease;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//액티비티 시작시 처음으로 실행되는 생명주기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_id=findViewById(R.id.et_id);
        et_pass=findViewById(R.id.et_pass);
        et_name=findViewById(R.id.et_name);
        et_home=findViewById(R.id.et_home);
        et_disease=findViewById(R.id.et_disease);
        et_age=findViewById(R.id.et_age);
        et_phone=findViewById(R.id.et_phone);

        btn_register=findViewById(R.id.btn_register);
        //회원가입 버튼 클릭시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edittext에 현재 입력되어 있는 값을 겟(가져온다)해온다.
                String userID=et_id.getText().toString();
                String userPass=et_pass.getText().toString();
                String userName=et_name.getText().toString();
                String userHome=et_home.getText().toString();
                String userDisease=et_disease.getText().toString();
                int userAge=Integer.parseInt(et_age.getText().toString());
                int userPhone=Integer.parseInt(et_phone.getText().toString());
                Response.Listener<String> responseListner=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //결과값을 json오브젝트로
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success=jsonObject.getBoolean("success");//php의 success
                            if(success){//성공했을때
                                Toast.makeText(getApplicationContext(), "원격진료를 위한 회원등록에 성공했어요!",Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            } else {//실패한경우
                                Toast.makeText(getApplicationContext(), "등록에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //실제적으로 사용되는 부분 서버로 volley를 이용해서 요청을 함
                RegisterRequest registerRequest = new RegisterRequest(userID,userPass,userName,userHome,userDisease,userAge,userPhone,responseListner);
                RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });

    }
}