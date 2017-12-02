package com.rambostudio.todomvp.data.source.remote;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.common.collect.Lists;
import com.rambostudio.todomvp.data.Task;
import com.rambostudio.todomvp.data.source.TasksDataSource;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;

    private static final int SERVICE_LATENCY_IN_MILLS = 5000;

    private final static Map<String, Task> TASK_SERVICE_DATA;

    static {
        TASK_SERVICE_DATA = new LinkedHashMap<>(2);
        addTask("Build tower in Pisa", "Ground looks good, no foundation work required.");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!");
    }

    public static TasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private TasksRemoteDataSource() {
    }


    private static void addTask(String title, String description) {
        Task newTask = new Task(title, description);
        TASK_SERVICE_DATA.put(newTask.getId(), newTask);
    }

    /**
     * Note: {@Link LoadTasksCallback#onDataNotAvailable()} is never fired. In a real remote data
     * source implementation, this would be fired if the server can't be contracted or the server
     * return an error.
     * @param callback
     */
    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {

        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onTasksLoaded(Lists.newArrayList(TASK_SERVICE_DATA.values()));
            }
        }, SERVICE_LATENCY_IN_MILLS);

    }

    /**
     * Note: {@link GetTaskCallback#onDataNotAvailable()} is never fired. In a real remote data
     * soure implementation, this would be fired if the server can't be contracted or the server
     * returns an error.
     *
     * @param taskId
     * @param callback
     */
    @Override
    public void getTask(@NonNull String taskId, @NonNull final GetTaskCallback callback) {
        final Task task = TASK_SERVICE_DATA.get(taskId);

        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onTaskLoaded(task);
            }
        }, SERVICE_LATENCY_IN_MILLS);
    }

    @Override
    public void saveTask(@NonNull Task task) {
        TASK_SERVICE_DATA.put(task.getId(), task);
    }

    @Override
    public void completeTask(@NonNull Task task) {
        Task completedTask = new Task(task.getTitle(), task.getDescription(), task.getId(), true);
        TASK_SERVICE_DATA.put(task.getId(), completedTask);
    }

    @Override
    public void completeTask(@NonNull String taskId) {
        // Not required for the remote data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }

    @Override
    public void activateTask(@NonNull Task task) {
        Task activeTask = new Task(task.getTitle(), task.getDescription(), task.getId());
        TASK_SERVICE_DATA.put(task.getId(), activeTask);
    }

    @Override
    public void activateTask(@NonNull String taskId) {
        // Not required for the remote data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }

    @Override
    public void clearCompletedTasks() {
        Iterator<Map.Entry<String, Task>> it = TASK_SERVICE_DATA.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Task> entry = it.next();
            if (entry.getValue().isCompleted()) {
                it.remove();
            }
        }
    }


    @Override
    public void refreshTasks() {
        // Not required because the {@link TasksRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }

    @Override
    public void deleteAllTasks() {
        TASK_SERVICE_DATA.clear();
    }

    @Override
    public void deleteTask(@NonNull String taskId) {
        TASK_SERVICE_DATA.remove(taskId);
    }
}
