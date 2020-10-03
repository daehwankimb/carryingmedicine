package com.example.carryingmedicine;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
 //서버 url 설정
    final static private String URL="http://daehwan6960.dothome.co.kr/Register.php";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPassword, String userName, String userHome, String userDisease, int userAge, int userPhone, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map=new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword", userPassword);
        map.put("userName",userName);
        map.put("userHome",userHome);
        map.put("userDisease",userDisease);
        map.put("userAge",userAge+"");
        map.put("userPhone",userPhone+"");


    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
