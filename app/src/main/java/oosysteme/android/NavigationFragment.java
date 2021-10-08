package oosysteme.android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

public class NavigationFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        NavigationView navigationView = view.findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("nav", item.getItemId() + "");
                switch (item.getItemId()) {
                    case R.id.manual_control_exec:
                        Log.d("manual", "control");
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, new ManualControl2Fragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case R.id.strategies_exec:
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, new StrategiesFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                }
                return false;
            }
        });
        return view;
    }
}
