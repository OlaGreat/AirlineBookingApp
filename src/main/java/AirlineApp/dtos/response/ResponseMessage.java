package AirlineApp.dtos.response;

public enum ResponseMessage {
    WELCOME_ONBOARD("Welcome onboard "),
    FLIGHT("Flight "),
    SUCCESSFULLY_REMOVED(" Successfully removed"),
    FLIGHT_REGISTERED_SUCCESSFULLY("Flight registered successfully"),
    TRIP_SCHEDULE_FOR_FLIGHT("Trip schedule for flight "),
    FOR("for "),
    IS_SUCCESSFUL("Is Successful"),
    SCHEDULE("Schedule"),
    WE_LOOK_FORWARD_TO_A_WONDERFUL_PARTNERSHIP(" we look forward to a wonderful partnerShip"),
    FLIGHT_SCHEDULE_NOT_FOUND("Schedule flight not found"),
    YOU_SUCCESSFULLY_REGISTERED("You successfully registered"),
    USER_WITH_EMAIL_NOT_FOUND("User Not found"),
    SCHEDULE_SUCCESSFUL_DELETED("Schedule successfully deleted");
private String message;
 ResponseMessage(String message){
    this.message = message;
}
public String getMessage(){
     return this.message;
}
}
