package com.example.dagger2demo.subcomponent;

import com.example.dagger2demo.MainActivity;

import dagger.Component;
import dagger.Subcomponent;

@Subcomponent(modules = FirstModule.class)
public interface FirstComponent {
}
