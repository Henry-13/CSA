package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Card> cardlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCards();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CardAdapter adapter=new CardAdapter(cardlist);
        recyclerView.setAdapter(adapter);
    }

    private void initCards(){
        for(int i=0;i<2;i++){
            Card bmw=new Card(getRandomLengthName("BMW"),R.drawable.bmw);
            cardlist.add(bmw);
            Card starbucks=new Card(getRandomLengthName("STARBUCKS"),R.drawable.starbucks);
            cardlist.add(starbucks);
            Card loisvuitton=new Card(getRandomLengthName("LOISVUITTON"),R.drawable.loisvuitton);
            cardlist.add(loisvuitton);
            Card guiic=new Card(getRandomLengthName("GUIIC"),R.drawable.guiic);
            cardlist.add(guiic);
            Card nokia=new Card(getRandomLengthName("NOKIA"),R.drawable.nokia);
            cardlist.add(nokia);
            Card porsche=new Card(getRandomLengthName("PORSCHE"),R.drawable.porsche);
            cardlist.add(porsche);
            Card underarmours=new Card(getRandomLengthName("UNDERARMOURS"),R.drawable.underarmours);
            cardlist.add(underarmours);
        }
    }

    private String getRandomLengthName(String name){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }
}