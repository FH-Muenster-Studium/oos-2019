package oosysteme.android;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import oosysteme.common.StrategyChangeCommand;

public class StrategyRepository {
    private final List<Strategy> strategies = new ArrayList<>();

    private final MutableLiveData<StrategyType> activeStrategy = new MutableLiveData<>();

    private BTSend btSend;

    public StrategyRepository(Context context) {
        strategies.add(new Strategy("Calibrate", "Calibrate", StrategyType.CALIBRATE, context.getDrawable(R.drawable.ic_calibre)));
        strategies.add(new Strategy("Zick Zack", "Zick Zack", StrategyType.ZICKZACK, context.getDrawable(R.drawable.ic_resistance)));
        strategies.add(new Strategy("Halt", "Halt", StrategyType.ZICKZACK_HALT, context.getDrawable(R.drawable.ic_stop)));
        strategies.add(new Strategy("Halt", "Halt", StrategyType.PID_HALT, context.getDrawable(R.drawable.ic_stop)));
        strategies.add(new Strategy("PID", "PID", StrategyType.PID, context.getDrawable(R.drawable.ic_axis)));
        strategies.add(new Strategy("Manual", "Manual control", StrategyType.MANUAL_CONTROL, context.getDrawable(R.drawable.ic_controller)));
        strategies.add(new Strategy("Overtake right", "Overtake right", StrategyType.OVERTAKE_RIGHT, context.getDrawable(R.drawable.ic_controller)));
        strategies.add(new Strategy("Overtake left", "Overtake left", StrategyType.OVERTAKE_LEFT, context.getDrawable(R.drawable.ic_controller)));
        strategies.add(new Strategy("Overtake circle right", "Overtake circle right", StrategyType.OVERTAKE_CIRCLE_RIGHT, context.getDrawable(R.drawable.ic_controller)));
        strategies.add(new Strategy("Zickzack2", "Zickzack2", StrategyType.ZICKZACK2, context.getDrawable(R.drawable.ic_resistance)));
    }

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public LiveData<StrategyType> getActiveStrategy() {
        return activeStrategy;
    }

    public void setActive(StrategyType strategyType) {
        activeStrategy.setValue(strategyType);
        for (int i = 0, length = strategies.size(); i < length; i++) {
            Strategy strategy = strategies.get(i);
            if (strategy.getType() == strategyType) {
                strategy.setActive(true);
            } else {
                strategy.setActive(false);
            }
        }
        getBtSend().writeCommand(new StrategyChangeCommand(strategyType));
    }

    public BTSend getBtSend() {
        return btSend;
    }

    public void setBtSend(BTSend btSend) {
        this.btSend = btSend;
    }
}
