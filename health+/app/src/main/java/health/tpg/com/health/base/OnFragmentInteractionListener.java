package health.tpg.com.health.base;

import android.os.Parcelable;

import health.tpg.com.health.enums.FragmentTag;


public interface OnFragmentInteractionListener {


    /**
     * show message on view
     *
     * @param msg
     */
    void showMessage(String msg);

    /**
     * perform UI action on data failure response
     *
     * @param failureResponse
     */
    void showError(FailureResponse failureResponse);

    /**
     * show progress
     *
     * @param isCancelable
     */
    void showLoading(boolean isCancelable);

    /**
     * show progress with message
     *
     * @param message
     * @param isCancelable
     */
    void showLoading(String message, boolean isCancelable);

    /**
     * hide progress
     */
    void hideLoading();



    /**
     * show fragment
     *
     * @param fragmentTag
     * @param fragmentArgs
     * @param addToBackStack
     */

    void showFragment(FragmentTag fragmentTag, Parcelable fragmentArgs, boolean addToBackStack);


    /**
     * show fragment for particular container
     *
     * @param containerId
     * @param fragmentTag
     * @param fragmentArgs
     * @param addToBackStack
     */

    void showFragment(int containerId, FragmentTag fragmentTag, Parcelable fragmentArgs, boolean addToBackStack);

    /**
     * clearing all back stack.
     */

    void clearBackStack();


    /**
     * clear back stack upto some fragment.
     *
     * @param fragmentTag
     */
    void clearBackStackUptoFrag(FragmentTag fragmentTag);


}
