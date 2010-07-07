package simuladorcolas;

import java.util.ArrayList;


/**
 *
 * @author Fabián Guevara
 * @date 6-7-10
 * @version 1.0
 * La clase simulador contiene toda la lógica para una simulación de colas con
 * un solo servidor, 3 tipos de clientes con tiempos de atención
 * exponencialmente distribuido con media ajustable (igual para todos), y
 * tiempos de llegada también exponencialmente distribuidos, con media ajustable
 * para cada cliente individualmente. También el tiempo de la simulación es
 * ajustable.
 */
public class Simulador
{
    /**
     * Tiempo de llegada esperado de los clientes tipo 1
     */
    private final long lambda1;
    /**
     * Tiempo de llegada esperado de los clientes tipo 2
     */
    private final long lambda2;
    /**
     * Tiempo de llegada esperado de los clientes tipo 3
     */
    private final long lambda3;
    /**
     * Tiempo de atención esperado de los 3 tipos de cliente
     */
    private final long miu;
    /**
     * En cada momento de la simulación, indica el tiempo actual
     */
    private long horaSimulacion; //TM
    /**
     * Indica el tiempo que falta para la siguiente llegada (en cierta unidad)
     */
    private long tiempoProximaLlegada; //AT
    /**
     * Indica el tiempo faltante para la siguiente salida (en la misma unidad
     * que tiempoProximaLlegada)
     */
    private long tiempoProximaSalida; //DT
    /**
     * Si true, el servidor puede atender de inmediato al primero en la cola
     * (o el próximo en llegar, si la cola está vacía). Si false, significa
     * que el servidor está ocupado. Mientras el servidor se mantenga así, todo
     * cliente que llegue tendrá que esperar
     */
    private boolean servidorDisponible; // SS
    /**
     * En cada momento de la simulación, indica el número de clientes en la cola
     */
    private int longitudCola; // WL
    /**
     * Tiempo en el que se acaba la simulación
     */
    private final long tiempoLimiteSimulacion; // MX
    /**
     * Arreglo de listas, una para cada tipo de cliente. La i-ésima lista
     * contiene el tiempo que esperó cada cliente tipo i en el sistema para ser
     * atendido
     */
    private ArrayList<Long>[] tiemposEspera;
    /**
     * Lista con la longitud de la cola al momento de cada llegada y cada salida
     * durante la simulación
     */
    private ArrayList<Integer> longitudesCola;

    /**
     * Crea un nuevo objeto Simulador indicando la media de los tiempos de
     * llegada para cada cliente, la media del tiempo de atención y el tiempo
     * de la simulación.
     * @param l1 tiempo esperado de llegada de los clientes de tipo 1
     * @param l2 tiempo esperado de llegada de los clientes de tipo 2
     * @param l3 tiempo esperado de llegada de los clientes de tipo 3
     * @param t tiempo que tardará la simulación
     */
    public Simulador(long l1, long l2, long l3, long m, long t)
    {
        this.lambda1 = l1;
        this.lambda2 = l2;
        this.lambda3 = l3;
        this.miu = m;
        //this.horaSimulacion = 0;
        this.tiempoProximaLlegada = 0;
        this.tiempoProximaSalida = Long.MAX_VALUE;
        this.servidorDisponible = true;
        this.longitudCola = 0;
        this.tiempoLimiteSimulacion = t;
        this.tiemposEspera = new ArrayList[3];
        for(int i = 0; i < 3; ++i)
            tiemposEspera[i] = new ArrayList<Long>();
        this.longitudesCola = new ArrayList<Integer>();
    }
    /**
     * Inicia la simulación
     */
    public void simular()
    {
        //TODO lógica de la simulación
    }
}
