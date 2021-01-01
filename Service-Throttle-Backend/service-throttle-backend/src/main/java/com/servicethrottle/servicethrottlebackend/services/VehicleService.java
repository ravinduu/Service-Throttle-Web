package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.CustomerVehicleNotFound;
import com.servicethrottle.servicethrottlebackend.exceptions.UserNotLogIn;
import com.servicethrottle.servicethrottlebackend.exceptions.VehicleEngineDosentExist;
import com.servicethrottle.servicethrottlebackend.exceptions.VehicleMakeDosentExist;
import com.servicethrottle.servicethrottlebackend.models.*;
import com.servicethrottle.servicethrottlebackend.models.dto.*;
import com.servicethrottle.servicethrottlebackend.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final VehicleModelRepository vehicleModelRepository;
    private final VehicleEngineRepository vehicleEngineRepository;


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

    @Transactional(readOnly = true)
    public CustomerVehicle getMyVehicle(long id) {
        Customer currentCustomer = customerService.getCurrentCustomer();
        CustomerVehicle customerVehicle = customerVehicleRepository.findById(id).orElseThrow(
                ()-> new CustomerVehicleNotFound("id")
        );

        if (currentCustomer.getUsername().equals(customerVehicle.getCustomer().getUsername())) return customerVehicle;

        throw new UserNotLogIn("User not login");
    }

    public MobileServiceVehicle addMobileServiceVehicle(MobileServiceVehicleDto mobileServiceVehicleDto) {
        MobileServiceVehicle mobileServiceVehicle = new MobileServiceVehicle();
        mobileServiceVehicle.setYear(mobileServiceVehicleDto.getYear());
        mobileServiceVehicle.setVehicleMake(mobileServiceVehicleDto.getVehicleMake());
        mobileServiceVehicle.setVehicleModel(mobileServiceVehicleDto.getVehicleModel());
        mobileServiceVehicle.setVehicleEngine(mobileServiceVehicleDto.getVehicleEngine());
        mobileServiceVehicle.setCapacity(mobileServiceVehicleDto.getCapacity());
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

    @Transactional(readOnly = true)
    public List<VehicleMake> getAllVehicleMake() {
        return vehicleMakeRepository.findAll().stream().collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VehicleMake getVehicleMake(long vehicleMakeId) throws VehicleMakeDosentExist {
       Optional<VehicleMake> vehicleMake = vehicleMakeRepository.findById(vehicleMakeId);

       if(vehicleMake.isPresent()){
           return vehicleMake.get();
       }
       throw new VehicleMakeDosentExist("No Vehicle make for id : "+vehicleMakeId);
    }

    public VehicleMake updateVehicleMake(long id, VehicleMakeDto vehicleMakeDto) throws VehicleMakeDosentExist {
        VehicleMake vehicleMake = getVehicleMake(id);
        vehicleMake.setMake(vehicleMakeDto.getMake());
        vehicleMakeRepository.save(vehicleMake);
        return vehicleMake;
    }

    public int deleteVehicleMake(long id) throws VehicleMakeDosentExist {
        VehicleMake vehicleMakeToDelete = getVehicleMake(id);
        if(vehicleMakeToDelete.getMake() != null){
            vehicleMakeRepository.delete(vehicleMakeToDelete);
            return 1;
        }
        return 0;
    }

    public VehicleModel addVehicleModel(VehicleModelDto vehicleModelDto) {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setVehicleMake(vehicleModelDto.getVehicleMake());
        vehicleModel.setModel(vehicleModelDto.getModel());
        vehicleModelRepository.save(vehicleModel);
        return vehicleModel;
    }

    public List<VehicleModel> getAllVehicleModel() {
        return vehicleModelRepository.findAll().stream().collect(Collectors.toList());
    }

    public VehicleModel getVehicleModel(long id) throws VehicleModelDosentExist {
        Optional<VehicleModel> vehicleModels = vehicleModelRepository.findById(id);
        if (vehicleModels.isPresent()) {
            return vehicleModels.get();

        }

        throw new VehicleModelDosentExist("No Vehicle make for id : "+id);
    }

    public List<VehicleModel> getAllVehicleModelByMake(long makeId) throws VehicleMakeDosentExist, VehicleModelDosentExist {
        VehicleMake vehicleMake = getVehicleMake(makeId);
        Optional<VehicleModel> vehicleModels = vehicleModelRepository.findAllByVehicleMake(vehicleMake);

        if (vehicleModels.isPresent()) return vehicleModels.stream().collect(Collectors.toList());

        throw new VehicleModelDosentExist("Vehicle models doesn't exist for "+ vehicleMake.getMake().name());
    }

    public VehicleModel updateVehicleModel(long id, VehicleModelDto vehicleModelDto) throws VehicleModelDosentExist {
        VehicleModel vehicleModelToUpdate = getVehicleModel(id);

        vehicleModelToUpdate.setVehicleMake(vehicleModelDto.getVehicleMake());
        vehicleModelDto.setModel(vehicleModelDto.getModel());
        vehicleModelRepository.save(vehicleModelToUpdate);
        return vehicleModelToUpdate;
    }

    public int deleteVehicleModel(long id) throws VehicleModelDosentExist {
        VehicleModel vehicleModelToDelete = getVehicleModel(id);
        if (vehicleModelToDelete != null) {
            vehicleModelRepository.delete(vehicleModelToDelete);
            return 1;
        }
        return 0;
    }

    public VehicleEngine addVehicleEngine(VehicleEngineDto vehicleEngineDto) {
        VehicleEngine vehicleEngine = new VehicleEngine();
        vehicleEngine.setEngine(vehicleEngineDto.getEngine());
        vehicleEngineRepository.save(vehicleEngine);
        return vehicleEngine;
    }

    public List<VehicleEngine> getAllVehicleEngine() {
        return vehicleEngineRepository.findAll().stream().collect(Collectors.toList());
    }

    public VehicleEngine getVehicleEngine(long id) throws VehicleEngineDosentExist {
        Optional<VehicleEngine> vehicleEngine = vehicleEngineRepository.findById(id);

        if (vehicleEngine.isPresent()) return vehicleEngine.get();

        throw new VehicleEngineDosentExist("No Vehicle engine for id : "+id);
    }

    public VehicleEngine updateVehicleEngine(long id, VehicleEngineDto vehicleEngineDto) throws VehicleEngineDosentExist {
        VehicleEngine vehicleEngineToUpdate = getVehicleEngine(id);
        vehicleEngineDto.setEngine(vehicleEngineDto.getEngine());
        vehicleEngineRepository.save(vehicleEngineToUpdate);
        return vehicleEngineToUpdate;

    }

    public int deleteVehicleEngine(long id) throws VehicleEngineDosentExist {
        VehicleEngine vehicleEngineToDelete = getVehicleEngine(id);
        if (vehicleEngineToDelete == null){
            vehicleEngineRepository.delete(vehicleEngineToDelete);
            return 1;
        }
        return 0;
    }
}
