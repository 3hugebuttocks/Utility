package com.example.dagger2demo.data;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TaskRepositoryModule {

	@Singleton
	@Binds
	abstract TaskDataSource provideTasksLocalDataSource(TaskLocalDataSource dataSource);
}
