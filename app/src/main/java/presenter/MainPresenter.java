package presenter;

import java.util.List;

import io.reactivex.functions.Consumer;
import model.entity.ProjectEntity;

/**
 * Created by chenshuai on 2017/11/3.
 */

public interface MainPresenter {

    void onGetData(Consumer<List<ProjectEntity>> onNext);

}
