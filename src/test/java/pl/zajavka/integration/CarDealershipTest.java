package pl.zajavka.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import pl.zajavka.business.*;
import pl.zajavka.business.dao.*;
import pl.zajavka.infrastructure.configuration.HibernateUtil;
import pl.zajavka.infrastructure.database.repository.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarDealershipTest {

    private CarDealershipManagementService carDealershipManagementService;
    private CarPurchaseService carPurchaseService;
    private CarServiceRequestService carserviceRequestService;
    private CarServiceProcessingService carServiceProcessingService;

    @BeforeEach
    void beforeEach() {

        CarDAO carDAO = new CarRepository();
        SalesmanDAO salesmanDAO = new SalesmanRepository();
        CustomerDAO customerDAO = new CustomerRepository();
        CarServiceRequestDAO carServiceRequestDAO = new CarServiceRequestRepository();
        MechanicDAO mechanicDAO = new MechanicRepository();
        ServiceDAO serviceDAO = new ServiceRepository();
        PartDAO partDAO = new PartRepository();
        ServiceRequestProcessingDAO serviceRequestProcessingDAO = new ServiceRequestProcessingRepository();

        FileDataPreparationService fileDataPreparationService = new FileDataPreparationService();
        ServiceCatalogService serviceCatalogService = new ServiceCatalogService(serviceDAO);
        PartCatalogueService partCatalogueService = new PartCatalogueService(partDAO);
        CustomerService customerService = new CustomerService(customerDAO);
        CarService carService = new CarService(carDAO);
        SalesmanService salesmanService = new SalesmanService(salesmanDAO);
        MechanicService mechanicService = new MechanicService(mechanicDAO);

        this.carDealershipManagementService = new CarDealershipManagementService(
                new CarDealershipManagementRepository(),
                fileDataPreparationService
        );

        this.carPurchaseService = new CarPurchaseService(
                fileDataPreparationService,
                customerService,
                carService,
                salesmanService
        );

        this.carserviceRequestService = new CarServiceRequestService(
                fileDataPreparationService,
                carService,
                customerService,
                carServiceRequestDAO
        );

        this.carServiceProcessingService = new CarServiceProcessingService(
            fileDataPreparationService,
            mechanicService,
                carService,
                serviceCatalogService,
                partCatalogueService,
                carserviceRequestService,
                serviceRequestProcessingDAO
        );
    }

    @AfterAll
    static void afterAll() {
        HibernateUtil.closeSessionFactory();
    }

    @Test
    @Order(1)
    void purge() {
        log.info("### RUNNING ORDER 1");
        carDealershipManagementService.purge();
    }

    @Test
    @Order(2)
    void init() {
        log.info("### RUNNING ORDER 2");
        carDealershipManagementService.init();
    }

    @Test
    @Order(3)
    void purchase() {
        log.info("### RUNNING ORDER 3");
        carPurchaseService.purchase();
    }

    @Test
    @Order(4)
    void processServiceRequest() {
        log.info("### RUNNING ORDER 4");
        carserviceRequestService.requestService();
    }

    @Test
    @Order(5)
    void printCarHistory() {
        log.info("### RUNNING ORDER 5");
        carServiceProcessingService.process();
    }
}
