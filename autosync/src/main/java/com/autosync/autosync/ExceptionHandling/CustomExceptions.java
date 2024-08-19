package com.autosync.autosync.ExceptionHandling;

public class CustomExceptions {
    public static class ProfileNotFoundException extends RuntimeException {
        public ProfileNotFoundException(String message) {
            super(message);
        }
    }

    public static class ProfileNotProvidedException extends RuntimeException {
        public ProfileNotProvidedException(String message) {
            super(message);
        }
    }

    public static class CarNotFoundException extends RuntimeException {
        public CarNotFoundException(String message) {
            super(message);
        }
    }

    public static class CarNotProvidedException extends RuntimeException {
        public CarNotProvidedException(String message) {
            super(message);
        }
    }

    public static class LicenseNotFoundException extends RuntimeException {
        public LicenseNotFoundException(String message) {
            super(message);
        }
    }

    public static class LicenseNotProvidedException extends RuntimeException {
        public LicenseNotProvidedException(String message) {
            super(message);
        }
    }

    public static class CompanyNotFoundException extends RuntimeException {
        public CompanyNotFoundException(String message) {
            super(message);
        }
    }

    public static class CompanyNotProvidedException extends RuntimeException {
        public CompanyNotProvidedException(String message) {
            super(message);
        }
    }

    public static class CarOwnerNotProvidedException extends RuntimeException{
        public CarOwnerNotProvidedException(String message){
            super(message);
        }
    }
    public static class UniqueKeyViolationException extends RuntimeException {
        public UniqueKeyViolationException(String message) {
            super(message);
        }
    }

    public static class InvalidCredentialsException extends RuntimeException{
        public InvalidCredentialsException(String message){
            super(message);
        }
    }
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class UsernameAlreadyExistsException extends RuntimeException{
        public UsernameAlreadyExistsException(String message){
            super(message);
        }
    }

}
