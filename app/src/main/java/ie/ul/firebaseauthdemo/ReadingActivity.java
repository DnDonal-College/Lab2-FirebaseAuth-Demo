package ie.ul.firebaseauthdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class ReadingActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "ie.ul.myfirstapp.EXTRA_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference Posts = db.collection("Posts");
        db.collection("Posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int i = 0;
                        if (task.isSuccessful()) {
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            CollectionReference Posts = db.collection("Posts");
                            TextView post1 = (TextView) findViewById(R.id.post1);
                            TextView post2 = (TextView) findViewById(R.id.post2);
                            TextView post3 = (TextView) findViewById(R.id.post3);
                            TextView post4 = (TextView) findViewById(R.id.post4);
                            TextView post5 = (TextView) findViewById(R.id.post5);
                            Map<String, Object> dbPost = new HashMap<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(i == 0){
                                    dbPost = document.getData();
                                    post1.setText(dbPost.toString());
                                }
                                if(i == 1){
                                    dbPost = document.getData();
                                    post2.setText(dbPost.toString());
                                }
                                if(i == 2){
                                    dbPost = document.getData();
                                    post3.setText(dbPost.toString());
                                }
                                if(i == 3){
                                    dbPost = document.getData();
                                    post4.setText(dbPost.toString());
                                }
                                if(i == 4){
                                    dbPost = document.getData();
                                    post5.setText(dbPost.toString());
                                }
                                i++;
                            }
                        }
                    }
                });
    }

    public void onClickPost (View view){
        Intent intentPost = new Intent(this, PostActivity.class);
        startActivity(intentPost);
    }

    public void onClickRefresh (View view){
        this.recreate();
    }
}