package AirlineApp.dtos.response;

public enum ResponseMessage {
    WELCOME_ONBOARD("Welcome onboard "),
    FLIGHT("Flight "),
    SUCCESSFULLY_REMOVED(" Successfully removed"),
    WE_LOOK_FORWARD_TO_A_WONDERFUL_PARTNERSHIP(" we look forward to a wonderful partnerShip");
private String message;
 ResponseMessage(String message){
    this.message = message;
}
public String getMessage(){
     return this.message;
}
}
