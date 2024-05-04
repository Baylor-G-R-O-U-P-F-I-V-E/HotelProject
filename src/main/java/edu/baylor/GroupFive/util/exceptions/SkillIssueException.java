package edu.baylor.GroupFive.util.exceptions;

/**
 * Throw if just bad at coding.
 *
 * Extends the {@link edu.baylor.GroupFive.util.exceptions.HotelProjectException} abstract exception class.
 *
 * @author Icko
 */
public class SkillIssueException extends HotelProjectException {

    private static final int SIE_CODE = 69;

    /**
     * Constructs a new SkillIssueException object.
     */
    public SkillIssueException() {
        super("Skill Issue encountered", SIE_CODE);
    }

    /**
     * Constructs a new SkillIssueException with the specified message.
     *
     * @param message Error message
     * @param code Specific exception code
     * */
    public SkillIssueException(String message, int code) {
        super(message, SIE_CODE);
    }

    /**
     * Construct a new SkillIssueException with the given cause.
     *
     * @param cause Cause of exception
     * @param code Specific exception code
     * */
    public SkillIssueException(Throwable cause, int code) {
        super(cause, SIE_CODE);
    }

    /**
     * Construct a new SkillIssueException with a specified message
     * and given cause.
     *
     * @param message Error message
     * @param cause Cause of exception
     * @param code Specific exception code
     * */
    public SkillIssueException(String message, Throwable cause, int code) {
        super(message, cause, SIE_CODE);
    }

    /**
     * Construct a new SkillIssueException with a specified message
     * and given cause, with extra flags for designating suppression
     * and writable stack trace status.
     *
     * @param message Error message
     * @param cause Cause of exception
     * @param enableSuppression Flag for enable/disable suppression
     * @param writableStackTrace Flag for writable stack trace
     * @param code Specific exception code
     * */
    public SkillIssueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace, SIE_CODE);
    }

}
