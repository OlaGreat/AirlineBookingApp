package AirlineApp.exceptions;

public enum ExceptionMessages {

    PLEASE_PROVIDE_YOUR_COMPANY_NAME("Please provide your company name"),
    PLEASE_PROVIDE_YOUR_LICENCES_NUMBER("Please Provide  your Company Licence Number"),
    COMPANY_NOT_FOUND("Company not found"),
    FLIGHT_NOT_FOUND("Flight not found in valid flight Number"),
    FLIGHT_ALREADY_EXIST("Flight already exist"),
    INVALID_TAKE_OFF_DATE_PLEASE_CHECK_AND_CORRECT_DATE("Your takeoff date is incorrect please check and correct"),
    COMPANY_NOT_REGISTERED("Company is not registered");



    ExceptionMessages(String message){
        this.message = message;
    }
    private String message;

    public String getMessage(){
        return message;
    }
}
