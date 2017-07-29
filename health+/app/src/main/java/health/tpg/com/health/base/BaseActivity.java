package health.tpg.com.health.base;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;
import health.tpg.com.health.R;
import health.tpg.com.health.enums.FragmentTag;
import health.tpg.com.health.fragmentManager.AppFragmentManager;
import health.tpg.com.health.util.AppConstants;
import health.tpg.com.health.widget.CustomProgressDialog;


public abstract class BaseActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private static final String PROGRESS_TAG = "progress_dialog";
    ActionBar actionBar;
    private AppFragmentManager appFragmentManager;
    private CustomProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar = getSupportActionBar();
        appFragmentManager = new AppFragmentManager(this);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setToolbar(String title) {
        actionBar.setTitle(title);
        supportInvalidateOptionsMenu();
    }


    protected abstract int getResourceId();

    /**
     * get main framelayout id to show fragments
     *
     * @return
     */
    protected abstract int getMainFragmentContainerId();

    /**
     * initialize view such that it can be reinitialized by calling this method
     */
    protected abstract void initView();

    @Override
    public void showMessage(String msg) {
        Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        snackBar.show();
    }

    @Override
    public void showFragment(FragmentTag fragmentTag, Parcelable fragmentArgs, boolean addToBackStack) {
        if (appFragmentManager != null)
            appFragmentManager.showFragment(getMainFragmentContainerId(), fragmentTag, fragmentArgs, addToBackStack);

    }

    @Override
    public void showFragment(int containerId, FragmentTag fragmentTag, Parcelable fragmentArgs, boolean addToBackStack) {
        if (appFragmentManager != null)
            appFragmentManager.showFragment(containerId, fragmentTag, fragmentArgs, addToBackStack);
    }

    @Override
    public void clearBackStack() {
        if (appFragmentManager != null)
            appFragmentManager.clearBackStack();
    }

    @Override
    public void clearBackStackUptoFrag(FragmentTag fragmentTag) {
        if (appFragmentManager != null)
            appFragmentManager.clearBackStackUptoFrag(fragmentTag);

    }

    @Override
    public void showError(FailureResponse failureResponse) {

    }

    @Override
    public void showLoading(boolean isCancelable) {
        hideLoading();
        mProgressDialog = new CustomProgressDialog();
        Bundle args = new Bundle();
        args.putBoolean(AppConstants.Bundle.PROGRESS_DIALOG_ISCANCELABLE, isCancelable);
        args.putString(AppConstants.Bundle.PROGRESS_DIALOG_MESSAGE, getString(R.string.loading));
        mProgressDialog.setArguments(args);
        mProgressDialog.setRetainInstance(true);
        mProgressDialog.show(getSupportFragmentManager(), PROGRESS_TAG);
    }

    @Override
    public void showLoading(String message, boolean isCancelable) {
        hideLoading();
        mProgressDialog = new CustomProgressDialog();
        Bundle args = new Bundle();
        args.putBoolean(AppConstants.Bundle.PROGRESS_DIALOG_ISCANCELABLE, isCancelable);
        args.putString(AppConstants.Bundle.PROGRESS_DIALOG_MESSAGE, message);
        mProgressDialog.setArguments(args);
        mProgressDialog.setRetainInstance(true);
        mProgressDialog.show(getSupportFragmentManager(), PROGRESS_TAG);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isAdded()) {
            mProgressDialog.dismissAllowingStateLoss();
        }

    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (appFragmentManager != null)
            appFragmentManager.cancelAction(getMainFragmentContainerId());
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        appFragmentManager.cancelAction(getMainFragmentContainerId());
    }

}
