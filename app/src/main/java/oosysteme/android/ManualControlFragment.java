package oosysteme.android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import oosysteme.common.ManualControlCommand;

public class ManualControlFragment extends Fragment {

    private MainActivityViewModel viewModel;

    private static final short MAX_SPEED = 1024;

    private final ManualControlCommand command = new ManualControlCommand();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rowView = inflater.inflate(R.layout.fragment_manual_control, container, false);
        if (getActivity() != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        }
        JoystickView joystickView = rowView.findViewById(R.id.joystick);
        joystickView.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                if (strength == 0) {
                    //viewModel.getStrategyRepository().getBtSend().setLeftSpeed((short) 0);
                    //viewModel.getStrategyRepository().getBtSend().setRightSpeed((short) 0);

                    command.setLeftSpeed((short)0);
                    command.setRightSpeed((short)0);
                    viewModel.getStrategyRepository().getBtSend().writeCommand(command);
                    return;
                }

                short speedLeft = MAX_SPEED;
                short speedRight = MAX_SPEED;

                if (angle >= 80 && angle <= 100) { // oben mitte
                    speedLeft = MAX_SPEED;
                    speedRight = MAX_SPEED;
                } else if (angle >= 260 && angle <= 280) { // unten mitte
                    speedLeft = -MAX_SPEED;
                    speedRight = -MAX_SPEED;
                } else if (angle > 100 && angle <= 180) { // oben links
                    // 180 right schnell
                    speedRight = (short) (MAX_SPEED / 80 * (angle - 100));
                    speedLeft = MAX_SPEED;
                } else if (angle >= 0 && angle < 80) { // oben rechts
                    // 180 left schnell
                    speedLeft = (short) (MAX_SPEED / 80 * angle);
                    speedRight = MAX_SPEED;
                } else {
                    speedLeft = 0;
                    speedRight = 0;
                }

                /* else if(angle >= 0 && angle <= 180) { // oben
                    // 0 rechts langsamer / aus, links schneller
                    //90 gleich
                    // 180 links langsamer / aus, rechts langsamer

                    //speedLeft = (short) (400 - (400 / 360 * angle));
                    //speedRight = (short) ((400 / 360) * angle);
                    speedLeft = (short) (400 / 80  * (80.0 / angle));
                    speedRight = (short) (400 / 80 * angle);
                } else { // unten

                    //speedLeft = (short) (-400 - (400 / 360 * angle));
                    //speedRight = (short) (-(400 / 360) * angle);
                    //speedRight = (short) (-400 / 80 * angle);

                    //speedLeft = (short) (-400 - (400 / 80 * angle));
                    //speedRight = (short) (-400 / 80 * angle);

                    speedLeft = (short) (-400 / 80  * (80.0 / angle));
                    speedRight = (short) (-400 / 80 * angle);
                }*/


                //viewModel.getStrategyRepository().getBtSend().setLeftSpeed(speedLeft);
                //viewModel.getStrategyRepository().getBtSend().setRightSpeed(speedRight);
                command.setLeftSpeed(speedLeft);
                command.setRightSpeed(speedRight);
                viewModel.getStrategyRepository().getBtSend().writeCommand(command);

                /*if (angle >= 0 && angle <= 180) {
                    viewModel.getStrategyRepository().getBtSend().setLeftSpeed((short) 400);
                    viewModel.getStrategyRepository().getBtSend().setRightSpeed((short) 400);
                } else {
                    viewModel.getStrategyRepository().getBtSend().setLeftSpeed((short) -400);
                    viewModel.getStrategyRepository().getBtSend().setRightSpeed((short) -400);
                }*/
                Log.d("angle", angle + "");
            }
        });
        return rowView;
    }
}
