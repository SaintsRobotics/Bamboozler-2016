package com.saintsrobotics.util.logging;

public interface ContinuousLogObject {

    String getMessage();
    boolean isLogging();
    boolean remove();
}
