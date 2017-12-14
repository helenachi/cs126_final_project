//package edu.illinois.techdemonstration;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.squareup.picasso.Picasso;
//
//import TVMaze.Show;
//
///**
// * Created by Helena Chi on 11/29/2017.
// */
//
//public class DetailFragment extends Fragment {
//    View myView;
//
//    public DetailFragment() {
//        //empty constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        myView = inflater.inflate(edu.illinois.techdemonstration.R.layout.detail_activity, container, false);
//
//
//        // Get a show out of extras passed with intent
//
//        Intent intent = new Intent(getActivity(), DetailAdapter.class);
//        Show show = intent.getParcelableExtra("show");
//        final String imgURL = show.getImage().getMedium();
//
//
//
//        // fill the image
//        final ImageView showImageView = (ImageView) myView.findViewById(R.id.show_imageView);
//        Picasso.with(myView.getContext()).load(imgURL).into(showImageView);
//
//        // fill the name
//        final TextView titleYearTextView = (TextView) myView.findViewById(R.id.title_year_textView);
//        titleYearTextView.setText(show.getName());
//
//
//        return myView;
//    }
//
//
//
//
//}
