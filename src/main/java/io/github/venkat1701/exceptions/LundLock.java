package io.github.venkat1701.exceptions;

public class LundLock {
    private String defaultMessage = "Lundlock:";
    private String prefix = "maa chudd gyi at ";
    private String error = defaultMessage + prefix;
    public LundLock(String lineNumber) {
        this.error = this.error + "bed 4";
    }

    public String getError() {
        return error;
    }
}
