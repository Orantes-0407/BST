
package umg.edu.progra.arboles;

/**
 * Clase principal que demuestra el uso del Arbol Binario de Busqueda (BST)
 * implementado manualmente, sin usar librerias como java.util.
 *
 * @author Walter Cordova
 */
public class Principal {

    public static void main(String[] args) {

        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        /*
         * Insertamos estos valores para formar el siguiente BST:
         *
         *               50
         *              /  \
         *            30    70
         *           /  \   / \
         *          20  40 60  80
         *         /
         *        10
         */
        int[] valores = {50, 30, 70, 20, 40, 60, 80, 10};

        for (int v : valores) {
            arbol.insertar(v);
        }

        System.out.println("===== Arbol Binario de Busqueda =====");

        System.out.println("Tamanio: " + arbol.tamanio());
        System.out.println("Altura:  " + arbol.altura());
        System.out.println("Minimo:  " + arbol.minimo());
        System.out.println("Maximo:  " + arbol.maximo());
        System.out.println("Hojas:   " + arbol.contarHojas());

   
        System.out.println("\n--- Representacion visual ---");

        arbol.imprimirArbol();

   
        System.out.println("\n--- Recorridos ---");

        System.out.print("InOrden    (ascendente): ");
        arbol.inOrden();

        System.out.print("PreOrden   (raiz primero): ");
        arbol.preOrden();

        System.out.print("PostOrden  (raiz al final): ");
        arbol.postOrden();

        System.out.print("Por niveles (BFS):         ");
        arbol.recorridoPorNiveles();

   
        System.out.println("\n--- Busquedas ---");

        System.out.println("Contiene 40? " + arbol.contiene(40));
        System.out.println("Contiene 99? " + arbol.contiene(99));

        System.out.println("\n--- Eliminacion ---");

        System.out.println("Eliminando 20...");
        arbol.eliminar(20);

        System.out.print("InOrden tras eliminar 20: ");
        arbol.inOrden();

        System.out.println("Eliminando 30...");
        arbol.eliminar(30);

        System.out.print("InOrden tras eliminar 30: ");
        arbol.inOrden();

        System.out.println("Eliminando 50...");
        arbol.eliminar(50);

        System.out.print("InOrden tras eliminar 50: ");
        arbol.inOrden();


        System.out.println("\n--- Estado final ---");

        arbol.imprimirArbol();

        System.out.println("Tamanio final: " + arbol.tamanio());
        System.out.println("Altura final:  " + arbol.altura());

 
        ArbolBinarioBusqueda pruebas = new ArbolBinarioBusqueda();

        int[] datos = {50, 30, 70, 20, 40, 60, 80, 10};

        for (int v : datos) {
            pruebas.insertar(v);
        }

        System.out.println("\n===== PROBLEMA 1 =====");

        System.out.println("Contar nodos recursivamente: "
                + pruebas.contarNodos());

        System.out.println("Tamanio oficial: "
                + pruebas.tamanio());


        System.out.println("\n===== PROBLEMA 2 =====");

        System.out.println("El arbol esta balanceado? "
                + pruebas.esBalanceado());

        ArbolBinarioBusqueda desbalanceado =
                new ArbolBinarioBusqueda();

        desbalanceado.insertar(1);
        desbalanceado.insertar(2);
        desbalanceado.insertar(3);
        desbalanceado.insertar(4);
        desbalanceado.insertar(5);

        System.out.println("\nArbol desbalanceado:");

        desbalanceado.imprimirArbol();

        System.out.println("Esta balanceado? "
                + desbalanceado.esBalanceado());

     
        System.out.println("\n===== PROBLEMA 3 =====");

        System.out.println("Es BST valido? "
                + pruebas.esBSTValido());


        System.out.println("\n===== PROBLEMA 4 =====");

        System.out.println("LCA(10, 40): "
                + pruebas.ancestroComunMasBajo(10, 40));

        System.out.println("LCA(10, 80): "
                + pruebas.ancestroComunMasBajo(10, 80));

        System.out.println("LCA(60, 80): "
                + pruebas.ancestroComunMasBajo(60, 80));

        System.out.println("\n===== PROBLEMA 5 =====");

        System.out.println("\nArbol original:");

        pruebas.imprimirArbol();

        System.out.print("InOrden original: ");

        pruebas.inOrden();

        pruebas.invertir();

        System.out.println("\nArbol invertido:");

        pruebas.imprimirArbol();

        System.out.print("InOrden invertido: ");

        pruebas.inOrden();
    }
}


