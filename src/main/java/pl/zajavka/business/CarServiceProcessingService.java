package pl.zajavka.business;

import lombok.AllArgsConstructor;
import pl.zajavka.business.dao.ServiceRequestProcessingDAO;
import pl.zajavka.domain.CarServiceProcessingRequest;
import pl.zajavka.infrastructure.database.entity.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class CarServiceProcessingService {

    private final FileDataPreparationService fileDataPreparationService;
    private final MechanicService mechanicService;
    private final CarService carService;
    private final ServiceCatalogService serviceCatalogService;
    private final PartCatalogueService partCatalogueService;
    private final CarServiceRequestService carServiceRequestService;
    private final ServiceRequestProcessingDAO serviceRequestProcessingDAO;

    public void process() {
        List<CarServiceProcessingRequest> toProcess = fileDataPreparationService.prepareServiceRequestsToProcess();
        toProcess.forEach(this::processRequest);
    }

    private void processRequest(CarServiceProcessingRequest request) {
        MechanicEntity mechanic = mechanicService.findMechanic(request.getMechanicPesel());
        CarToServiceEntity car = carService.findCarToService(request.getCarVin()).orElseThrow();
        CarServiceRequestEntity serviceRequest = carServiceRequestService.findAnyActiveServiceRequest(request.getCarVin());

        ServiceEntity service = serviceCatalogService.findService(request.getServiceCode());

        ServiceMechanicEntity serviceMechanicEntity = buildServiceMechanicEntity(request, mechanic, serviceRequest, service)

        if (Objects.isNull(request.getPartSerialNumber()) || Objects.isNull(request.getPartQuantity())) {
            serviceRequestProcessingDAO.process(serviceRequest, serviceMechanicEntity);
        } else {
            PartEntity part = partCatalogueService.findPart(request.getPartSerialNumber());
            ServicePartEntity servicePartEntity = buildServicePartEntity(request, serviceRequest, part);
            serviceRequestProcessingDAO.process(serviceRequest, serviceMechanicEntity);
        }

    }
}
