package org.atumar4031;

import org.atumar4031.enums.Gender;
import org.atumar4031.enums.Role;
import org.atumar4031.exceptions.*;
import org.atumar4031.model.Applicant;
import org.atumar4031.model.Customer;
import org.atumar4031.model.Staff;
import org.atumar4031.services.applicant.ApplicantServiceImpl;
import org.atumar4031.services.customer.CustomerServiceImple;
import org.atumar4031.services.staff.CashierStaff;
import org.atumar4031.services.staff.ManagerStaff;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws  IOException {

        // TODO: passing applicant to hire method after applying

        Store phoneStore = new Store(101, "phoneStore");
        Staff managerMustapha = new Staff("MNG/101","Almustapha Tukur Umar","musty@gmail.com",
                "08166966063","Edo Tech Park",Role.MANAGER, Gender.MALE, 25);
        ManagerStaff managerService = new ManagerStaff();

        // APPLICATION AND HIRING PROCESS
        ApplicantServiceImpl applicantService = new ApplicantServiceImpl();
        Applicant ApplicantSamira = new  Applicant("Samira", "samira@gmail.com",
                "08100009813", "Tudun wada area",
                "MSC", 24, Gender.FEMALE, 2);
        Staff CashierSamira ;
        try{
            applicantService.apply(ApplicantSamira,phoneStore);
            managerService.hireCashier(managerMustapha, phoneStore,ApplicantSamira,4);
            System.out.println(phoneStore.getStaffList());
        }catch (ApplicantAlreadyExistException | InvalidInputException | StaffAlreadyExistException e){
            e.printStackTrace();
        }finally {
            System.out.println("application process completed");
        }

        // CUSTOMER AND SHOPPING CART PROCESS
        CashierStaff cashierService = new CashierStaff();
        Customer bala = new Customer("Bala", "abu@gmail.com","08066765467",
                Gender.MALE, "Tudun wada");
        CustomerServiceImple customerService = new CustomerServiceImple();
        try{
            customerService.fundMyWallet(bala, 500000.0);
            customerService.addProductToShoppingCart("iphone Xr",phoneStore,2,bala);
            System.out.println(bala.getShoppingCart());
        }catch (ProductNotFoundException | InvalidInputException e){
            e.printStackTrace();
        }finally {
            System.out.println("you have add product(s) to your shopping cart");
        }

        try {
            CashierSamira = phoneStore.getStaffList().get(0);
            cashierService.processCustomersInQueue(phoneStore, CashierSamira);
        }catch (StaffNotAuthorizedException|
                EmptyShoppingCartException|
                InsufficientFundException|
                IOException e){
            e.printStackTrace();
        }finally {
            System.out.println("Product sold");
        }
    }
}
