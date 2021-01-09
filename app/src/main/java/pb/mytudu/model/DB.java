package pb.mytudu.model;

import java.util.ArrayList;

public class DB {
    ArrayList<Task> listTask = new ArrayList<>();
    private static DB instance = null;

    private DB(){
//
    }

    public static DB getInstance() {
        if(instance == null)
            instance = new DB();
        return instance;
    }

    public Task getTaskById(int id){
        for(Task task : listTask){
            if(task.getId() == id)
                return task;
        };

        return null;
    }

    public int getIndexById(int id){
        for(int i=0; i<listTask.size(); i++){
            if(listTask.get(i).getId() == id)
                return i;
            i++;
        }

        return 3;
    }

    public void setTaskById(int id, Task task){
        listTask.set(getIndexById(id), task);
    }

    public void removeById(int id){
        listTask.remove(getIndexById(id));
    }

    public ArrayList<Task> getListTask(){
        return listTask;
    }
}
