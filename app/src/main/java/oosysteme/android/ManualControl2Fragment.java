package oosysteme.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;
import io.github.controlwear.virtual.joystick.android.JoystickView;
import oosysteme.common.ManualControlCommand;
import oosysteme.common.PIDChangeCommand;
import oosysteme.common.StrategyChangeCommand;
import oosysteme.common.ZickZackOffsetChangeCommand;
import oosysteme.common.ZickZackSpeedOffsetChangeCommand;

public class ManualControl2Fragment extends Fragment {

    private MainActivityViewModel viewModel;

    private final ManualControlCommand command = new ManualControlCommand();

    private final PIDChangeCommand commandPid = new PIDChangeCommand();

    private final ZickZackSpeedOffsetChangeCommand commandSpeedOffset = new ZickZackSpeedOffsetChangeCommand();

    private final ZickZackOffsetChangeCommand commandOffset = new ZickZackOffsetChangeCommand();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rowView = inflater.inflate(R.layout.fragment_manual_control2, container, false);
        if (getActivity() != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        }
        final BoxedVertical left = rowView.findViewById(R.id.left);
        final BoxedVertical right = rowView.findViewById(R.id.right);

        final EditText offset = rowView.findViewById(R.id.offset);
        final EditText speedOffset = rowView.findViewById(R.id.speedoffset);

        final Button offsetApply = rowView.findViewById(R.id.offset_apply);
        final Button speedOffsetApply = rowView.findViewById(R.id.speedoffset_apply);

        final EditText offset2 = rowView.findViewById(R.id.offset_2);
        final EditText speedOffset2 = rowView.findViewById(R.id.speedoffset_2);

        final Button offsetApply2 = rowView.findViewById(R.id.offset_apply_2);
        final Button speedOffsetApply2 = rowView.findViewById(R.id.speedoffset_apply_2);

        offsetApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int offsetValue = Integer.parseInt(offset.getEditableText().toString());
                    commandOffset.setOffset(offsetValue);
                    viewModel.getStrategyRepository().getBtSend().writeCommand(commandOffset);
                }catch (Exception ex) {
                    Activity activity = getActivity();
                    if (activity != null)
                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        speedOffsetApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int speedOffsetValue = Integer.parseInt(speedOffset.getEditableText().toString());
                    commandSpeedOffset.setSpeedOffset(speedOffsetValue);
                    viewModel.getStrategyRepository().getBtSend().writeCommand(commandSpeedOffset);
                }catch (Exception ex) {
                    Activity activity = getActivity();
                    if (activity != null)
                        Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        offsetApply2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int offsetValue = Integer.parseInt(offset2.getEditableText().toString());
                    commandOffset.setOffset(offsetValue);
                    viewModel.getStrategyRepository().getBtSend().writeCommand(commandOffset);
                }catch (Exception ex) {
                    Activity activity = getActivity();
                    if (activity != null)
                        Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        speedOffsetApply2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int speedOffsetValue = Integer.parseInt(speedOffset2.getEditableText().toString());
                    commandSpeedOffset.setSpeedOffset(speedOffsetValue);
                    viewModel.getStrategyRepository().getBtSend().writeCommand(commandSpeedOffset);
                }catch (Exception ex) {
                    Activity activity = getActivity();
                    if (activity != null)
                        Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        /*final EditText p = rowView.findViewById(R.id.p);
        final EditText i = rowView.findViewById(R.id.i);
        final EditText d = rowView.findViewById(R.id.d);

        Button apply = rowView.findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    commandPid.setP(Float.parseFloat(p.getEditableText().toString()));
                    commandPid.setI(Float.parseFloat(i.getEditableText().toString()));
                    commandPid.setD(Float.parseFloat(d.getEditableText().toString()));
                    Log.d("pid", commandPid.getP() + " " + commandPid.getI() + " " + commandPid.getD());
                    StrategyChangeCommand command = new StrategyChangeCommand();
                    command.setType(StrategyType.PID);
                    viewModel.getStrategyRepository().getBtSend().writeCommand(command);
                    viewModel.getStrategyRepository().getBtSend().writeCommand(commandPid);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });*/

        left.setOnBoxedPointsChangeListener(new BoxedVertical.OnValuesChangeListener() {
            @Override
            public void onPointsChanged(BoxedVertical boxedVertical, int i) {
                command.setLeftSpeed((short) ((short) i - 1024));
                viewModel.getStrategyRepository().getBtSend().writeCommand(command);
            }

            @Override
            public void onStartTrackingTouch(BoxedVertical boxedVertical) {

            }

            @Override
            public void onStopTrackingTouch(BoxedVertical boxedVertical) {
                left.setValue(1024);
            }
        });

        right.setOnBoxedPointsChangeListener(new BoxedVertical.OnValuesChangeListener() {
            @Override
            public void onPointsChanged(BoxedVertical boxedVertical, int i) {
                command.setRightSpeed((short) ((short) i - 1024));
                viewModel.getStrategyRepository().getBtSend().writeCommand(command);
            }

            @Override
            public void onStartTrackingTouch(BoxedVertical boxedVertical) {
            }

            @Override
            public void onStopTrackingTouch(BoxedVertical boxedVertical) {
                right.setValue(1024);
            }
        });
        return rowView;
    }
}
