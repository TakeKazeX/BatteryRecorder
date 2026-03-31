package android.app;

import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface INotificationManager extends IInterface {

    void createNotificationChannelsForPackage(
            String pkg,
            int uid,
            boolean fromTargetApp,
            ParceledListSlice channels
    ) throws RemoteException;

    void enqueueNotificationWithTag(
            String pkg,
            String opPkg,
            String tag,
            int id,
            Notification notification,
            int userId
    ) throws RemoteException;

    abstract class Stub extends Binder implements INotificationManager {
        public static INotificationManager asInterface(IBinder obj) {
            throw new UnsupportedOperationException();
        }
    }
}