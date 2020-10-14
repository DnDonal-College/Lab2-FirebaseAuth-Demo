package ie.ul.firebaseauthdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

import javax.annotation.OverridingMethodsMustInvokeSuper;


public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void onClickSubmit(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference Posts = db.collection("Posts");
        Map<String, Object> post5 = new HashMap<>();

        db.collection("Posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int i = 0;
                        if (task.isSuccessful()) {
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            CollectionReference Posts = db.collection("Posts");
                            Map<String, Object> post = new HashMap<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(i == 0){//ignores oldest
                                }
                                if(i == 1){//sets post 1 to 2
                                    post = document.getData();
                                    Posts.document("post1").set(post);
                                }
                                if(i == 2){// sets post 2 to 3
                                    post = document.getData();
                                    Posts.document("post2").set(post);
                                }
                                if(i == 3){// sets post 3 to 4
                                    post = document.getData();
                                    Posts.document("post3").set(post);
                                }
                                if(i == 4){// sets post 4 to 5
                                    post = document.getData();
                                    Posts.document("post4").set(post);
                                }
                                i++;
                            }
                            EditText editText = (EditText) findViewById(R.id.userPost);
                            String message = editText.getText().toString();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String name = user.getDisplayName();
                            post.put("user", name);
                            post.put("posted", message);
                            Posts.document("post5").set(post);
                        }
                    }
                });
        this.finish();
    }
}