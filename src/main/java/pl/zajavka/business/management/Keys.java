package pl.zajavka.business.management;

public interface Keys {

     enum InputDataGroup {
        INIT,
         BUY_FIRST_TIME,
         SERVICE_REQUEST,
         BUY_AGAIN
     }

     enum Entity {
        SALESMAN,
         MECHANIC,
         CAR,
         SERVICE,
         CUSTOMER,
         PART
     }

     enum Constans {
         WHAT
     }
}