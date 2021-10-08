package oosysteme.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class StrategyAdapter extends RecyclerView.Adapter<StrategyAdapter.ViewHolder> {

    private final StrategyRepository strategyRepository;

    public StrategyAdapter(StrategyRepository strategyRepository) {
        this.strategyRepository = strategyRepository;
        setHasStableIds(true);
    }

    public List<Strategy> getStrategies() {
        return strategyRepository.getStrategies();
    }

    @Override
    public int getItemCount() {
        return getStrategies().size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_strategy, parent, false);
        return new ViewHolder(strategyRepository, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Strategy strategy = getStrategies().get(position);
        holder.name.setText(strategy.getName());
        holder.description.setText(strategy.getDescription());
        holder.icon.setImageDrawable(strategy.getIcon());
        holder.itemView.setTag(strategy);
        if (strategy.isActive()) {
            ((MaterialCardView) holder.itemView).setChecked(true);
            if (strategy.getType() == StrategyType.MANUAL_CONTROL) {
                holder.action.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        int length = payloads.size();
        if (length == 0) {
            super.onBindViewHolder(holder, position, payloads);
            return;
        }
        for (int i = 0; i < length; i++) {
            Object object = payloads.get(i);
            if (object.equals("active")) {
                Strategy strategy = getStrategies().get(position);
                if (strategy.isActive()) {
                    ((MaterialCardView) holder.itemView).setChecked(true);
                    if (strategy.getType() == StrategyType.MANUAL_CONTROL) {
                        holder.action.setVisibility(View.VISIBLE);
                    }
                } else {
                    ((MaterialCardView) holder.itemView).setChecked(false);
                    if (strategy.getType() == StrategyType.MANUAL_CONTROL) {
                        holder.action.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        holder.action.setVisibility(View.GONE);
        ((MaterialCardView) holder.itemView).setChecked(false);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final StrategyRepository strategyRepository;

        private final TextView name;

        private final TextView description;

        private final ImageView icon;

        private final MaterialButton action;

        ViewHolder(StrategyRepository strategyRepository, View view) {
            super(view);
            this.strategyRepository = strategyRepository;
            view.setOnClickListener(this);
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            icon = view.findViewById(R.id.icon);
            action = view.findViewById(R.id.action);
            action.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.strategy_item) {
                Object object = v.getTag();
                if (object instanceof Strategy) {
                    Strategy strategy = (Strategy) object;
                    Toast.makeText(v.getContext(), strategy.getName(), Toast.LENGTH_SHORT).show();
                    strategyRepository.setActive(strategy.getType());
                    //TODO: bluetooth
                }
            } else if (v.getId() == R.id.action) {
                Toast.makeText(v.getContext(), "control", Toast.LENGTH_SHORT).show();
                //TODO: go to control
            }
        }
    }
}
