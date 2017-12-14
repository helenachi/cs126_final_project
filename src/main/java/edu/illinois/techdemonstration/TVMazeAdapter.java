package edu.illinois.techdemonstration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import TVMaze.Show;

/**
 * Created by Helena Chi on 11/15/2017.
 */

public class TVMazeAdapter extends RecyclerView.Adapter<TVMazeAdapter.ViewHolder>{
    private List<Show> shows;

    public static final String TVSHOW = "SHOW";

    public TVMazeAdapter(List<Show> tvShowsList) {
        this.shows = tvShowsList;
    }

    public void addShow(Show show) {
        this.shows.add(show);
    }

    public void addShowList(List<Show> showList) {
        this.shows.addAll(showList);
    }

    public Show[] filterShows(Show[] shows, String label) {
        List<Show> showsWithLabel = new ArrayList<>();

        if (label != null && label.length() > 0) {
            for (Show s : shows) {
                if (label.equals(s.getLabel())) {
                    showsWithLabel.add(s);
                }
            }
            return showsWithLabel.toArray(new Show[showsWithLabel.size()]);
        } else {
            return shows;
        }


    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.tvshow_item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        View tvShowItem = LayoutInflater.from(context).inflate(viewType, parent, false);
        return new ViewHolder(tvShowItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Show show = shows.get(position);

        final String imgURL = show.getImage().getMedium();
        if (imgURL != null) {
            final Context context = holder.itemView.getContext();
            Picasso.with(context).load(imgURL).into(holder.imageView);
        }

        holder.nameTextView.setText(show.getName());

        final String[] genres = show.getGenres();
        String genreList = getGenreList(genres);
        holder.genreTextView.setText(genreList);

        final int runtime = show.getRuntime();
        holder.runtimeTextView.setText(String.valueOf(runtime) + " minutes per episode");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra(TVSHOW,show);
                view.getContext().startActivity(intent);
            }

        });


    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public ImageView imageView;
        public TextView nameTextView;
        public TextView genreTextView;
        public TextView runtimeTextView;

        public ViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            this.genreTextView = (TextView) itemView.findViewById(R.id.genreTextView);
            this.runtimeTextView = (TextView) itemView.findViewById(R.id.runtimeTextView);
        }

    }


    public static String getGenreList(String[] genres) {
        String genreList = "";
        if (genres.length == 1) {
            genreList = genres[0];
        } else if (genres.length > 1) {
            if (genres.length >= 1) {
                genreList = genres[0];
                for (int i = 1; i < genres.length - 1; i++) {
                    genreList = genreList + ", " + genres[i];
                }
                genreList += ", " + genres[genres.length - 1];
            }
        } else {
            genreList = "genre unspecified";
        }
        return genreList;
    }
}
