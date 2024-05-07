package dev.mmieckowski.algorithm.execution;

public class MissingDataException extends RuntimeException {
    public MissingDataException(String errorMsg) {
        super(errorMsg);
    }
}
