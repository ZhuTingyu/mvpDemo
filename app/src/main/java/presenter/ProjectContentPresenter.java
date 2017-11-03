package presenter;

import java.util.List;

import io.reactivex.functions.Consumer;
import model.entity.ProjectContentEntity;

/**
 * Created by chenshuai on 2017/11/3.
 */

public interface ProjectContentPresenter {
    void onGetData(String id, Consumer<List<ProjectContentEntity>> onNext);
}
