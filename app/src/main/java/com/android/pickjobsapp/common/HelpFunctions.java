package com.android.pickjobsapp.common;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.android.pickjobsapp.R;
import com.android.pickjobsapp.model.Action;
import com.android.pickjobsapp.model.PatchApiRequestBody;
import com.android.pickjobsapp.model.PickJob;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HelpFunctions {

    public static boolean isValidLogin(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        if (password.length() < 3) return false;
        return !TextUtils.isEmpty(password);
    }

    public static void showSnackbar(View view, String message, Context context) {
        Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(context, R.color.black));
        Snackbar.SnackbarLayout customLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView tv = (TextView) snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setVisibility(View.INVISIBLE);
        LayoutInflater inflater = LayoutInflater.from(context);
        View customSnackView = inflater.inflate(R.layout.my_snackbar_layout, null);
        customSnackView.setBackgroundColor(ContextCompat.getColor(context, R.color.black));
        TextView customTv = customSnackView.findViewById(R.id.snackbar_tv);
        customTv.setText(message);
        snackbar.setDuration(2000);
        customLayout.addView(customSnackView);
        snackbar.show();
    }

    public static PatchApiRequestBody setPatchApiRequestBody(PickJob pickJob) {
        PatchApiRequestBody body = new PatchApiRequestBody();
        String newStatus = changeStatus(pickJob.getStatus());
        Action action = new Action();
        List<Action> actionsList = new ArrayList<>();
        action.setStatus(newStatus);
        action.setAction(Constants.PATACH_ACTION);
        actionsList.add(action);
        body.setVersion(pickJob.getVersion());
        body.setActions(actionsList);

        return body;
    }

    public static String changeStatus(String status) {
        if (status.equals(Constants.OPEN)) return Constants.CLOSED;
        else return Constants.OPEN;
    }
}
