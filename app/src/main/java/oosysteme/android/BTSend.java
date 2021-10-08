package oosysteme.android;

import android.os.Looper;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.pc.comm.NXTConnector;
import oosysteme.common.Command;

public class BTSend extends Thread {
    private static final String TAG = "BTSend";
    private NXTConnector conn;
    private DataOutputStream dos;
    private DataInputStream dis;

    public BTSend() {
        super();
    }

    public void closeConnection() {
        try {
            Log.d(TAG, "BTSend run loop finished and closing");

            dis.close();
            dos.close();
            conn.getNXTComm().close();
        } catch (Exception e) {
        } finally {
            dis = null;
            dos = null;
            conn = null;
        }
    }

    public synchronized void writeCommand(Command command) {
        try {
            if (dos != null) {
                dos.writeByte(command.getType().getIndex());
                command.write(dos);
                dos.flush();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        Log.d(TAG, "BTSend run");
        Looper.prepare();

        while (true) {
            try {
                conn = MainActivity.connect(MainActivity.CONN_TYPE.LEJOS_PACKET);

                dos = conn.getDataOut();
                dis = conn.getDataIn();

        /*try {
            while (dos == null || dis == null) {
                Log.d("sleep", "sleep");
                try {
                    Thread.sleep(1000);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }*/

                while (true) {
                    //try {
                    if (dos == null || dis == null) {
                        Log.d("sleep", "sleep");
                        Thread.sleep(1000);
                    }
                    if (dis.available() >= 1) {
                        byte type = dis.readByte();
                        Log.d("readType", type + "");
                    }
                    //dos.writeByte(CommandType.MANUAL_CONTROL.getIndex());
                    //command.write(dos);
                    //dos.flush();
                    //Log.d("written", command.getLeftSpeed() + " " + command.getRightSpeed());
                    //Thread.sleep(1000);
                    //} catch (Exception exception) {
                    //    exception.printStackTrace();
                    //}
                }

                //int x;
                //for (int i = 0; i < 100; i++) {

            /*try {
                dos.writeInt((i * 30000));
                dos.flush();
                //LeJOSDroid.sendMessageToUIThread("sent:" + i * 30000);
                yield();
                x = dis.readInt();
                if (x > 0) {

                    //LeJOSDroid.displayToastOnUIThread("got: " + x);
                }
                Log.d(TAG, "sent:" + i * 30000 + " got:" + x);
                //LeJOSDroid.sendMessageToUIThread("sent:" + i * 30000 + " got:" + x);
                yield();
            } catch (IOException e) {
                Log.e(TAG, "Error ... ", e);

            }*/
                //}

                //closeConnection();
                //Looper.loop();
                //Looper.myLooper().quit();

                //LeJOSDroid.sendMessageToUIThread("");//clear
                //LeJOSDroid.displayToastOnUIThread("BTSend finished it's run");
            } catch (Exception ex) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ex.printStackTrace();
            }
        }
    }
}