package ProjectSuggester;

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
            else return false;
        }
        return true;
    }
}
