package pb.mytudu.modul.listTask;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;

import java.util.List;

import pb.mytudu.callback.RequestCallback;
import pb.mytudu.model.Task;
import pb.mytudu.model.User;
import pb.mytudu.utils.Constant;
import pb.mytudu.utils.SharedPreferenceUtil;

public class ListTaskInteracator implements ListTaskContract.Interactor  {
    private SharedPreferenceUtil sharedPreferenceUtil;
    String token;
    int userId;

    ListTaskInteracator(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
        token = this.sharedPreferenceUtil.getToken();
        String userStr = this.sharedPreferenceUtil.getUser();
        User user = new Gson().fromJson(userStr, User.class);
        userId = user.getId();
    }

    @Override
    public void requestListTask(RequestCallback<List<Task>> requestCallback) {
        AndroidNetworking.get(Constant.TASK+"?userId="+ userId)
                .addHeaders("Authorization", token)
                .build()
                .getAsObjectList(Task.class, new ParsedRequestListener<List<Task>>() {
                    @Override
                    public void onResponse(List<Task> response) {
                        if(response != null){
                            requestCallback.requestSuccess(response);
                        }
                        else {
                            requestCallback.requestFailed("Null Response");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }
}
