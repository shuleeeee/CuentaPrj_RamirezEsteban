package Cuenta;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Write a description of class Cuenta here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cuenta {

    private String codCuenta;
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    private static int cantCuentasCreadas;

    /**
     * Constructor for objects of class Cuenta
     */
    public Cuenta (String nombreCuentaHabiente, double pSaldo) {
        cantCuentasCreadas++;
        codCuenta = "cta-" + cantCuentasCreadas;
        saldo = pSaldo;
        this.nombreCuentaHabiente = nombreCuentaHabiente;
        fechaCreacion = establecerFechaCreacion();        
    }
    
    public Cuenta (double pSaldo) {
        this("", pSaldo);
    }
    
    public void setNombreCuentaHabiente (String pNombreCuentaHabiente) {
        nombreCuentaHabiente = pNombreCuentaHabiente;
    }
    
    public String getCodCuenta () {
        return codCuenta;
    }
    
    public double getSaldo () {
        return saldo;
    }
    
    public double depositar (double monto) {
        if (monto >= 0) {
            saldo += monto;
            cantDepositosRealizados++;
        } else {
            System.out.println("Deposito denegado, el monto debe de ser un número positivo");
        }
        return saldo;
    }
    
    public double retirar (double monto) {
        if (validarRetiro(monto)) {
            saldo -= monto;
            cantRetirosExitososRealizados++;
        } else {
            System.out.println("Retiro inválido, el monto debe de ser un número positivo y no mayor al saldo");
        }
        return saldo;    
    }
    
    private boolean validarRetiro (double monto) {
        return (monto <= saldo && monto > 0);
    }
    
    public static int getCantCuentasCreadas () {
        return cantCuentasCreadas;
    }
    
    public String toString () {
        return  "\nCódigo de la cuenta: " + codCuenta + "\n" +
                "Nombre de la cuenta: " + nombreCuentaHabiente + "\n" +
                "Saldo: " + saldo + "\n" +
                "Fecha de creación: " + fechaCreacion + "\n" +
                "Cantidad de depósitos: " + cantDepositosRealizados + "\n" +
                "Cantidad de retiros exitosos: " + cantRetirosExitososRealizados + "\n";
    }
    
    private String establecerFechaCreacion () {
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return fechaHora.format(formatoFecha);
    }
}