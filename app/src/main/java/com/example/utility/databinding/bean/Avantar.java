package com.example.utility.databinding.bean;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableMap;

/**
 * Created by caoyouqiang on 18-4-3.
 */

public class Avantar extends BaseObservable{
	public final ObservableInt id = new ObservableInt();
	public final ObservableField<String> name = new ObservableField<>();
	public final ObservableField<String> url = new ObservableField<>();
	public final ObservableMap<String, Object> quality = new ObservableArrayMap<>();
	public final ObservableField<String> current = new ObservableField<>();
	public final ObservableField<String> content = new ObservableField<>();
}
