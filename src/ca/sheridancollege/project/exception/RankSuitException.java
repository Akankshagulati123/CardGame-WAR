package ca.sheridancollege.project.exception;


/**
 * A class to be used as a custom exception for card rank and card suit related exceptions.
 *
 * @author dancye
 * @modifier Akanksha
 */
public class RankSuitException extends RuntimeException{
    private String exceptionMessage;

    public RankSuitException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
