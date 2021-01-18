package com.android.pickjobsapp.listner;

import com.android.pickjobsapp.model.PickJob;
import java.util.List;

public interface OnFirestoreTaskComplete {
    void onPickjobsAdded(List<PickJob> pickJobList);
    void onError(Exception exception);
}
