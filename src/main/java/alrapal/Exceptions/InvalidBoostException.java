package alrapal.Exceptions;

public class InvalidBoostException extends Exception{
    public InvalidBoostException() {
        super("Le boost doit être un multiple de 200 (0,200,400,etc...)");
    }

    public InvalidBoostException(String message) {
        super(message);
    }
}
