package presenter;

import com.google.gson.reflect.TypeToken;
import com.mpvdemo.app.MainView;

import java.util.List;

import io.reactivex.functions.Consumer;
import model.MainModel;
import model.MainModelImpl;
import model.ProjectContentModel;
import model.ProjectContentModelImpl;
import model.entity.ProjectContentEntity;
import model.entity.ProjectEntity;
import util.GsonUtil;

/**
 * Created by chenshuai on 2017/11/3.
 */

public class ProjectContentPresenterImpl implements ProjectContentPresenter {

    private MainView mainView;
    private ProjectContentModel mainModel;

    public ProjectContentPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainModel = new ProjectContentModelImpl();
    }

    @Override
    public void onGetData(String id,Consumer<List<ProjectContentEntity>> onNext) {
        if (mainView != null) {
            mainView.showProgress();
        }
        mainModel.getData(id).map(s -> {
            mainView.hideProgress();
            List<ProjectContentEntity> projectEntity = GsonUtil.fromJson(s, new TypeToken<List<ProjectContentEntity>>(){}.getType());
            return projectEntity;
        }).subscribe(onNext);
    }

}
