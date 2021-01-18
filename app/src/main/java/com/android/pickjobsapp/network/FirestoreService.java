package com.android.pickjobsapp.network;

import com.android.pickjobsapp.common.Constants;
import com.android.pickjobsapp.listner.OnFirestoreTaskComplete;
import com.android.pickjobsapp.model.PickJob;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class FirestoreService {
    private final OnFirestoreTaskComplete onFirestoreTaskComplete;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FirestoreService(OnFirestoreTaskComplete onFirestoreTaskComplete) {
        this.onFirestoreTaskComplete = onFirestoreTaskComplete;
    }

    public void getPickJobs() {
        db.collection(Constants.COLLECTION_PATH).get().addOnCompleteListener(task -> {
            if ((task.isSuccessful())) {
                onFirestoreTaskComplete.onPickjobsAdded(Objects.requireNonNull(task.getResult()).toObjects(PickJob.class));
            } else {
                onFirestoreTaskComplete.onError(task.getException());
            }
        });
    }

    public void getPickJobsUpdated() {
        db.collection(Constants.COLLECTION_PATH).addSnapshotListener((value, error) -> {
            if (error != null) {
                onFirestoreTaskComplete.onError(error);
                return;
            }
            getPickJobs();
        });
    }
}
