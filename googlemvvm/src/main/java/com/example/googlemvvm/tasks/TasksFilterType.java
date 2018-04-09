package com.example.googlemvvm.tasks;

public enum TasksFilterType {
	/**
	 * Do not filter tasks.
	 */
	ALL_TASKS,

	/**
	 * Filters only the active (not completed yet) tasks.
	 */
	ACTIVE_TASKS,

	/**
	 * Filters only the completed tasks.
	 */
	COMPLETED_TASKS
}
