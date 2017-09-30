package com.bignerdranch.android.calendar3s.Login;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bignerdranch.android.calendar3s.MainActivity;
import com.bignerdranch.android.calendar3s.R;


public class LoginFragment extends Fragment implements MainActivity.onKeyBackPressedListener{
    private EditText userId, userPw;
    private Button loginButton;
    private Button startBtn;
    private CheckBox autoLogin;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private MainActivity main;

    public LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        //SM을 부모로부터 받아올것
        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        main = ((MainActivity)getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        userId = (EditText) v.findViewById(R.id.userId);
        userPw = (EditText) v.findViewById(R.id.userPw);
        autoLogin = (CheckBox) v.findViewById(R.id.autoLoginCheckBox);

        loginButton = (Button) v.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userId.getText().toString().trim();
                String pw = userPw.getText().toString().trim();

                startLogin(id,pw);

                if(autoLogin.isChecked()){
                    saveLoginInfo(id,pw,autoLogin.isChecked());
                }
            }
        });

        startBtn = (Button) v.findViewById(R.id.startRegisterBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment fragment = new SignUpFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.commit();

            }
        });


        //자동 로그인 실행
        preferences = main.getSharedPreferences("Pref", Activity.MODE_PRIVATE);
        if(preferences.getBoolean("check", false)){
            startLogin(preferences.getString("id",""), preferences.getString("pw",""));
        }

        return v;
    }

    private void startLogin(String id, String pw){
        if(!main.CheckUser("where Googleid = '" + id + "'")) {
            //서버에서 구글 아이디와 비밀번호를 확인하기
            // 파이어 베이스 로그인
            //id = "test0@hansung.ac.kr";
            //pw = "111111";
            main.SetFirebaseListener();
            main.signinFireBase(id, pw);
            Log.d("Login FB", "ID : " + main.getFBid());


            //DB에서 로그인한 유저로 데이터 가져오기
            main.initDB(id, pw);
            main.ChangeFragment(R.id.calendars);
        }
        else{
            Toast.makeText(getActivity(),"로그인 실패! 등록되지 않은 아이디와 비밀번호!",Toast.LENGTH_SHORT).show();
        }
    }

    private void saveLoginInfo(String id, String pw, boolean check){
        preferences = main.getSharedPreferences("Pref", Activity.MODE_PRIVATE);
        editor = preferences.edit();

        editor.putString("id",id);
        editor.putString("pw",pw);
        editor.putBoolean("check", check);

        editor.commit();
    }

    //메인의 백키를 실행시킨다.
    @Override
    public void onBack() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        activity.onBackPressed();
    }

    //자신의 백키를 불러오게 등록
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) context).setOnKeyBackPressedListener(this);
    }
}

