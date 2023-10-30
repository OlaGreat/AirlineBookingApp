package AirlineApp.exceptions;

public enum ExceptionMessages {

    PLEASE_PROVIDE_YOUR_COMPANY_NAME("Please provide your company name"),
    PLEASE_PROVIDE_YOUR_LICENCES_NUMBER("Please Provide  your Company Licence Number"),
    COMPANY_NOT_FOUND("Company not found"),
    COMPANY_NOT_REGISTERED("Company is not registered");


    ExceptionMessages(String message){
        this.message = message;
    }
    private String message;

    public String getMessage(){
        return message;
    }
}
