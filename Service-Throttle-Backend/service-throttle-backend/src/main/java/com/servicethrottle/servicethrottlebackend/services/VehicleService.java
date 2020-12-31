package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.CustomerVehicleNotFound;
import com.servicethrottle.servicethrottlebackend.models.*;
import com.servicethrottle.servicethrottlebackend.models.dto.CustomerVehicleDto;
import com.servicethrottle.servicethrottlebackend.models.dto.MobileServiceVehicleDto;
import com.servicethrottle.servicethrottlebackend.models.dto.VehicleMakeDto;
import com.servicethrottle.servicethrottlebackend.repositories.CustomerVehicleRepository;
import com.servicethrottle.servicethrottlebackend.repositories.MobileServiceVehicleRepository;
import com.servicethrottle.servicethrottlebackend.repositories.VehicleMakeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleService {

    private final CustomerService customerService;
    private final MobileMechanicService mobileMechanicService;

    private final CustomerVehicleRepository customerVehicleRepository;
    private final MobileServiceVehicleRepository mobileServiceVehicleRepository;
    private final VehicleMakeRepository vehicleMakeRepository;


    public CustomerVehicle addCustomerVehicle(CustomerVehicleDto customerVehicleDto){
        CustomerVehicle customerVehicle = new CustomerVehicle();
        Customer currentCustomer = customerService.getCurrentCustomer();
        customerVehicle.setCustomer(currentCustomer);

        customerVehicleRepository.save(customerVehicle);
        return customerVehicle;
    }

    @Transactional(readOnly = true)
    public List<CustomerVehicle> getCustomerVehicles(String username) {
        Customer customer = customerService.getCustomer(username);
        return customerVehicleRepository
                .findAllByCustomer(customer)
                .stream()
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerVehicle> getAllCustomerVehicles() {
        return customerVehicleRepository
                .findAll()
                .stream()
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerVehicle getCustomerVehicle(Long id) {
        return customerVehicleRepository
                .findById(id)
                .orElseThrow(() -> new CustomerVehicleNotFound(id.toString()));
    }

    @Transactional(readOnly = true)
    public List<CustomerVehicle> getMyCustomerVehicles() {
        Customer currentCustomer = customerService.getCurrentCustomer();
        return customerVehicleRepository
                .findAllByCustomer(currentCustomer)
                .stream()
                .collect(Collectors.toList());
    }

    public MobileServiceVehicle addMobileServiceVehicle(MobileServiceVehicleDto mobileServiceVehicleDto) {
        MobileServiceVehicle mobileServiceVehicle = new MobileServiceVehicle();
        mobileServiceVehicleRepository.save(mobileServiceVehicle);

        return mobileServiceVehicle;
    }


    public MobileServiceVehicle addMechanicToMobileServiceVehicle(long mobileServiceVehicleId, String  mobileMechanicUsername) {
        MobileMechanic mobileMechanic = mobileMechanicService.getMobileMechanic(mobileMechanicUsername);
        Optional<MobileServiceVehicle> mobileServiceVehicle = mobileServiceVehicleRepository.findById(mobileServiceVehicleId);

        if (mobileServiceVehicle.isPresent()) mobileServiceVehicle.stream().map(mobileServiceVehicle1 -> {
            mobileServiceVehicle1.setMobileMechanic(mobileMechanic);
            //add check condition for MM
            mobileServiceVehicleRepository.save(mobileServiceVehicle1);
            return mobileServiceVehicle1;
        });

        return new MobileServiceVehicle();
    }

    public MobileServiceVehicle removeMechanicOfMobileServiceVehicle(long mobileServiceVehicleId) {
        Optional<MobileServiceVehicle> mobileServiceVehicle = mobileServiceVehicleRepository.findById(mobileServiceVehicleId);

        if (mobileServiceVehicle.isPresent()) {
            mobileServiceVehicle.stream().map(mobileServiceVehicle1 -> {
                mobileServiceVehicle1.setMobileMechanic(null);
                mobileServiceVehicleRepository.save(mobileServiceVehicle1);
                return mobileServiceVehicle1;
            });
        }
        return new MobileServiceVehicle();
    }

    public MobileServiceVehicle changeMechanicOfMobileServiceVehicle(long mobileServiceVehicleId, String mobileMechanicUsername) {

        Optional<MobileServiceVehicle> mobileServiceVehicle = mobileServiceVehicleRepository.findById(mobileServiceVehicleId);

        if (mobileServiceVehicle.isPresent()) {
            mobileServiceVehicle.stream().map(mobileServiceVehicle1 -> {
                MobileMechanic mobileMechanic = mobileServiceVehicle1.getMobileMechanic();

                if(mobileMechanic != null && mobileMechanic.getUsername() != mobileMechanicUsername ){
                    MobileMechanic newMobileMechanic = mobileMechanicService.getMobileMechanic(mobileMechanicUsername);
                    mobileServiceVehicle1.setMobileMechanic(newMobileMechanic);
                }

                System.out.println("Same Mechanic");
                mobileServiceVehicleRepository.save(mobileServiceVehicle1);
                return mobileServiceVehicle1;
            });
        }
        return new MobileServiceVehicle();

    }

    public MobileServiceVehicle updateMobileServiceVehicle(MobileServiceVehicleDto mobileServiceVehicleDto) {
        //implement
        return null;
    }

    @Transactional(readOnly = true)
    public MobileServiceVehicle getMobileServiceVehicle(long mobileServiceVehicleId) {

        Optional<MobileServiceVehicle> mobileServiceVehicle = mobileServiceVehicleRepository.findById(mobileServiceVehicleId);

        if (mobileServiceVehicle.isPresent()) {
            return mobileServiceVehicle.get();
        }

        return new MobileServiceVehicle();
    }

    @Transactional(readOnly = true)
    public List<MobileServiceVehicle> getAllMobileServiceVehicle() {
        return mobileServiceVehicleRepository
                .findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public int removeMobileServiceVehicle(long mobileServiceVehicleId) {
        Optional<MobileServiceVehicle> mobileServiceVehicle = mobileServiceVehicleRepository.findById(mobileServiceVehicleId);

        if (mobileServiceVehicle.isPresent()) {
            mobileServiceVehicle.stream().map(mobileServiceVehicle1 -> {
                mobileServiceVehicleRepository.delete(mobileServiceVehicle1);
                return 1;
            });
        }
        return 0;
    }

    public VehicleMake addVehicleMake(VehicleMakeDto vehicleMakeDto) {
        VehicleMake vehicleMake = new VehicleMake();
        vehicleMake.setMake(vehicleMakeDto.getMake());
        vehicleMakeRepository.save(vehicleMake);
        return vehicleMake;
    }

    public List<VehicleMake> getAllVehicleMake() {
        return vehicleMakeRepository.findAll().stream().collect(Collectors.toList());
    }

    public VehicleMake getVehicleMake(long vehicleMakeId) {
       Optional<VehicleMake> vehicleMake = vehicleMakeRepository.findById(vehicleMakeId);

       if(vehicleMake.isPresent()){
           return vehicleMake.get();
       }

       return new VehicleMake();
    }

    public VehicleMake updateVehicleMake(long id, VehicleMakeDto vehicleMakeDto) {
        VehicleMake vehicleMake = getVehicleMake(id);
        vehicleMake.setMake(vehicleMakeDto.getMake());
        vehicleMakeRepository.save(vehicleMake);
        return vehicleMake;
    }

    public int deleteVehicleMake(long id) {
        VehicleMake vehicleMakeToDelete = getVehicleMake(id);
        if(vehicleMakeToDelete.getMake() != null){
            vehicleMakeRepository.delete(vehicleMakeToDelete);
            return 1;
        }
        return 0;
    }
}
