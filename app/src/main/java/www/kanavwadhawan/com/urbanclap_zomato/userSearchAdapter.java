package www.kanavwadhawan.com.urbanclap_zomato;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class userSearchAdapter extends RecyclerView.Adapter<userSearchAdapter.UserSearchViewHolder> {
    Context context;
    public List<Restaurants> userSearchresult;
    @NonNull
    @Override
    public UserSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_action_list_item, null);
        Log.i("TAG","onCreateViewHolder");
        // create ViewHolder
        return new UserSearchViewHolder(itemLayoutView);
    }

    public userSearchAdapter(Context context, List<Restaurants> restaurantsArrayListInfo) {
        this.context = context;
        this.userSearchresult=restaurantsArrayListInfo;

        //Log.i("TAG",userSearchresult.get(0).getRestaurant().getName());


    }

    @Override
    public void onBindViewHolder(@NonNull userSearchAdapter.UserSearchViewHolder holder, int position) {
        final Restaurants restaurants = userSearchresult.get(position);

        Log.i("TAG","hello");

        holder.resName.setText(restaurants.getRestaurant().getName());
     holder.placeImage.setImageResource(R.drawable.zomato);
     holder.place_description.setText(restaurants.getRestaurant().getCuisines());
     holder.place_budget.setText("Average cost for two : "+restaurants.getRestaurant().getAverage_cost_for_two());
        //holder.resName.setText("Kanav");
        holder.place_location.setText(restaurants.getRestaurant().getLocation().getCity());

    }

    @Override
    public int getItemCount() {
        return userSearchresult.size();
    }


    protected class UserSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView resName;
        final ImageView placeImage;
        final TextView place_description;
       final TextView place_budget;
        final TextView place_location;
//        final TextView friendsText;
//        final TextView cancelBtn;

        UserSearchViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            itemLayoutView.setOnClickListener(this);
            resName= itemLayoutView.findViewById(R.id.place_name);
            placeImage=itemLayoutView.findViewById(R.id.place_image);
            place_description=itemLayoutView.findViewById(R.id.place_description);
            place_budget=itemLayoutView.findViewById(R.id.place_budget);
            place_location= itemLayoutView.findViewById(R.id.place_location);
//            addFriendText= itemLayoutView.findViewById(R.id.addFrndBtn);
//            friendsText= itemLayoutView.findViewById(R.id.friendsText);
//            cancelBtn= itemLayoutView.findViewById(R.id.cancelbtn);


        }

        @Override
        public void onClick(View v) {

        }


    }
    private void add(Restaurants users) {
        userSearchresult.add(users);
        notifyItemInserted(userSearchresult.size() - 1);
    }

    public void addAll(List<Restaurants> usersList) {
        for (Restaurants restaurants : usersList) {
            add(restaurants);
        }
    }

    public void addAllOnTop(List<Restaurants> usersList) {
        userSearchresult.addAll(0, usersList);
        notifyItemRangeInserted(0, usersList.size() - 1);
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        List<Restaurants> usersList=userSearchresult;

        if (charText.length() == 0) {
            //userSearchresult.addAll(arraylist);

        }
        else
        {
            for (Restaurants wp : userSearchresult) {
                if (wp.getRestaurant().getCuisines().contains(charText)) {
                    userSearchresult.add(wp);
                    Log.i("TAG","inside");
                }

            }

        }
        notifyDataSetChanged();
    }


}
