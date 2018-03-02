package masters.vlad.humeniuk.notesmvp.presentation.base;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class BaseRouter {

    private BaseActivity activityContext;

    private int containerId;

    public BaseRouter(BaseActivity activity, int containerId) {
        this.activityContext = activity;
        this.containerId = containerId;
    }

    public static void showFragment(BaseFragment fragment, boolean addToBackStack,
                                int containerId, FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getName());
        }
        transaction.commit();
    }

    public static void clearBackStack(FragmentManager fragmentManager) {
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    protected void startActivity(Intent intent) {
        activityContext.startActivity(intent);
    }

    protected void startActivityForResult(Intent intent, int requestCode) {
        activityContext.startActivityForResult(intent, requestCode);
    }

    protected BaseActivity getActivityContext() {
        return activityContext;
    }

    protected FragmentManager getFragmentManager() {
        return activityContext.getSupportFragmentManager();
    }

    protected int getContainerId() {
        return containerId;
    }
}
