package pb.mytudu.callback;

public interface RequestCallback<T> {
    void requestSuccess(T data);
    void requestFailed(String errorMessage);
}
