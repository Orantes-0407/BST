
package umg.edu.progra.arboles;

/**
 * Arbol Binario de Busqueda (BST) implementado manualmente,
 * sin utilizar java.util ni librerias externas.
 *
 * @author Walter Cordova
 */
public class ArbolBinarioBusqueda {

    private Nodo raiz;
    private int tamanio;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
        this.tamanio = 0;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int tamanio() {
        return tamanio;
    }

  
    public void insertar(int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            tamanio++;
            return;
        }

        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo actual, int valor) {

        if (actual == null) {
            tamanio++;
            return new Nodo(valor);
        }

        if (valor < actual.dato) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }

        return actual;
    }

    // ============================================================
    // BUSCAR
    // ============================================================

    public Nodo buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private Nodo buscarRecursivo(Nodo actual, int valor) {

        if (actual == null) {
            return null;
        }

        if (valor == actual.dato) {
            return actual;
        }

        if (valor < actual.dato) {
            return buscarRecursivo(actual.izquierdo, valor);
        }

        return buscarRecursivo(actual.derecho, valor);
    }

    public boolean contiene(int valor) {
        return buscar(valor) != null;
    }

    // ============================================================
    // ELIMINAR
    // ============================================================

    public boolean eliminar(int valor) {

        int tamanioPrevio = tamanio;

        raiz = eliminarRecursivo(raiz, valor);

        return tamanio < tamanioPrevio;
    }

    private Nodo eliminarRecursivo(Nodo actual, int valor) {

        if (actual == null) {
            return null;
        }

        if (valor < actual.dato) {

            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);

        } else if (valor > actual.dato) {

            actual.derecho = eliminarRecursivo(actual.derecho, valor);

        } else {

            // Caso 1: hoja
            if (actual.izquierdo == null && actual.derecho == null) {
                tamanio--;
                return null;
            }

            // Caso 2: un hijo derecho
            if (actual.izquierdo == null) {
                tamanio--;
                return actual.derecho;
            }

            // Caso 2: un hijo izquierdo
            if (actual.derecho == null) {
                tamanio--;
                return actual.izquierdo;
            }

            // Caso 3: dos hijos
            int sucesor = minimo(actual.derecho);

            actual.dato = sucesor;

            actual.derecho = eliminarRecursivo(actual.derecho, sucesor);
        }

        return actual;
    }

    // ============================================================
    // MINIMO Y MAXIMO
    // ============================================================

    public int minimo() {

        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }

        return minimo(raiz);
    }

    private int minimo(Nodo nodo) {

        Nodo actual = nodo;

        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }

        return actual.dato;
    }

    public int maximo() {

        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }

        Nodo actual = raiz;

        while (actual.derecho != null) {
            actual = actual.derecho;
        }

        return actual.dato;
    }

    // ============================================================
    // ALTURA
    // ============================================================

    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo nodo) {

        if (nodo == null) {
            return -1;
        }

        int izq = alturaRecursiva(nodo.izquierdo);
        int der = alturaRecursiva(nodo.derecho);

        return 1 + (izq > der ? izq : der);
    }

    // ============================================================
    // CONTAR HOJAS
    // ============================================================

    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo nodo) {

        if (nodo == null) {
            return 0;
        }

        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }

        return contarHojasRecursivo(nodo.izquierdo)
                + contarHojasRecursivo(nodo.derecho);
    }

    // ============================================================
    // PROBLEMA 1 - CONTAR NODOS
    // ============================================================

    public int contarNodos() {
        return contarNodosRecursivo(raiz);
    }

    private int contarNodosRecursivo(Nodo nodo) {

        if (nodo == null) {
            return 0;
        }

        return 1
                + contarNodosRecursivo(nodo.izquierdo)
                + contarNodosRecursivo(nodo.derecho);
    }

    
    public boolean esBalanceado() {
        return esBalanceadoRecursivo(raiz);
    }

    private boolean esBalanceadoRecursivo(Nodo nodo) {

        if (nodo == null) {
            return true;
        }

        int alturaIzq = alturaRecursiva(nodo.izquierdo);
        int alturaDer = alturaRecursiva(nodo.derecho);

        int diferencia = alturaIzq - alturaDer;

        if (diferencia < 0) {
            diferencia = -diferencia;
        }

        return diferencia <= 1
                && esBalanceadoRecursivo(nodo.izquierdo)
                && esBalanceadoRecursivo(nodo.derecho);
    }

  
    public boolean esBSTValido() {
        return esBSTValidoRecursivo(
                raiz,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE
        );
    }

    private boolean esBSTValidoRecursivo(
            Nodo nodo,
            int min,
            int max
    ) {

        if (nodo == null) {
            return true;
        }

        if (nodo.dato <= min || nodo.dato >= max) {
            return false;
        }

        return esBSTValidoRecursivo(
                nodo.izquierdo,
                min,
                nodo.dato
        )
                &&
                esBSTValidoRecursivo(
                        nodo.derecho,
                        nodo.dato,
                        max
                );
    }



    public int ancestroComunMasBajo(int a, int b) {

        if (!contiene(a) || !contiene(b)) {
            throw new IllegalArgumentException(
                    "Uno o ambos valores no existen"
            );
        }

        Nodo actual = raiz;

        while (actual != null) {

            if (a < actual.dato && b < actual.dato) {

                actual = actual.izquierdo;

            } else if (a > actual.dato && b > actual.dato) {

                actual = actual.derecho;

            } else {

                return actual.dato;
            }
        }

        throw new IllegalArgumentException(
                "No existe ancestro comun"
        );
    }


    public void invertir() {
        invertirRecursivo(raiz);
    }

    private void invertirRecursivo(Nodo nodo) {

        if (nodo == null) {
            return;
        }

        Nodo temporal = nodo.izquierdo;
        nodo.izquierdo = nodo.derecho;
        nodo.derecho = temporal;

        invertirRecursivo(nodo.izquierdo);
        invertirRecursivo(nodo.derecho);
    }

 

    public void inOrden() {
        inOrdenRecursivo(raiz);
        System.out.println();
    }

    private void inOrdenRecursivo(Nodo nodo) {

        if (nodo == null) {
            return;
        }

        inOrdenRecursivo(nodo.izquierdo);
        System.out.print(nodo.dato + " ");
        inOrdenRecursivo(nodo.derecho);
    }

    public void preOrden() {
        preOrdenRecursivo(raiz);
        System.out.println();
    }

    private void preOrdenRecursivo(Nodo nodo) {

        if (nodo == null) {
            return;
        }

        System.out.print(nodo.dato + " ");

        preOrdenRecursivo(nodo.izquierdo);
        preOrdenRecursivo(nodo.derecho);
    }

    public void postOrden() {
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(Nodo nodo) {

        if (nodo == null) {
            return;
        }

        postOrdenRecursivo(nodo.izquierdo);
        postOrdenRecursivo(nodo.derecho);

        System.out.print(nodo.dato + " ");
    }

    
    public void recorridoPorNiveles() {

        if (raiz == null) {
            System.out.println();
            return;
        }

        ColaNodos cola = new ColaNodos();

        cola.encolar(raiz);

        while (!cola.estaVacia()) {

            Nodo actual = cola.desencolar();

            System.out.print(actual.dato + " ");

            if (actual.izquierdo != null) {
                cola.encolar(actual.izquierdo);
            }

            if (actual.derecho != null) {
                cola.encolar(actual.derecho);
            }
        }

        System.out.println();
    }

    
    public void imprimirArbol() {

        if (raiz == null) {
            System.out.println("(arbol vacio)");
            return;
        }

        imprimirArbolRecursivo(raiz, 0);
    }

    private void imprimirArbolRecursivo(Nodo nodo, int nivel) {

        if (nodo == null) {
            return;
        }

        imprimirArbolRecursivo(nodo.derecho, nivel + 1);

        for (int i = 0; i < nivel; i++) {
            System.out.print("     ");
        }

        System.out.println("-> " + nodo.dato);

        imprimirArbolRecursivo(nodo.izquierdo, nivel + 1);
    }


    		// ============================================================
    		// EJERCICIO EXTRA 1 - K-ESIMO MENOR
    		// ============================================================

    		public int kEsimoMenor(int k) {

    		    if (k <= 0 || k > tamanio) {

    		        throw new IllegalArgumentException(
    		                "k fuera de rango"
    		        );
    		    }

    		    int[] contador = {0};

    		    Nodo resultado =
    		            kEsimoMenorRecursivo(
    		                    raiz,
    		                    k,
    		                    contador
    		            );

    		    return resultado.dato;
    		}

    		private Nodo kEsimoMenorRecursivo(
    		        Nodo nodo,
    		        int k,
    		        int[] contador
    		) {

    		    if (nodo == null) {
    		        return null;
    		    }

    		    Nodo izquierda =
    		            kEsimoMenorRecursivo(
    		                    nodo.izquierdo,
    		                    k,
    		                    contador
    		            );

    		    if (izquierda != null) {
    		        return izquierda;
    		    }

    		    contador[0]++;

    		    if (contador[0] == k) {
    		        return nodo;
    		    }

    		    return kEsimoMenorRecursivo(
    		            nodo.derecho,
    		            k,
    		            contador
    		    );
    		}

    		// ============================================================
    		// EJERCICIO EXTRA 2 - IMPRIMIR RANGO
    		// ============================================================

    		public void imprimirRangoOrdenado(
    		        int min,
    		        int max
    		) {

    		    imprimirRangoOrdenadoRecursivo(
    		            raiz,
    		            min,
    		            max
    		    );

    		    System.out.println();
    		}

    		private void imprimirRangoOrdenadoRecursivo(
    		        Nodo nodo,
    		        int min,
    		        int max
    		) {

    		    if (nodo == null) {
    		        return;
    		    }

    		    // recorrer izquierda solo si puede
    		    // haber valores utiles
    		    if (nodo.dato > min) {

    		        imprimirRangoOrdenadoRecursivo(
    		                nodo.izquierdo,
    		                min,
    		                max
    		        );
    		    }

    		    // imprimir si esta en rango
    		    if (nodo.dato >= min
    		            && nodo.dato <= max) {

    		        System.out.print(
    		                nodo.dato + " "
    		        );
    		    }

    		    // recorrer derecha solo si puede
    		    // haber valores utiles
    		    if (nodo.dato < max) {

    		        imprimirRangoOrdenadoRecursivo(
    		                nodo.derecho,
    		                min,
    		                max
    		        );
    		    }
    		}

    		// ============================================================
    		// EJERCICIO EXTRA 3 - DIAMETRO
    		// ============================================================

    		public int diametro() {

    		    int[] diametro = {0};

    		    diametroRecursivo(
    		            raiz,
    		            diametro
    		    );

    		    return diametro[0];
    		}

    		private int diametroRecursivo(
    		        Nodo nodo,
    		        int[] diametro
    		) {

    		    if (nodo == null) {
    		        return -1;
    		    }

    		    int izquierda =
    		            diametroRecursivo(
    		                    nodo.izquierdo,
    		                    diametro
    		            );

    		    int derecha =
    		            diametroRecursivo(
    		                    nodo.derecho,
    		                    diametro
    		            );

    		    int posibleDiametro =
    		            izquierda + derecha + 2;

    		    if (posibleDiametro > diametro[0]) {

    		        diametro[0] =
    		                posibleDiametro;
    		    }

    		    if (izquierda > derecha) {
    		        return izquierda + 1;
    		    }

    		    return derecha + 1;
    		}

    		// ============================================================
    		// EJERCICIO EXTRA 4 - CONSTRUIR BST DESDE ARGS
    		// ============================================================

    		public static ArbolBinarioBusqueda construirDesdeArgs(
    		        String[] args
    		) {

    		    ArbolBinarioBusqueda arbol =
    		            new ArbolBinarioBusqueda();

    		    for (String s : args) {

    		        try {

    		            int valor =
    		                    Integer.parseInt(s);

    		            arbol.insertar(valor);

    		        } catch (NumberFormatException e) {

    		            System.out.println(
    		                    "Valor invalido ignorado: "
    		                            + s
    		            );
    		        }
    		    }

    		    return arbol;
    		}
    	

    private static class NodoCola {

        Nodo valor;
        NodoCola siguiente;

        NodoCola(Nodo valor) {
            this.valor = valor;
        }
    }

    private static class ColaNodos {

        private NodoCola frente;
        private NodoCola fondo;

        boolean estaVacia() {
            return frente == null;
        }

        void encolar(Nodo n) {

            NodoCola nuevo = new NodoCola(n);

            if (frente == null) {

                frente = fondo = nuevo;

            } else {

                fondo.siguiente = nuevo;
                fondo = nuevo;
            }
        }

        Nodo desencolar() {

            if (frente == null) {
                throw new IllegalStateException("Cola vacia");
            }

            Nodo valor = frente.valor;

            frente = frente.siguiente;

            if (frente == null) {
                fondo = null;
            }

            return valor;
        }
    
}

}