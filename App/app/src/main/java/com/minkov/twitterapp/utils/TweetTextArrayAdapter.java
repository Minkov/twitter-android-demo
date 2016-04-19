package com.minkov.twitterapp.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.minkov.twitterapp.R;
import com.minkov.twitterapp.models.Tweet;

import java.util.List;

/**
 * Created by dminkov on 4/19/2016.
 */
public class TweetTextArrayAdapter extends ArrayAdapter<Tweet> {
    private static final String DEFAULT_PROFILE_IMAGE = "http://i.imgur.com/vNlsRbF.jpg";

    private final Context context;
    private final List<Tweet> values;

    public TweetTextArrayAdapter(Context context, List<Tweet> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    public void addValue(Tweet value) {
        this.values.add(value);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        textView.setText(values.get(position).getText());
        return rowView;
    }
}
