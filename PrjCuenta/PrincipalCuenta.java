import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Cuenta.Cuenta;

public class PrincipalCuenta {
    public static void main() {
        Scanner sc = new Scanner (System.in);
        List<Cuenta> cuentasCreadas = new ArrayList<> ();
        int cuentaActual = -1;
        
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú principal"); 
            System.out.println("1) Crear Cuenta"); 
            System.out.println("2) Conocer la cantidad de Cuentas Creadas"); 
            System.out.println("3) Listar Cuentas"); 
            System.out.println("4) Seleccionar Cuenta actual"); 
            System.out.println("5) Depositar"); 
            System.out.println("6) Retirar"); 
            System.out.println("7) Consultar Saldo"); 
            System.out.println("8) Consultar Estado (toString)");
            System.out.println("9) Modificar nombre cuenta");
            System.out.println("0) Salir");
            
            String op = sc.nextLine().trim();
            
            switch (op) {
                case "1" : { // Crear cuenta
                    System.out.print("Nombre de la cuenta: ");
                    String nombreCuenta = sc.nextLine().trim();
                    
                    System.out.print("Saldo: ");
                    String entradaSaldo = sc.nextLine().trim();
                    
                    try{
                        double saldoCuenta = Double.parseDouble(entradaSaldo);
                        Cuenta nuevaCuenta;
                        if (nombreCuenta.isEmpty()){
                            nuevaCuenta = new Cuenta(saldoCuenta);
                        } else {
                            nuevaCuenta = new Cuenta(nombreCuenta, saldoCuenta);
                        }
                        cuentasCreadas.add(nuevaCuenta);
                        System.out.println("Cuenta creada exitosamente");
                    } catch (NumberFormatException e) {
                        System.out.println("Error, el saldo debe de ser un numero");
                    }
                    break;
                }
                
                case "2" : { // Cantidad de cuentas creadas
                    System.out.println("Cantidad de cuentas creadas: " + Cuenta.getCantCuentasCreadas());
                    break;
                }
                
                case "3" : { // Listar cuentas
                    System.out.println("Indice | Código de cuenta");
                    
                    for (int i = 0; i < cuentasCreadas.size(); i++){
                        System.out.println("   " + i + "   | " +
                                        cuentasCreadas.get(i).getCodCuenta());
                    }
                    break;
                }
                case "4" : { // Selecionar cuenta actual
                    System.out.println("Ingrese el código de la cuenta por seleccionar");
                    String codigoCuenta = sc.nextLine().trim();
                    boolean cuentaEncontrada = false;
                    for (int i = 0; i < cuentasCreadas.size(); i++){
                        if (cuentasCreadas.get(i).getCodCuenta().equals(codigoCuenta)) {
                            cuentaActual = i;
                            cuentaEncontrada = true;
                        }
                    }
                    if (cuentaEncontrada) {
                    System.out.println("Cuenta seleccionada: " + 
                                        cuentasCreadas.get(cuentaActual).getCodCuenta());
                    } else {
                        System.out.println("No existe una cuenta con el codigo: " + codigoCuenta);
                    }
                    break;
                }
                
                case "5" : { // Depositar cuenta actual
                    System.out.print("Ingrese el monto a depositar: ");
                    String entradaDeposito = sc.nextLine().trim();
                    try{
                        double montoDeposito = Double.parseDouble(entradaDeposito);
                        double saldo = cuentasCreadas.get(cuentaActual).depositar(montoDeposito);
                        System.out.println("Su saldo es de: " + saldo);
                    } catch (NumberFormatException e) {
                        System.out.println("Error, el monto debe de un número");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No hay una cuenta seleccionada");                        
                    }
                    break;
                }
                
                case "6" : { // Retirar cuenta actual
                    System.out.print("Ingrese el monto a retirar: ");
                    String entradaRetiro = sc.nextLine().trim();
                    try {
                        double montoRetiro = Double.parseDouble(entradaRetiro);
                        double saldo = cuentasCreadas.get(cuentaActual).retirar(montoRetiro);
                        System.out.println("Su saldo es de: " + saldo);
                    } catch (NumberFormatException e) {
                        System.out.println("Error, el monto debe de un número");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No hay una cuenta seleccionada");                        
                    }
                    break;
                }
                
                case "7" : { // Consultar saldo cuenta actual
                    try {
                        System.out.println(cuentasCreadas.get(cuentaActual).getSaldo());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No hay una cuenta seleccionada");                        
                    }
                    break;
                }
                
                case "8" : { // Consultar estado cuenta actual
                    try {
                        System.out.println(cuentasCreadas.get(cuentaActual).toString());
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No hay una cuenta seleccionada");                        
                    }
                    break;
                }
                
                case "9" : { // Modificar nombre cuenta
                    try {
                        System.out.print("Ingrese el nuevo nombre de la cuenta: ");
                        String nombre = sc.nextLine().trim();
                        cuentasCreadas.get(cuentaActual).setNombreCuentaHabiente(nombre);
                        System.out.println("El nombre de la cuenta ahora es: " +
                                            nombre);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No hay una cuenta seleccionada");                        
                    }
                    break;
                }
                
                case "0" : { // Salir
                    salir = !salir;
                    break;
                }
                
                default : { // Manejo de salidas inválidas
                    System.out.println("Seleccione una opción válida");
                    break;
                }
                    
            }
        }
    }   
}