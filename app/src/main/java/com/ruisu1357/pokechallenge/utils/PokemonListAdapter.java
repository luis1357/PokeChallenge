package com.ruisu1357.pokechallenge.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

import com.ruisu1357.pokechallenge.R;
import com.ruisu1357.pokechallenge.model.general.NameUrl;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private static final String TAG = "POKE";
    private final ItemClickListener mOnClickListener;

    private Context context;

    private ArrayList<NameUrl> pokeList;

    public PokemonListAdapter(Context context, ItemClickListener mOnClickListener) {
        this.context = context;
        this.mOnClickListener = mOnClickListener;
        pokeList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pokemon, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        NameUrl pokemon = pokeList.get(i);
        viewHolder.pokeName.setText(pokemon.getName());

        Glide.with(context).load(Constants.SPRITES_URL + pokemon.getNumber() +".png")
        .centerCrop()
        .transition(new DrawableTransitionOptions()
                .crossFade())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(viewHolder.pokeImg);

        Log.i(TAG, "Total of Pokemon: " + pokemon.getName() + pokemon.getNumber());
    }

    @Override
    public int getItemCount() {
        return pokeList.size();
    }

    public void addPokemonList(ArrayList<NameUrl> pokemonList) {
        pokeList.addAll(pokemonList);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView pokeImg;
        private TextView pokeName;

        public ViewHolder(View itemView){
            super(itemView);

            pokeImg = itemView.findViewById(R.id.pokeImg);
            pokeName = itemView.findViewById(R.id.pokeName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onItemClick(clickedPosition);
        }
    }

    public interface ItemClickListener{
        void onItemClick(int clickedItemIndex);
    }
}
