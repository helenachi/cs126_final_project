package edu.illinois.techdemonstration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import TVMaze.Show;

/**
 * Created by Helena Chi on 11/29/2017.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder>{
    Show tvshow = new Show();

    public DetailAdapter(Show tvshow) {
        this.tvshow = tvshow;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //set image
        final String imgURL = tvshow.getImage().getMedium();
        if (imgURL != null) {
            final Context context = holder.itemView.getContext();
            Picasso.with(context).load(imgURL).into(holder.showImageView);
        }

        //set title and year
        String titleAndYear = tvshow.getName();
        String year = " (" + tvshow.getPremiered().substring(0,4) + ")";
        titleAndYear += year;
        holder.titleYearTextView.setText(titleAndYear);

        //set genres
        final String[] genres = tvshow.getGenres();
        String genreList = TVMazeAdapter.getGenreList(genres);
        holder.genreTextView.setText(genreList);

        //set summary
        holder.summaryTextView.setText(tvshow.getSummary());

        //set rating
        String rating = "Rating: " + tvshow.getRating().toString() + " / 10";
        holder.ratingTextView.setText(rating);

        //set back button
        holder.backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context = view.getContext();
                Intent detailedIntent = new Intent(context, MainActivity.class);

                context.startActivity(detailedIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public ImageButton backImageButton;
        public ImageView showImageView;
        public TextView titleYearTextView;
        public TextView genreTextView;
        public TextView ratingTextView;
        public TextView summaryTextView;

        public ViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            this.backImageButton = (ImageButton) itemView.findViewById(R.id.back_imageButton);
            this.showImageView = (ImageView) itemView.findViewById(R.id.show_imageView);
            this.titleYearTextView = (TextView) itemView.findViewById(R.id.title_year_textView);
            this.genreTextView = (TextView) itemView.findViewById(R.id.genre_textView);
            this.ratingTextView = (TextView) itemView.findViewById(R.id.rating_textView);
        }
    }

}
