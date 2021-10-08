package oosysteme.android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StrategiesFragment extends Fragment implements Observer<StrategyType> {

    private StrategyAdapter strategyAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_strategies, container, false);
        RecyclerView list = view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            MainActivityViewModel viewModel = ViewModelProviders.of(activity).get(MainActivityViewModel.class);
            viewModel.getStrategyRepository().getActiveStrategy().observe(this, this);

            Log.d("strategies", viewModel.getStrategyRepository().getStrategies().get(0).getName());

            strategyAdapter = new StrategyAdapter(viewModel.getStrategyRepository());
            list.setAdapter(strategyAdapter);
        }
        return view;
    }

    @Override
    public void onChanged(StrategyType strategyType) {
        List<Strategy> strategies = strategyAdapter.getStrategies();
        for (int i = 0, length = strategies.size(); i < length; i++) {
            strategyAdapter.notifyItemChanged(i, "active");
        }
    }
}
