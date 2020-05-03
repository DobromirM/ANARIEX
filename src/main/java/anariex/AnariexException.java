package anariex;

/**
 * @author DMarinov
 * Created on: 06/Feb/2019
 */
class AnariexException extends RuntimeException
{
    /**
     * Custom Anariex exception used for reporting parse errors.
     *
     * @param message - Error message of the exception.
     */
    AnariexException(String message)
    {
        super(message);
    }
}
