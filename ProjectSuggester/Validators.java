package ProjectSuggester;

import java.util.Calendar;

public class Validators {
    public static Validators Throw = new Validators(true);
    public static Validators Validate = new Validators(false);
    private boolean isThrow;

    private Validators(boolean isThrow) {
        this.isThrow = isThrow;
    }


    public boolean NotPositive(int id) {
        if (id <= 0) {
            if (isThrow)
                throw new IllegalArgumentException();
            return false;
        }
        return true;
    }

    public boolean NotFuture(Calendar calendar) {
        if (calendar.after(calendar)) {
            if (isThrow) throw new IllegalArgumentException();
            return false;
        }
        return true;
    }
}
