package oosysteme.android;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

public class MainActivityViewModel extends AndroidViewModel {

    private StrategyRepository strategyRepository;

    public MainActivityViewModel(Application application) {
        super(application);
        this.strategyRepository = new StrategyRepository(application.getApplicationContext());
    }

    public StrategyRepository getStrategyRepository() {
        return strategyRepository;
    }
}
