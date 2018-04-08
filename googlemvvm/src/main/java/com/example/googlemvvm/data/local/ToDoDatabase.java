package com.example.googlemvvm.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.googlemvvm.data.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class ToDoDatabase extends RoomDatabase{
	private static ToDoDatabase INSTANCE;

	public abstract TasksDao tasksDao();

	private static final Object sLock = new Object();

	public static ToDoDatabase getInstance(Context context){
		synchronized (sLock){
			if (INSTANCE == null){
				INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
						ToDoDatabase.class, "Tasks.db")
						.build();
			}
			return INSTANCE;
		}
	}
}
