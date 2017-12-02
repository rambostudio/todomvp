package com.rambostudio.todomvp.data.source;

import android.support.annotation.NonNull;

import com.rambostudio.todomvp.data.Task;

import java.util.Map;

/**
 * Created by kunrambo on 27-Nov-17.
 */

/**
 * Concrete implementation to load tasks from the data source into a cache.
 * For simplicity this implement a dumb synchronisation between locally persosted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty
 */
public class TasksRepository implements TasksDataSource {

    private static TasksRepository instance = null;

    private final TasksDataSource mTaskRemoteDataSource;

    private final TasksDataSource mTaskLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, Task> mCacheTasks;

    /**
     * Marks the cache as invalid, to force an update the next time data is required. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    public TasksRepository(TasksDataSource mTaskRemoteDataSource, TasksDataSource mTaskLocalDataSource) {
        this.mTaskRemoteDataSource = mTaskRemoteDataSource;
        this.mTaskLocalDataSource = mTaskLocalDataSource;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    @Override
    public void completeTask(@NonNull Task task) {

    }

    @Override
    public void completeTask(@NonNull String taskId) {

    }

    @Override
    public void activateTask(@NonNull Task task) {

    }

    @Override
    public void activateTask(@NonNull String taskId) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

    }
}
