/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colasordenada;

/**
 *
 * @author HP
 */
public class Colasordenada {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
         ColaPrioridad<Integer> cola = new ColaPrioridadSecuenciaOrdenada<>(new ComparadorEntero());
     System.out.println("esta vacia "+ cola.estaVacio());
        cola.insertar(12);
        cola.insertar(15);
        cola.insertar(32);
        cola.insertar(11);
        cola.insertar(10);
       cola.insertar(13);
        cola.insertar(14);
        cola.insertar(9);

        cola.insertar(7);
        cola.insertar(8);
    
        System.out.println(cola);
        
        System.out.println(cola.consultar());
        System.out.println(cola.eliminar());
             System.out.println(cola);
        
        
    }
    
}
