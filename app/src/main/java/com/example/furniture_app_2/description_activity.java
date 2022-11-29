package com.example.furniture_app_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class description_activity extends AppCompatActivity {

    TextView product_name, price, description;
    ImageView img_src;
    ImageButton back_arrow;
    Button minus;
    Button plus;
    TextView no_of_items;
    Button view_ar;
    String url_3d;
    Button add_to_cart;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    //String userId;
    String product_id;
    String cost;
    String img_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_activity);

        product_name = findViewById(R.id.product_name_2);
        price = findViewById(R.id.price_2);
        description = findViewById(R.id.description_content);
        img_src = findViewById(R.id.description_img);
        back_arrow = findViewById(R.id.back_arrow_0);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        view_ar = findViewById(R.id.view_AR);
        no_of_items = findViewById(R.id.no_of_items);
        add_to_cart = findViewById(R.id.add_to_cart);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            String name = extras.getString("name");
            product_name.setText(name);
            cost = extras.getString("cost");
            price.setText(cost);
            String description_content = extras.getString("description");
            description.setText(description_content);

            url_3d = extras.getString("url_3d");

            product_id = extras.getString("product_id");
            img_url=extras.getString("url");

            Glide.with(getApplicationContext()).load(extras.get("url")).into(img_src);
        }


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s =  no_of_items.getText().toString();
                if(!s.equals("1")){
                    int i = Integer.parseInt(s);
                    i--;
                    s=String.valueOf(i);
                    no_of_items.setText(s);
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s =  no_of_items.getText().toString();
                if(!s.equals("10")){
                    int i = Integer.parseInt(s);
                    i++;
                    s=String.valueOf(i);
                    no_of_items.setText(s);
                }
            }
        });
        view_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ar.class);
                i.putExtra("url_3d", url_3d);
                startActivity(i);
            }
        });

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_to_cart();
            }
        });
    }

    private void add_to_cart() {
        Integer coun = Integer.parseInt(no_of_items.getText().toString());

        String saveCurrTime, saveCurrDate;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        saveCurrDate = currentDate.format(calForDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrTime = currentTime.format(calForDate.getTime());
        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("product_id", product_id);
        cartMap.put("product_name", product_name.getText().toString());
        cartMap.put("product_cost", cost);
        cartMap.put("quantity", coun);
        cartMap.put("date", saveCurrDate);
        cartMap.put("time", saveCurrTime);
        String s = cost.substring(1);
        int product_price = Integer.parseInt(s);
        int totalPrice=product_price*coun;
        cartMap.put("total_price",totalPrice);
        cartMap.put("url",img_url);

        firebaseFirestore.collection("AddToCart")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("user").add(cartMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(description_activity.this, "Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Cart.class));
                    }
                });
    }
}