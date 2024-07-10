package lk.ijse.dao.custom;



import lk.ijse.dao.impl.*;


public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory==null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public CrudDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUTOMER :
                return new CustomerDaoImpl();
                case ROOM :
                    return new RoomDaoImpl();
                    case PAYMENT:
                return new PaymentDaoImpl();
            case PACKAGE:
                return new PackageDaoImpl();
            case EMPLOYEE:
                return new EmployeeDaoImpl();
            case ADMIN:
                return new AdminDaoImpl();
            case BOOKING:
                return new BookingDaoImpl();


            default:
                return null;

        }
    }
}
