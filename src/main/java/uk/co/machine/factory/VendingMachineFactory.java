package uk.co.machine.factory;

import uk.co.machine.api.VendingMachine;
import uk.co.machine.implementation.VendingMachineImpl;

/**
 * Factory class to create instance of Vending Machine, this can be extended to create instance of * different types of vending machines.
 */
public class VendingMachineFactory {
    public static VendingMachine createVendingMachine() {
        return new VendingMachineImpl();
    }
}

