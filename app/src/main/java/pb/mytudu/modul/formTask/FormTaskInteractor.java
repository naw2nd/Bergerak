package pb.mytudu.modul.formTask;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import pb.mytudu.callback.RequestCallback;
import pb.mytudu.model.Task;
import pb.mytudu.model.User;
import pb.mytudu.utils.Constant;
import pb.mytudu.utils.SharedPreferenceUtil;

public class FormTaskInteractor implements FormTaskContract.Interactor {
    private SharedPreferenceUtil sharedPreferenceUtil;
    private String token;
    private int userId;

    public FormTaskInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
        this.token = sharedPreferenceUtil.getToken();
        String userStr = this.sharedPreferenceUtil.getUser();
        User user = new Gson().fromJson(userStr, User.class);
        userId = user.getId();
    }

    @Override
    public void requestTask(RequestCallback<Task> requestCallback, int id) {
        AndroidNetworking.get(Constant.TASK + id)
                .addHeaders("Authorization", token)
                .build()
                .getAsObject(Task.class, new ParsedRequestListener<Task>() {
                    @Override
                    public void onResponse(Task response) {
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

    @Override
    public void saveTask(RequestCallback<Task> requestCallback, int id, Task task) {
        AndroidNetworking.put(Constant.TASK + id)
                .addHeaders("Authorization", token)
                .addJSONObjectBody(taskJsonObject(task))
                .build()
                .getAsObject(Task.class, new ParsedRequestListener<Task>() {
                    @Override
                    public void onResponse(Task response) {
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

    @Override
    public void deleteTask(int id) {
        AndroidNetworking.delete(Constant.TASK + id)
                .addHeaders("Authorization", token)
                .build();
//                .getAsObject(Task.class, new ParsedRequestListener<Task>() {
//                    @Override
//                    public void onResponse(Task response) {
//                        if(response != null){
//                            requestCallback.requestSuccess(response);
//                        }
//                        else {
//                            requestCallback.requestFailed("Null Response");
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        requestCallback.requestFailed(anError.getMessage());
//                    }
//                });
    }

    @Override
    public void addTask(RequestCallback<Task> requestCallback, Task task) {
        AndroidNetworking.post(Constant.TASK)
                .addHeaders("Authorization", token)
                .addJSONObjectBody(taskJsonObject(task))
                .build()
                .getAsObject(Task.class, new ParsedRequestListener<Task>() {
                    @Override
                    public void onResponse(Task response) {
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

    private JSONObject taskJsonObject(Task task){
        JSONObject jo = new JSONObject();
        try {
            jo.put("title", task.getTitle());
            jo.put("description", task.getDescription());
            jo.put("status", task.getStatus());
            jo.put("userId", userId);
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jo;
    }
}
