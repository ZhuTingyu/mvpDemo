package presenter;

import com.google.gson.reflect.TypeToken;
import com.mpvdemo.app.MainView;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import model.MainModel;
import model.MainModelImpl;
import model.entity.ProjectEntity;
import util.GsonUtil;

/**
 * Created by chenshuai on 2017/11/3.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private MainModel mainModel;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainModel = new MainModelImpl();
    }

    @Override
    public void onGetData(Consumer<List<ProjectEntity>> onNext) {
        if (mainView != null) {
            mainView.showProgress();
        }
       /* mainModel.getData().map(s -> {
            mainView.hideProgress();
            List<ProjectEntity> projectEntity = GsonUtil.fromJson(s, new TypeToken<List<ProjectEntity>>(){}.getType());
            return projectEntity;
        }).subscribe(onNext);*/

        mainModel.demoInfo().map(s -> {
            mainView.hideProgress();
            return s;
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(r -> {

        });
    }
}
