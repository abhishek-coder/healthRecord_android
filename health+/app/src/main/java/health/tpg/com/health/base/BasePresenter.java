package health.tpg.com.health.base;

public abstract class BasePresenter<T extends BaseView> {

    private T view;

    protected void attachView(T view){
        this.view = view;
    }

    public T getView(){
        return view;
    }

    public  void detachView(){
        view = null;
    }

    public abstract void initView(String url);

    public abstract void cancelRequest();
}
