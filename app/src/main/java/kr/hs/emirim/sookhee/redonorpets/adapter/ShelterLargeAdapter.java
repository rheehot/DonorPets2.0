package kr.hs.emirim.sookhee.redonorpets.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import kr.hs.emirim.sookhee.redonorpets.R;
import kr.hs.emirim.sookhee.redonorpets.model.ShelterData;
import kr.hs.emirim.sookhee.redonorpets.ShelterProfileActivity;

public class ShelterLargeAdapter extends RecyclerView.Adapter<ShelterLargeAdapter.CustomViewHolder> {

    private Context mCtx;
    private HashMap<String, ShelterData> mData;
    private ArrayList<String> shelterPosition = new ArrayList<>();

    public ShelterLargeAdapter(Context mCtx) {
        this.mCtx = mCtx;
        mData = new HashMap<>();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mCtx).inflate(R.layout.shelter_item_large, parent, false);


        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        ShelterData shelter = (ShelterData) mData.values().toArray()[position];

        String img = shelter.getProfileImg();
        holder.shelterPositionInAdpater = shelter.getShelterPosition();

        holder.tvShelterName.setText(shelter.getName());
        holder.tvShelterStoryCount.setText(shelter.getStoryCount() + "개의 스토리");
        Picasso.get().load(img).into(holder.ivShelterProfile);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView tvShelterName;
        TextView tvShelterStoryCount;
        ImageView ivShelterProfile;
        View pView;
        String shelterPositionInAdpater;


        public CustomViewHolder(View itemView) {
            super(itemView);

            pView = itemView;
            tvShelterName = itemView.findViewById(R.id.shelterNameTextView);
            tvShelterStoryCount = itemView.findViewById(R.id.shelterStoryCountTextView);
            ivShelterProfile = itemView.findViewById(R.id.shelterProfileImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(v.getContext(), ShelterProfileActivity.class);
                    intent.putExtra("shelterPosition", shelterPositionInAdpater);
                    v.getContext().startActivity(intent);
                }
            });
        }

    }

    public void addDataAndUpdate(String key, ShelterData p){
        mData.put(key, p);
        notifyDataSetChanged();
    }

    public void deleteDataAndUpdate(String key){
        mData.remove(key);
        notifyDataSetChanged();
    }
}