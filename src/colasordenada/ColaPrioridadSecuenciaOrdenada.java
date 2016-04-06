/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colasordenada;

import java.util.Stack;

/**
 *
 * @author Usuario
 */
public class ColaPrioridadSecuenciaOrdenada<TYPE> extends ColaPrioridad<TYPE> {

    private Nodo<TYPE> primero;
    private Nodo<TYPE> ultimo;
    private Nodo<TYPE> nodo;
    private int cantidadElementos;

    public ColaPrioridadSecuenciaOrdenada(Comparador<TYPE> comparador) {
        super(comparador);
        if (comparador == null) {
            throw new IllegalArgumentException("El comparador no puede ser nulo");
        }
    }

    @Override
    public void insertar(TYPE obj) {
        Nodo<TYPE> nuevo = new Nodo(obj);

        if (cantidadElementos == 0) {

            primero = nuevo;
            ultimo = nuevo;

       

        } else {
            if (this.comparador.esMenor(nuevo.getContenido(), primero.getContenido())) {
                primero.setAnterior(nuevo);
                nuevo.setSiguiente(primero);
                primero = nuevo;

            } else {
                if (this.comparador.esMayor(nuevo.getContenido(), ultimo.getContenido())) {

                    ultimo.setSiguiente(nuevo);
                    nuevo.setAnterior(ultimo);
                    ultimo = nuevo;

                } else {
                    

                    Nodo<TYPE> nodoAMover = getNodo(obj);

                    nuevo.setSiguiente(nodoAMover);
                    nuevo.setAnterior(nodoAMover.getAnterior());

                    nodoAMover.setAnterior(nuevo);
                    nuevo.getAnterior().setSiguiente(nuevo);
                    
                }

            }
        }

        cantidadElementos++;

    }

    private Nodo<TYPE> getNodo(TYPE obj) {
        Nodo<TYPE> nodoAux = primero;

        while (!this.comparador.esMayor(nodoAux.getContenido(), obj)) {
            nodoAux = nodoAux.getSiguiente();

        }
        return nodoAux;
    }

    @Override
    public TYPE eliminar() throws Exception {
        if (cantidadElementos == 0) {
            throw new Exception("La cola esta vacia");
        }
        Nodo<TYPE> nodoAEliminar = getNodoMaximo();
        TYPE contenido = nodoAEliminar.getContenido();

        //Proceso de eliminacion
        if (primero == ultimo) {
            primero = null;
            ultimo = null;
            cantidadElementos = 0;
            return contenido;
        }

        if (nodoAEliminar == primero) {
            primero = nodoAEliminar.getSiguiente();
            primero.setAnterior(null);
            nodoAEliminar.setSiguiente(null);
            cantidadElementos--;
            return contenido;
        }

        if (nodoAEliminar == ultimo) {
            ultimo = nodoAEliminar.getAnterior();
            ultimo.setSiguiente(null);
            nodoAEliminar.setAnterior(null);
            cantidadElementos--;
            return contenido;
        }

        nodoAEliminar.getAnterior().setSiguiente(nodoAEliminar.getSiguiente());
        nodoAEliminar.getSiguiente().setAnterior(nodoAEliminar.getAnterior());

        nodoAEliminar.setSiguiente(null);
        nodoAEliminar.setAnterior(null);
        cantidadElementos--;

        return contenido;
    }

    @Override
    public TYPE consultar() throws Exception {
        if (cantidadElementos == 0) {
            throw new Exception("La cola esta vacia");
        }

        Nodo<TYPE> nodo = getNodoMaximo();
        return nodo.getContenido();
    }

    private Nodo<TYPE> getNodoMaximo() {
        if (cantidadElementos == 0) {
            return null;
        }

        if (cantidadElementos == 1) {
            return primero;
        }

        Nodo<TYPE> mayor = primero;
        Nodo<TYPE> nodoAux = primero.getSiguiente();
        while (nodoAux != null) {
            if (this.comparador.esMayor(nodoAux.getContenido(), mayor.getContenido())) {
                mayor = nodoAux;
            }

            nodoAux = nodoAux.getSiguiente();
        }
        return mayor;

    }

    @Override
    public boolean estaVacio() {
        return cantidadElementos == 0;
    }

    public String toString() {
        Nodo<TYPE> aux = primero;
        String resultado = "";
        while (aux != null) {
            resultado += aux.getContenido().toString() + " - ";
            aux = aux.getSiguiente();
        }
        return resultado;
    }

}
