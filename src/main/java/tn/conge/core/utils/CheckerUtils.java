package tn.conge.core.utils;


import tn.conge.domain.exceptions.exceptions.CustomException;

import java.util.function.Supplier;

public abstract class CheckerUtils {
    private CheckerUtils() {
    }

    public static boolean returnFalseOrThrowException(Supplier<? extends CustomException> exceptionSupplier, boolean throwExceptionOnFalse) {
        if (throwExceptionOnFalse) {
            throw exceptionSupplier.get();
        }
        return false;
    }
}
