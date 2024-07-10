package lk.ijse.bo;


import lk.ijse.bo.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        if (boFactory==null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public SuperBO getBOTYpes(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER :
                return new CustomerBoImpl();
            case ROOM:
                return new RoomBoImpl();
            case PAYMENT:
                return new PaymentBoImpl();
            case PACKAGE:
                return new PackageBoImpl();
            case EMPLOYEE:
                return new EmployeeBoImpl();
            case ADMIN:
                return new AdminBoImpl();
            case BOOKING:
                return new BookingBoImpl();


            default:
                return null;
        }
    }
}
