package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.ValueNotFoundException;
import com.servicethrottle.servicethrottlebackend.models.Customer;
import com.servicethrottle.servicethrottlebackend.models.ServiceRequest;
import com.servicethrottle.servicethrottlebackend.models.dto.ServiceRequestDto;
import com.servicethrottle.servicethrottlebackend.repositories.ServiceRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceRequestService {

    private final CustomerService customerService;

    private final ServiceRequestRepository serviceRequestRepository;

    public ServiceRequest createServiceRequest(ServiceRequestDto serviceRequestDto) {
        ServiceRequest serviceRequest = new ServiceRequest();
        return addDataAndSaveServiceRequest(serviceRequest, serviceRequestDto);
    }

    private ServiceRequest addDataAndSaveServiceRequest(ServiceRequest serviceRequest, ServiceRequestDto serviceRequestDto) {

        Customer customer = customerService.getCurrentCustomer();

        serviceRequest.setCustomer(customer);
        serviceRequest.setCustomerVehicle(serviceRequestDto.getCustomerVehicle());
        serviceRequest.setVehicleServices(serviceRequestDto.getVehicleServices());
        serviceRequestRepository.save(serviceRequest);
        return serviceRequest;
    }

    public List<ServiceRequest> getAllServiceRequests() {
        return new ArrayList<>(serviceRequestRepository.findAll());
    }

    public ServiceRequest getAServiceRequests(long id) {
        return serviceRequestRepository.findById(id).orElseThrow(() -> new ValueNotFoundException("No Service Request to Be Found By id : "+id));
    }

    public ServiceRequest updateServiceRequest(long id, ServiceRequestDto serviceRequestDto) {
        ServiceRequest serviceRequest = getAServiceRequests(id);
        return addDataAndSaveServiceRequest(serviceRequest, serviceRequestDto);
    }

    public int deleteServiceRequest(long id) {
        ServiceRequest serviceRequest = getAServiceRequests(id);
        if (serviceRequest != null){
            serviceRequestRepository.delete(serviceRequest);
            return 1;
        }
        return 0;
    }
}
