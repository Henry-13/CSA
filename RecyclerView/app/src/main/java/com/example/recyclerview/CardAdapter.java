package com.example.recyclerview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<Card> mCardList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cardImage;
        TextView cardName;

        public ViewHolder(View view){
            super(view);
            cardImage=(ImageView)view.findViewById(R.id.card_image);
            cardName=(TextView)view.findViewById(R.id.card_name);
        }
    }

    public CardAdapter(List<Card> cardList){
        mCardList=cardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Card card=mCardList.get(position);
        holder.cardImage.setImageResource(card.getImageId());
        holder.cardName.setText(card.getName());
    }

    @Override
    public int getItemCount(){
        return mCardList.size();
    }
}
