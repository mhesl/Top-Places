package com.example.nearbyplaces;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    /**
     * Could handle back press.
     *
     * @return true if back press was handled
     */
    public boolean onBackPressed() {
        return false;
    }
}
