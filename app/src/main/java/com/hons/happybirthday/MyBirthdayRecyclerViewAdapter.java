package com.hons.happybirthday;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hons.happybirthday.BirthdayFragment.OnListFragmentInteractionListener;
import com.hons.happybirthday.domain.BirthdayHolder;
import com.hons.happybirthday.domain.entity.Birthday;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Birthday} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyBirthdayRecyclerViewAdapter extends RecyclerView.Adapter<MyBirthdayRecyclerViewAdapter.ViewHolder> {

    private final List<Birthday> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyBirthdayRecyclerViewAdapter(List<Birthday> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_birthday, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.birthdayName.setText(mValues.get(position).getName());
        holder.birthdayDate.setText(mValues.get(position).getFormatedBirthday());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    // TODO: 07.01.2018 use this for item select interaction
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView birthdayName;
        public final TextView birthdayDate;
        public final ImageButton birthdayDeleteButton;
        public Birthday mItem;

        public ViewHolder(final View view) {
            super(view);
            mView = view;
            birthdayName = view.findViewById(R.id.birthdayName);
            birthdayDate = view.findViewById(R.id.birthdayDate);
            birthdayDeleteButton = view.findViewById(R.id.birthdayDeleteButton);
            
            birthdayDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    String message = String.format("Do you really want to delete %s's birthday?", 
                            mItem.getName());
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Confirm delete")
                            .setMessage(message)
                            .setIcon(R.drawable.ic_warning_black_24dp)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    BirthdayHolder.getInstance(v.getContext()).deleteBirthDay(mItem);
                                    MyBirthdayRecyclerViewAdapter.super.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton(R.string.no, null)
                            .show();
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + birthdayName.getText() + "'";
        }
    }
}
