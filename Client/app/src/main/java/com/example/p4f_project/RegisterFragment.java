package com.example.p4f_project;

import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFragment extends Fragment {
    EditText reg_username;
    EditText reg_password;
    EditText phone;
    EditText address;
    EditText fullName;
    Button regButton;
    float v=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.register_tab_fragment, container, false);

        reg_username= root.findViewById(R.id.reg_username);
        reg_password= root.findViewById(R.id.reg_password);
        fullName=root.findViewById(R.id.fullname);
        phone= root.findViewById(R.id.phone);
        address= root.findViewById(R.id.address);
        regButton= root.findViewById(R.id.regButton);


        reg_username.setTranslationX(0);
        reg_password.setTranslationX(0);
        fullName.setTranslationX(0);
        phone.setTranslationX(0);
        address.setTranslationX(0);
        regButton.setTranslationX(0);



        reg_username.setAlpha(v);
        reg_password.setAlpha(v);
        fullName.setAlpha(v);
        phone.setAlpha(v);
        address.setAlpha(v);
        regButton.setAlpha(v);


        reg_username.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        reg_password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        fullName.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        phone.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(900).start();
        address.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1100).start();
        regButton.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1200).start();

        return root;

    }
    public boolean checkString(String userName){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]]");
        Matcher matcher= pattern.matcher(userName);
        boolean isSpecial=matcher.find();
        return isSpecial;
    }
    public boolean validatePassword(String userName, String passWord, String Phone, String Address,String fName){
        // Case blank field(s)
        if(userName.equals("")||passWord.equals("")||Phone.equals("")|| Address.equals("")|| fName.equals("")){
            return false;
        }
        // Case password length too long
        if(passWord.length()>20){
            return false;
        }
        if(checkString(userName)){
            return false;
        }
        if(userName.length()<5){
            return false;
        }
        if(passWord.length()<8){
            return false;
        }
        return true;
    }


    public String returnAccount(){
        String uName=reg_username.getText().toString();
        String uPass=reg_password.getText().toString();
        String uPhone=phone.getText().toString();
        String uAddress=address.getText().toString();
        String uFullName=fullName.getText().toString();
        if(validatePassword(uName,uPass,uPhone,uAddress,uFullName)==false){
            return null;
        }
        return uName+":"+uPass+":"+uPhone+":"+uAddress+":"+uFullName;
    }


}
