package com.example.googlemvvm.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.util.UUID;

@Entity(tableName = "tasks")
public final class Task {

	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "entryid")
	private final String mId;

	@Nullable
	@ColumnInfo(name = "title")
	private final String mTitle;

	@android.support.annotation.Nullable
	@ColumnInfo(name = "description")
	private final String mDescription;

	@ColumnInfo(name = "completed")
	private final boolean mCompleted;

	@Ignore
	public Task(@Nullable String title, @Nullable String description) {
		this(title, description, UUID.randomUUID().toString(), false);
	}

	@Ignore
	public Task(@Nullable String title, @Nullable String description, boolean completed) {
		this(title, description, UUID.randomUUID().toString(), completed);
	}

	@Ignore
	public Task(@Nullable String title, @Nullable String description, @NonNull String id) {
		this(title, description, id, false);
	}

	public Task(@Nullable String title, @Nullable String description,
				@NonNull String id, boolean completed){
		mId = id;
		mTitle = title;
		mDescription = description;
		mCompleted = completed;
	}

	@NonNull
	public String getId() {
		return mId;
	}

	@Nullable
	public String getTitle() {
		return mTitle;
	}

	@Nullable
	public String getTitleForList() {
		if (!Strings.isNullOrEmpty(mTitle)) {
			return mTitle;
		} else {
			return mDescription;
		}
	}

	@Nullable
	public String getDescription() {
		return mDescription;
	}

	public boolean isCompleted() {
		return mCompleted;
	}

	public boolean isActive() {
		return !mCompleted;
	}

	public boolean isEmpty() {
		return Strings.isNullOrEmpty(mTitle) &&
				Strings.isNullOrEmpty(mDescription);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(mId, mTitle, mDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Task task = (Task) obj;
		return Objects.equal(mId, task.mId) &&
				Objects.equal(mTitle, task.mTitle) &&
				Objects.equal(mDescription, task.mDescription);
	}

	@Override
	public String toString() {
		return "Task with title " + mTitle;
	}
}
