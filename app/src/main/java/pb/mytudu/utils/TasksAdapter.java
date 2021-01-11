package pb.mytudu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pb.mytudu.R;
import pb.mytudu.model.Task;

public class TasksAdapter extends ArrayAdapter<Task> {
    public TasksAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task, parent, false);
        }
        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.taskTitle);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.taskDetail);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.taskStatus);
        // Populate the data into the template view using the data object
        tvTitle.setText(task.getTitle());
        tvDesc.setText(task.getDescription());
        if(task.getStatus().equals("done")){
            tvStatus.setText("Done");
        }else{
            tvStatus.setText("");
        }
//        tvDesc.setText(String.valueOf(task.getId()));
        // Return the completed view to render on screen
        return convertView;
    }
}
