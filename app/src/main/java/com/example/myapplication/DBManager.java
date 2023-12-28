package com.example.myapplication;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class DBManager {
private static FirebaseAuth auth;

public static FirebaseAuth getAuth(){
    if(auth==null){
        auth=FirebaseAuth.getInstance();
    }
    return auth;
    }
}
